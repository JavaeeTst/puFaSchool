package com.pufaschool.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.client.dao.ClientUserDao;
import com.pufaschool.client.service.ClientUserService;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;
import com.pufaschool.conn.exception.*;
import com.pufaschool.conn.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class ClientUserServiceImpl extends ServiceImpl<ClientUserDao, PuFaUser> implements ClientUserService {


    @Autowired
    private RedisTemplate redisTemplate;

    //用户名发送短信验证
    @Value("${spring.mail.username}")
    private String username;

    //发送验证码对象
    @Resource
    private JavaMailSender sender;


    /**
     * 用户修改密码
     *
     * @param sysUserUpdatePasswordVo
     * @return
     */
    @Override
    public boolean updatePassword(SysUserUpdatePasswordVo sysUserUpdatePasswordVo) {
        //使用md5加密旧密码比对数据库
        String oldPassword = Md5Util.encode(sysUserUpdatePasswordVo.getOldPassword());

        //在查询数据库的密码，使用wrapper条件对象，username为查询条件
        LambdaQueryWrapper<PuFaUser> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PuFaUser::getUsername, sysUserUpdatePasswordVo.getUsername());

        PuFaUser puFaUser = baseMapper.selectOne(wrapper);

        //判断旧密码是否与数据库密码一致
        if (!puFaUser.getPassword().equals(oldPassword)) {

            throw new PasswordErrorException("原密码不正确");
        }
        //程序走到这里，再次匹配旧密码与新密码是否一致
        if (sysUserUpdatePasswordVo.getNewPassword().equals(sysUserUpdatePasswordVo.getOldPassword())) {

            throw new PasswordErrorException("旧密码不能与新密码一致");
        }
        //再次匹配两次密码输入是否一致
        if(sysUserUpdatePasswordVo.getNewPassword().equals(sysUserUpdatePasswordVo.getConfirmNewPassword())){

            throw new PasswordErrorException("两次密码不一致");
        }

        //最后，程序走到这里就可以完成修改,先把新密码加密
        sysUserUpdatePasswordVo.setNewPassword(Md5Util.encode(sysUserUpdatePasswordVo.getNewPassword()));

        boolean result = baseMapper.modifyPassword(sysUserUpdatePasswordVo);

        return result;
    }

    /**
     * 邮箱验证
     *
     * @param email
     * @param code
     */
    @Override
    public void sendEmail(String email, String code) {
        //验证码配置
        SimpleMailMessage message = new SimpleMailMessage();

        //设置按邮箱查询条件
        LambdaQueryWrapper<PuFaUser> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PuFaUser::getEmail, email);

        //先按邮箱查询用户,如果该邮箱有用户绑定,那么就是找回密码,验证码有效期5分钟
        PuFaUser puFaUser = baseMapper.selectOne(wrapper);
        System.out.println(puFaUser);

        //设置验证码时间,默认为注册,时间2分钟
        int yzmTime = 2;

        //判断用户是否为null,如果不是的话,就判断为注册用户信息验证码设置5分钟
        if (puFaUser != null) {

            yzmTime = 5;
        }

        //验证码标配提
        message.setSubject("普法学堂验证码");

        //邮箱接收信息
        message.setText("尊敬的:" + email + "用户,您的注册校验码为: " + code + "有效期" + yzmTime + "分钟");


        //发送给哪个邮箱
        message.setTo(email);

        //发送验证码的账号
        message.setFrom(username);

        //发送
        sender.send(message);

        //设置缓存的有效实体注册2分钟密码找回5分钟
        redisTemplate.opsForValue().set(email, code, yzmTime, TimeUnit.MINUTES);

    }

    //用户注册方法
    @Override
    public boolean addUser(PuFaUser puFaUser, String code) {

        //把验证码放入redis里面
        redisTemplate.opsForValue().set(puFaUser.getEmail(), code);

        //用户注册之前先查询一遍数据库，查看用户是否存在
        //使用wrapper条件按用户名查询用户
        LambdaQueryWrapper<PuFaUser> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PuFaUser::getUsername, puFaUser.getUsername());

        PuFaUser getByUsername = baseMapper.selectOne(wrapper);
        //在查询一遍邮箱
        LambdaQueryWrapper<PuFaUser> email = new LambdaQueryWrapper<>();

        email.eq(PuFaUser::getEmail, puFaUser.getEmail());

        PuFaUser getUserByEmail = baseMapper.selectOne(email);

        //用户名存在抛异常
        if (getByUsername != null) {

            throw new UsernameRepeatException("用户名已经存在,请不要重复注册");
        }
        if (getUserByEmail != null) {

            throw new UsernameRepeatException("该邮箱已经注册过,请不要重复注册");
        }


        System.out.println("邮箱" + puFaUser.getEmail());
        //再次进行邮箱验证
        String yzmCode = (String) redisTemplate.opsForValue().get(puFaUser.getEmail());


        System.out.println(yzmCode.equals(code));

        //如果邮箱验证码为null，则代表验证码过期
        if (yzmCode == null) {

            throw new YZMException("验证码已过期");
        }

        //如果验证码不正确
        if (!code.equals(yzmCode)) {

            throw new YZMException("验证码错误");
        }
        //走到这一步，就代表没有问题，可以注册,先给密码加密
        puFaUser.setPassword(Md5Util.encode(puFaUser.getPassword()));

        //如果用户没有上传头像,给定一个默认头像
        String avatar = ClassLoader.getSystemClassLoader().getResource("default/user/default.jpeg").getPath();

        puFaUser.setAvatar(avatar);
        //注册完成
        int insert = baseMapper.insert(puFaUser);

        System.out.println(puFaUser.getId());


        return insert > 0;
    }

    /**
     * 用户找回密码(设置新密码)
     *
     * @param code
     * @param vo
     * @return
     */
    @Override
    public boolean passwordRetrieval(String email, String code, SysUserUpdatePasswordVo vo) {

        //先查询缓存看看里面有没有验证码
        String yzmCode = (String) redisTemplate.opsForValue().get(email);

        //没有抛异常
        if (yzmCode == null) {

            throw new YZMException("验证码已过期");
        }
        //有的话比较验证码是否正确
        if (!yzmCode.equals(code)) {

            throw new YZMException("验证码不正确");
        }

        //在查询一遍邮箱
        LambdaQueryWrapper<PuFaUser> userByEmail = new LambdaQueryWrapper<>();

        userByEmail.eq(PuFaUser::getEmail, email);

        PuFaUser getUserByEmail = baseMapper.selectOne(userByEmail);


        if (userByEmail == null) {

            throw new EmailNotFoundException("该邮箱不存在");
        }

        if (!vo.getNewPassword().equals(vo.getOldPassword())) {

            throw new PasswordErrorException("两次密码不一致");
        }
        //程序走到这里就完成
        vo.setNewPassword(Md5Util.encode(vo.getNewPassword()));

        System.out.println(baseMapper);

        boolean result = baseMapper.passwordRetrieval(email, code, vo);


        return result;
    }

    /**
     * 用户修改个人信息
     *
     * @param puFaUser
     * @return
     */
    @Override
    public boolean updateByUserId(PuFaUser puFaUser) {

        int result = 0;

        if (puFaUser != null) {

            result = baseMapper.updateById(puFaUser);
        }
        return result > 0;
    }

}
