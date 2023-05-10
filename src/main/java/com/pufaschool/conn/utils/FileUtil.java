package com.pufaschool.conn.utils;

import com.pufaschool.conn.exception.FileFormatException;
import com.pufaschool.conn.exception.FileOverFlowMaxException;
import com.pufaschool.conn.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类
 */
public class FileUtil {

    /**
     * 上传图片工具类封装
     *
     * @param files
     * @param upload
     * @param uploadMapper
     * @param url
     * @param imageFile
     * @return
     */
    public static Result imageFile(MultipartFile[] files, String upload, String uploadMapper, String url, String imageFile) {

        List<String> urlList = new ArrayList<>();

        String urlPath = null;



        for (MultipartFile file : files) {
            //截取文件后缀名
            String imgSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

            //如果文件不是jpg和png格式，抛异常
            if (!".jpg".equals(imgSuffix) && !".png".equals(imgSuffix) && !".jpeg".equals(imgSuffix)) {

                throw new FileFormatException("请上传jpg,png格式的文件");
            }

            //如果文件超过5mb，抛异常
            if (file.getSize() > 5000000) {
                throw new FileOverFlowMaxException("请不要上传大于5mb的文件");
            }
            //给文件取名字
            String img = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

            //新建一个file类存文件的位置
            File filepath = new File(upload + imageFile);

            System.out.println(filepath+"文件类");

            //判断是否有这个文件，有的话跳过，没有新建
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            try {
                //把文件写入磁盘里面
                file.transferTo(new File(filepath, img));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //把映射的路径给截取出来
            String mapperPath = uploadMapper.substring(uploadMapper.indexOf("/"), uploadMapper.lastIndexOf("/"));
            System.out.println(mapperPath);
            //拼接之后返回给前端
            urlPath = url + mapperPath + imageFile + "/" + img;

            System.out.println(urlPath);
            urlList.add(urlPath);
        }

        return Result.success(urlList);

    }

    /**
     * 视频工具类上传
     *
     * @param files
     * @param uploadPath
     * @param uploadMapper
     * @param url
     * @param videoFiles
     * @return
     */
    public static Result videoFile(MultipartFile[] files, String uploadPath, String uploadMapper, String url, String videoFiles) {

        List<String> path = new ArrayList<>();

        for (MultipartFile file : files) {

            //截取文件的后缀
            String videoSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            System.out.println(videoSuffix);

            //如果没有按照指定格式的文件上传,抛异常
            if (!".mp4".equals(videoSuffix) && !".mkv".equals(videoSuffix) && !".avi".equals(videoSuffix)) {

                throw new FileFormatException("仅支持MP4,AVI,MKV 格式的视频");
            }
            //文件大于1g,抛异常
            if (file.getSize() > 1000000000) {

                throw new FileOverFlowMaxException("文件请不不要大于1g的视频");
            }
            //程序走到这里,代表文件没有问题，符合规范,先加载路径
            File videoFile = new File(uploadPath + videoFiles);

            //先判断是否存在这个磁盘路径,不存在就创建
            if (!videoFile.exists()) {
                videoFile.mkdirs();
            }

            //图片名字
            String videoImgName = UUID.randomUUID() + videoSuffix;

            try {

                //把图片放入磁盘
                file.transferTo(new File(videoFile, videoImgName));
            } catch (IOException e) {

            }
            //截取映射路径
            String mapperPath = uploadMapper.substring(uploadMapper.indexOf("/"), uploadMapper.lastIndexOf("/"));

            //视频路径拼接(最终返回给前端)
            String imgPath = url + mapperPath + videoFiles + "/" + videoImgName;

            //先存放集合
            path.add(imgPath);

        }

        return Result.success(path);
    }

    /**
     * 课件上传工具类
     * @param files
     * @param uploadPath
     * @param uploadMapper
     * @param url
     * @param courseWareFiles
     * @return
     */
    public static Result courseWareFile(MultipartFile[] files,String uploadPath,String uploadMapper,String url,String courseWareFiles){

        List<String> path = new ArrayList<>();

        for (MultipartFile file : files) {

            //截取文件的后缀
            String courseWareSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            //如果没有按照指定格式的文件上传,抛异常
            if (!".ppt".equals(courseWareSuffix) &&!".pptx".equals(courseWareSuffix)) {

                throw new FileFormatException("仅支持ppt格式");
            }
            //文件大于10mb,抛异常
            if (file.getSize() > 10000000) {

                throw new FileOverFlowMaxException("文件请不不要大于10mb");
            }
            //程序走到这里,代表文件没有问题，符合规范,先加载路径
            File courseWareFile = new File(uploadPath + courseWareFiles);

            //先判断是否存在这个磁盘路径,不存在就创建
            if (!courseWareFile.exists()) {
                courseWareFile.mkdirs();
            }

            //图片名字
            String courseWareImgName = UUID.randomUUID() + courseWareSuffix;

            try {

                //把课件放入磁盘
                file.transferTo(new File(courseWareFile, courseWareImgName));
            } catch (IOException e) {

            }
            //截取映射路径
            String mapperPath = uploadMapper.substring(uploadMapper.indexOf("/"), uploadMapper.lastIndexOf("/"));

            //课件路径拼接(最终返回给前端)
            String imgPath = url + mapperPath + courseWareFiles + "/" + courseWareImgName;

            //先存放集合
            path.add(imgPath);

        }

        return Result.success(path);
    }

    /**
     * 分片上传
     */
    public static void chunkUpload(String videoFile,String chunkFile) throws Exception {

        //源文件
//        File sourceFile=new File("E:\\testvideo\\test.mp4");
        File sourceFile=new File(videoFile);


        //分块文件存储路径
        String chunkFilePath=chunkFile;

//        String chunkFilePath="E:\\testvideo\\test";

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
            File chunkFile2 = new File(chunkFilePath + i);

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


}
