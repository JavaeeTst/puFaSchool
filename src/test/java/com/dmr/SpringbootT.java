package com.dmr;

import com.pufaschool.PuFaSchoolApplication;
import com.pufaschool.conn.domain.PuFaCarousel;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.server.service.PuFaUserService;
import com.pufaschool.server.service.PuFsCarouselService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = PuFaSchoolApplication.class)
public class SpringbootT {

    @Autowired
    private PuFaUserService puFaUserService;
    @Autowired
    private StringRedisTemplate redis;

    @Value("${upload-path}")
    private String path;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PuFsCarouselService service;


    @Value("banner.image.location")
    private String banner;

    //    @Test
//    public void insert(){
//
//
//        for(int i=1;i<=20;i++){
//            PuFaUser puFaUser =new PuFaUser();
//            puFaUser.setUsername("test"+i);
//            puFaUser.setPassword("123456");
//            puFaUser.setEmail("534789@qq.com");
//            puFaUser.setRealName("测试数据"+i);
//            puFaUser.setTotalIntegration(40.0);
//            if(i==10){
//                puFaUser.setStatus(1);
//            }
//            boolean b = puFaUserService.addUser(puFaUser,null);
//
//            if (b) {
//                System.out.println("添加成功");
//            }
//        }
//    }
    @Test
    public void cxk(){

        System.out.println(banner);
    }
    @Test
    public void redisSet() {

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        PuFaUser byId = puFaUserService.getById(1);

        System.out.println(byId);


        valueOperations.set("byId", byId.toString());

    }

    @Test
    public void redisGet() {

        ValueOperations value = redis.opsForValue();

        PuFaUser user = (PuFaUser) value.get("byId");

        System.out.println(user);
    }

    @Test
    public void redisList() {

        ListOperations listOperations = redis.opsForList();


    }

    @Test
    public void redisDel() {

        ValueOperations value = redis.opsForValue();

        Boolean user = redis.delete("byId");

        System.out.println(user ? "删除成功" : "删除失败");
    }

    @Test
    public void linuxRedis() {
        ValueOperations<String, String> value = redisTemplate.opsForValue();

        value.set("name", "dmr");

        value.set("age", "21");

    }

    @Test
    public void file() {
        File file = new File(path + "avatar");

        String[] list = file.list();

        File[] files = file.listFiles();

        for (File file1 : files) {

            System.out.println(file1.getName());

        }

        List<PuFaUser> userAll = puFaUserService.getUserAll();

        System.out.println("===========================");

        for (PuFaUser puFaUser : userAll) {
            if (puFaUser.getAvatar() != null && puFaUser.getAvatar() != "") {
                String img = puFaUser.getAvatar().substring(puFaUser.getAvatar().lastIndexOf("/") + 1);

                System.out.println(img);
            }

        }


    }

    @Test
    public void getUser() {

        List<PuFaUser> list = puFaUserService.list();

        for (PuFaUser puFaUser : list) {

            String img = puFaUser.getAvatar().substring(puFaUser.getAvatar().lastIndexOf("/"));

            System.out.println(img);
        }
    }

    @Test
    public void getCarousel() {
        List<PuFaCarousel> list = service.getCarouselAll();

        PuFaCarousel puFaCarousel = list.get(0);

        System.out.println(puFaCarousel);

    }

    @Test
    public void stringRedis() {

        String role ="";
        //定义一个存头像的容器;
        List<String> avatar=new ArrayList<>();

        String url = "static/image/default/user";

        //如果是管理元,则对应的是管理员的头像
        if("ADMIN".equals(role) || "SUPER_ADMIN".equals(role)){

            url="static/image/default/admin";
        }

        //获取文件的路径
        String path = Thread.currentThread().getContextClassLoader().getResource(url).getPath();

        System.out.println(path);

        //拿到目录
        File file=new File(path);

        //遍历文件的所有头像
        File[] files = file.listFiles();


        if(files!=null){

            for (File file1 : files) {

                String path1 = file1.getPath();
                String substring = path1.substring(path1.lastIndexOf("/image"));
                System.out.println(substring);

            }
        }

    }
}
