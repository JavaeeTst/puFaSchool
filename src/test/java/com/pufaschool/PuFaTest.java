package com.pufaschool;

import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.server.dao.PuFaRoleDao;
import com.pufaschool.server.service.PuFaCourseWareService;
import com.sun.mail.smtp.DigestMD5;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
public class PuFaTest {

    @Autowired
    private PuFaCourseWareService wareService;

    @Autowired
    private PuFaRoleDao roleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setRoleDao(){

        List<PuFaRole> dmr = roleDao.findByUsernameOrUserId("dmr",null);

        System.out.println(dmr);

        PuFaRole puFaRole = new PuFaRole();
        puFaRole.setRoleCode("SUPER_ADMIN");
        puFaRole.setRoleName("super_admin");
        puFaRole.setDescription("超级管理员");
        System.out.println(dmr.contains(puFaRole));
    }


    @Test
    public void chunkUpload() throws Exception {

        //源文件
        File sourceFile=new File("E:\\testvideo\\test.mp4");

        //分块文件存储路径
        String chunkFilePath="E:\\testvideo\\test\\";



        //分块文件大小
        int chunkSize=1024*1024*1;

        //分块文件个数
        int chunkNum=(int)Math.ceil(sourceFile.length()*1.0/chunkSize);

        //使用流从源文件读数据,向分块文件中写数据
        RandomAccessFile raf_r = new RandomAccessFile(sourceFile, "r");

        //缓冲区
        byte[] bytes=new byte[1024];

        for (int i = 0; i <chunkNum ; i++) {

            //分块文件
            File chunkFile = new File(chunkFilePath);

            if(!chunkFile.exists()){

                chunkFile.mkdirs();
            }

            chunkFile=new File(chunkFilePath+i);


            //将分片写入
            RandomAccessFile rw = new RandomAccessFile(chunkFile, "rw");

            int len=-1;

            while ((len=raf_r.read(bytes))!=-1){

                rw.write(bytes,0,len);

                if(chunkFile.length()>=chunkSize){

                    break;
                }

            }
            rw.close();
        }

        raf_r.close();
    }

    //合并
    @Test
    public void merge() throws Exception {

        //快文件目录
        File chunkFolder =new File("E:\\testvideo\\test\\");

        //源文件
        File sourceFile=new File("E:\\testvideo\\test.mp4");

        //合并后的文件
        File mergeFile=new File("E:\\testvideo\\mergeFiles.mp4");

        //取出所有分块文件
        File[] files = chunkFolder.listFiles();


        if(files.length==0 || files==null){

            throw new FileNotFoundException("文件夹文null");
        }

        //把数组转成集合
        List<File> fileList = Arrays.asList(files);

        //在拿集合排序
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.valueOf(o1.getName())- Integer.valueOf(o2.getName());
            }
        });

        //向合并文件写的流
        RandomAccessFile rw = new RandomAccessFile(mergeFile, "rw");

        //缓存区
        byte[] bytes=new byte[1024];

        for (File file : fileList) {

            RandomAccessFile r = new RandomAccessFile(file, "r");

            int len=-1;
            while ((len=r.read(bytes))!=-1){

                rw.write(bytes,0,len);

            }
            r.close();

        }
        rw.close();

        FileInputStream fileInputStream = new FileInputStream(mergeFile);

        FileInputStream fileInputStream1 = new FileInputStream(sourceFile);

        String s = DigestUtils.md5DigestAsHex(fileInputStream);

        String s1 = DigestUtils.md5DigestAsHex(fileInputStream1);

        if(s.equals(s1)){

            System.out.println("文件合并完成");

            for (File file : files) {
                file.delete();
            }
        }

    }
    @Test
    public void dd(){
        PuFaCourseWare courseWareById = wareService.getCourseWareById(1658821510101516289l);

        System.out.println(courseWareById);
    }

    @Test
    public void redis(){

        redisTemplate.opsForValue().set("dmr","1234");
        Boolean dmr = redisTemplate.delete("dmr");
        System.out.println(dmr);
        System.out.println(redisTemplate.opsForValue().get("puFaIndex"));
    }
}
