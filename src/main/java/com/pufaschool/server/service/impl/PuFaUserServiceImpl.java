package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.BaseEntity;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.vo.EmailVo;
import com.pufaschool.conn.domain.vo.SysUserAttributeVo;
import com.pufaschool.conn.exception.*;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.conn.utils.LogUtil;
import com.pufaschool.conn.utils.RoleUtil;
import com.pufaschool.server.dao.PuFaUserDao;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import com.pufaschool.conn.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务实现层
 */
@Service
public class PuFaUserServiceImpl extends ServiceImpl<PuFaUserDao, PuFaUser> implements PuFaUserService {
    /**
     * 发送邮箱对象
     */
    @Resource
    private JavaMailSender sender;

    /**
     * 缓存对象
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${url-pattern}")
    private String url;

    /**
     * 发送验证码的邮箱账号
     */
    @Value("${spring.mail.username}")
    private String username;
    /**
     * 角色业务层对象
     */
    @Autowired
    private PuFaRoleService roleService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public PuFaUser login(String username, String password) {

        //先查询看看有没有用户
        PuFaUser userByUsernameAndPassword = baseMapper.findUserByUsernameAndPassword(username, Md5Util.encode(password));

        //如果没有查询到抛异常
        if (userByUsernameAndPassword == null) {

            throw new UsernameNotFoundExceptions("用户名或者密码错误");
        }

        //如果用户的状态为1，则该用户被冻结了
        if (userByUsernameAndPassword.getStatus() == 1) {

            throw new UsernameFreezeException("该用户已被冻结,请联系管理员");
        }

        //程序走到这里没有问题
        return userByUsernameAndPassword;
    }


//    //用户注册方法
//    @Override
//    public boolean addUser(PuFaUser puFaUser,String code) {
//
//        //测试
//        redisTemplate.opsForValue().set(puFaUser.getEmail(),"123456");
//        //用户注册之前先查询一遍数据库，查看用户是否存在
//        //使用wrapper条件按用户名查询用户
//        LambdaQueryWrapper<PuFaUser> wrapper=new LambdaQueryWrapper<>();
//
//        wrapper.eq(PuFaUser::getUsername, puFaUser.getUsername());
//
//        PuFaUser getByUsername = baseMapper.selectOne(wrapper);
//        //在查询一遍邮箱
//        Object userByEmail =  getUserByEmail(puFaUser.getEmail());
//
//        //用户名存在抛异常
//        if(getByUsername!=null){
//
//            throw new UsernameRepeatException("用户名已经存在,请不要重复注册");
//        }
//        if(userByEmail!=null){
//
//            throw new UsernameRepeatException("该邮箱已经注册过,请不要重复注册");
//        }
//
//
//        System.out.println("邮箱"+puFaUser.getEmail());
//        //再次进行邮箱验证
//        String yzmCode = (String) redisTemplate.opsForValue().get(puFaUser.getEmail());
//
//
//
//
//        System.out.println(yzmCode.equals(code));
//
//        //如果邮箱验证码为null，则代表验证码过期
//        if(yzmCode==null){
//
//            throw new YZMException("验证码已过期");
//        }
//
//        //如果验证码不正确
//        if(!code.equals(yzmCode)){
//
//            throw new YZMException("验证码错误");
//        }
//        //走到这一步，就代表没有问题，可以注册,先给密码加密
//        puFaUser.setPassword(Md5Util.encode(puFaUser.getPassword()));
//
//
//
//        int insert = baseMapper.insert(puFaUser);
//
//        System.out.println(puFaUser.getId());
//
//
//        return insert>0;
//    }

//    //修改用户方法
//    @Override
//    public boolean updateByUserId(PuFaUser puFaUser) {
//
//        int result=0;
//
//        if(puFaUser !=null){
//
//            result = baseMapper.updateById(puFaUser);
//        }
//        return result>0;
//    }

    /**
     * 按id删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteByUserId(Long id) {

        //删除之前先看看用户是否被禁用
        PuFaUser byId = this.getById(id);

        //如果被禁用则删除失败
        if (byId.getStatus()==1) {

            throw new UserErrorException("该用户已经被禁用,无法删除");
        }

        return this.removeById(id);
    }

    //    /**
//     * 用户修改密码
//     * @param sysUserVo
//     * @return
//     */
//    @Override
//    public boolean updatePassword(SysUserUpdatePasswordVo sysUserVo) {
//        //使用md5加密旧密码比对数据库
//        String oldPassword = Md5Util.encode(sysUserVo.getOldPassword());
//
//        //在查询数据库的密码，使用wrapper条件对象，username为查询条件
//        LambdaQueryWrapper<PuFaUser> wrapper=new LambdaQueryWrapper<>();
//
//        wrapper.eq(PuFaUser::getUsername,sysUserVo.getUsername());
//
//        PuFaUser puFaUser = baseMapper.selectOne(wrapper);
//
//        //判断旧密码是否与数据库密码一致
//        if(!puFaUser.getPassword().equals(oldPassword)){
//
//            throw new PasswordErrorException("原密码不正确");
//        }
//        //程序走到这里，再次匹配旧密码与新密码是否一致
//        if (sysUserVo.getNewPassword().equals(sysUserVo.getOldPassword())) {
//
//            throw new PasswordErrorException("旧密码不能与新密码一致");
//        }
//        //最后，程序走到这里就可以完成修改,先把新密码加密
//        sysUserVo.setNewPassword(Md5Util.encode(sysUserVo.getNewPassword()));
//
//        boolean result = baseMapper.modifyPassword(sysUserVo);
//
//        return result;
//    }
    //按状态查询用户
    @Override
    public List<PuFaUser> getUserByStatus(Integer status) {

        //如果传过来的值为null，默认查询未禁用的用户
        List<PuFaUser> userByStatus = baseMapper.findUserByStatus(status != null ? status : 0);

        return userByStatus;
    }

    /**
     * 分页查询用户
     *
     * @param page
     * @return
     */
    @Override
    public IPage<PuFaUser> getPageList(Page<PuFaUser> page) {

        IPage<PuFaUser> sysUserPage = baseMapper.selectPage(page, null);


        return sysUserPage;
    }

    /**
     * 模糊查询用户
     *
     * @param vo
     * @return
     */
    @Override
    public List<PuFaUser> getByUserFiled(SysUserUpdatePasswordVo vo) {

        List<PuFaUser> byUserFiled = baseMapper.findByUserFiled(vo);

        return byUserFiled;
    }

//    /**
//     * 邮箱验证
//     * @param email
//     * @param code
//     */
//    @Override
//    public void sendEmail(String email, String code) {
//        //验证码配置
//        SimpleMailMessage message=new SimpleMailMessage();
//
//        //设置按邮箱查询条件
//        LambdaQueryWrapper<PuFaUser> wrapper=new LambdaQueryWrapper<>();
//
//        wrapper.eq(PuFaUser::getEmail,email);
//
//        //先按邮箱查询用户,如果该邮箱有用户绑定,那么就是找回密码,验证码有效期5分钟
//        PuFaUser puFaUser = baseMapper.selectOne(wrapper);
//        System.out.println(puFaUser);
//
//         //设置验证码时间,默认为注册,时间2分钟
//        int yzmTime=2;
//
//        //判断用户是否为null,如果不是的话,就判断为注册用户信息验证码设置5分钟
//        if(puFaUser!=null){
//
//            yzmTime=5;
//        }
//
//        //验证码标配提
//        message.setSubject("普法学堂验证码");
//
//        //邮箱接收信息
//        message.setText("尊敬的:"+email+"用户,您的注册校验码为: "+code+"有效期"+yzmTime+"分钟");
//
//
//        //发送给哪个邮箱
//        message.setTo(email);
//
//        //发送验证码的账号
//        message.setFrom(username);
//
//        //发送
//        sender.send(message);
//
//        //设置缓存为5分钟
//        redisTemplate.opsForValue().set(email,code,yzmTime, TimeUnit.MINUTES);
//
//    }

//    /**
//     * 用户找回密码
//     * @param code
//     * @param vo
//     * @return
//     */
//    @Override
//    public boolean passwordRetrieval(String email,String code, SysUserUpdatePasswordVo vo) {
//
//        //先查询缓存看看里面有没有验证码
//        String yzmCode =(String) redisTemplate.opsForValue().get(email);
//
//        //没有抛异常
//        if(yzmCode==null){
//
//            throw new YZMException("验证码已过期");
//        }
//        //有的话比较验证码是否正确
//        if(!yzmCode.equals(code)){
//
//            throw  new YZMException("验证码不正确");
//        }
//
//        //在看看邮箱是否正确
//        Object userByEmail =  getUserByEmail(email);
//
//        if(userByEmail==null){
//
//            throw new EmailNotFoundException("该邮箱不存在");
//        }
//
//        if(!vo.getNewPassword().equals(vo.getOldPassword())){
//
//            throw new PasswordErrorException("两次密码不一致");
//        }
//        //程序走到这里就完成
//        vo.setNewPassword(Md5Util.encode(vo.getNewPassword()));
//
//        boolean result = baseMapper.passwordRetrieval(email, code, vo);
//
//
//        return result;
//    }

    /**
     * 按邮箱查询用户
     *
     * @param email
     * @return
     */
    @Override
    public Object getUserByEmail(String email) {

        PuFaUser getUserByEmail = (PuFaUser) redisTemplate.opsForValue().get("getUserByEmail");

        if (getUserByEmail == null) {
            LambdaQueryWrapper<PuFaUser> wrapper = new LambdaQueryWrapper<>();

            wrapper.eq(PuFaUser::getEmail, email);

            PuFaUser puFaUser = baseMapper.selectOne(wrapper);

            redisTemplate.opsForValue().set("getUserByEmail", puFaUser);

            getUserByEmail = (PuFaUser) redisTemplate.opsForValue().get("getUserByEmail");
        }

        return getUserByEmail;
    }

    /**
     * 按创建时间查询用户
     *
     * @param date
     * @return
     */
    @Override
    public List<PuFaUser> getUserByCreateTime(String date) {

        LambdaQueryWrapper<PuFaUser> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BaseEntity::getCreateTime, date);

        List<PuFaUser> userByCreateTime = baseMapper.findUserByCreateTime(date);

        return userByCreateTime;
    }

    /**
     * 按id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public PuFaUser getUserById(Long id) {

        PuFaUser puFaUser = baseMapper.selectById(id);

        return puFaUser;
    }

    /**
     * 用户自己查询自己的个人信息按id
     *
     * @param request
     * @return
     */
    @Override
    public PuFaUser getUserByUserId(HttpServletRequest request) {

        //先获取token
        String token = request.getHeader("token");

        //如果为null,代表用户没有登录
        if (token == null) {

            throw new UsernameNotFoundExceptions("请先登录");
        }

        //取出token里面的用户id
        Long userId = (Long)JWTUtils.checkToken(token).get("userId");


        //然后查询
        PuFaUser userById = this.getUserById(userId);


        return userById;
    }

    /**
     * 获取所有用户(包括删除的)
     *
     * @return
     */
    @Override
    public List<PuFaUser> getUserAll() {

        List<PuFaUser> userAll = baseMapper.findUserAll();

        return userAll;
    }

    /**
     * 查询所有的管理员(超级管理员)
     *
     * @return
     */
    @Override
    public List<PuFaUser> getAdminAll() {

        List<PuFaUser> adminAll = baseMapper.findAdminAll();


        return adminAll;
    }

    /**
     * 查询用户,按照头像查询(清理垃圾图片专用)
     *
     * @param avatar
     * @return
     */
    @Override
    public PuFaUser getUserByAvatar(String avatar) {

        PuFaUser userByAvatar = baseMapper.findUserByAvatar(avatar);

        return userByAvatar;
    }

    /**
     * 冻结(启用)用户
     *
     * @param status
     * @return
     */
    @Override
    public boolean updateUserStatus(Integer status, Long userId,HttpServletRequest request) {

        //取出自己的id
        Long selfId = Long.valueOf((Long) JWTUtils.checkToken(request.getHeader("token")).get("userId"));

        //在查询自己的角色
        List<PuFaRole> roleCode = (List<PuFaRole>) redisTemplate.opsForValue().get("roleCode");

        //如果自己不是超级管理员
        if(!roleCode.contains("SUPER_ADMIN")){

            //在看看要冻结用户的角色
            List<PuFaRole> roleByUsernameOrUserId = roleService.getRoleByUsernameOrUserId(null, userId);

            //如果被冻结用户是管理员就抛异常
            if(roleByUsernameOrUserId.contains("ADMIN")){

                throw new InsufficientAuthorityException("您没有权限禁用(启用)管理员");
            }

        }
        //如果禁用自己抛异常
        if (selfId.equals(userId)) {

            throw new UserErrorException("请不要禁用自己");
        }

        boolean result = baseMapper.modifyUserStatus(status, userId);

        return result;
    }

    /**
     * 用户查询默认头像
     */
    @Override
    public List<String> getUserAndAdminDefaultAvatar(String role) {

        return null;
    }

    /**
     * 按用户名查询用户
     * @param username
     * @return
     */
    @Override
    public PuFaUser getUserByUsername(String username) {

        LambdaQueryWrapper<PuFaUser> wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(PuFaUser::getUsername,username);

        wrapper.eq(PuFaUser::getStatus,0);

        PuFaUser getUserByUsername = this.getOne(wrapper);

        return getUserByUsername;
    }

    /**
     * 查询被删除的用户
     * @return
     */
    @Override
    public List<PuFaUser> getDeleteUser() {

        List<PuFaUser> deleteUser = baseMapper.findDeleteUser();

        return deleteUser;
    }

    /**
     * 按id查询被删除的用户
     * @param deleteId
     * @return
     */
    @Override
    public PuFaUser getUserByDeleteId(Long deleteId) {

        LambdaQueryWrapper<PuFaUser> wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(BaseEntity::getIsDelete, LogUtil.CONN_NO_CODE);

        wrapper.eq(BaseEntity::getId,deleteId);

        PuFaUser byId = this.getOne(wrapper);

        return byId;
    }

    /**
     * 用户邮箱修改
     * @param
     * @param
     * @return
     */

    @Override
    public boolean updateUserEmailByUserId(EmailVo vo) {

        //取出验证码
        String oldYzmCode = (String) redisTemplate.opsForValue().get(vo.getOldEmail());

        //在使用验证码比对，不一样就抛异常
        if (!vo.getOldCode().equals(oldYzmCode)) {

            throw new YZMException("验证码错误");
        }
        //程序走到这里,说明验证码是正确的，在拿新邮箱和老邮箱比对一下
        if (vo.getNewEmail().equals(vo.getOldCode())) {

            throw new EmailExistException("新邮箱不能和老邮箱一样");
        }

        //在取出新邮箱验证码
        String newYzmCode = (String) redisTemplate.opsForValue().get(vo.getNewCode());

        //在比对新验证码
        if (!newYzmCode.equals(vo.getNewCode())) {

            throw new YZMException("验证码错误");
        }
        //程序走到这里就没有问题
        boolean result = baseMapper.modifyUserEmailByUserId(vo);

        return result;
    }

    /**
     * 按用户属性查询用户
     * @param vo
     * @return
     */
    @Override
    public List<PuFaUser> getUserByUserAttribute(SysUserAttributeVo vo) {

        List<PuFaUser> userByUserAttribute = baseMapper.findUserByUserAttribute(vo);

        return userByUserAttribute;
    }

}
