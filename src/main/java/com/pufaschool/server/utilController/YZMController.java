package com.pufaschool.server.utilController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@Api(tags = "验证码")
@RequestMapping("system/puFaSchool")
public class YZMController {
    //验证码的高度
    private int height = 30;
    //验证码的宽度
    private int width = 150;
    //验证码的数值
    private String[] values = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x"
            , "y", "j", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "J",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    //验证码的数量
    private int num = 6;
    //定义间隔坐标
    private int space = 18;

    @GetMapping("/code")
    @ApiOperation(value = "验证码接口", notes = "使用方法，使用图片的src路径发送请求,例如:src='验证码接口'")
    public void ymz(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //创建一个画板
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics graphics = image.getGraphics();

        //设置画笔颜色

        graphics.setColor(Color.white);

        //给画板涂色

        graphics.fillRect(0, 0, width, height);

        //给画板写内容

        //字体风格(字体类型,斜体,大小)
        Font font = new Font("宋体", Font.ITALIC, 24);
        //设置字体给画笔
        graphics.setFont(font);

        graphics.setColor(Color.blue);
        //参数:文字，x，y坐标
        int ran = 0;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            ran = new Random().nextInt(values.length);
            graphics.setColor(getColor());
            buffer.append(values[ran]);
            graphics.drawString(values[ran], (i + 1) * space, 24);
            graphics.drawLine(getLine()[0], getLine()[1], getLine()[2], getLine()[3]);

        }

        String s = buffer.toString().toUpperCase();

        System.out.println(s);

        request.getSession().setAttribute("code", s);
        //设置验证码的缓存,不需要缓存
        response.setHeader("Pragma", "no-cache");

        response.setHeader("Cache-Control", "no-cache");

        response.setDateHeader("Expires", 0);


        //将画板写入响应体
        response.setContentType("image/png");

        ServletOutputStream outputStream = response.getOutputStream();


        ImageIO.write(image, "png", outputStream);

        outputStream.flush();

        outputStream.close();
    }

    //颜色方法
    private Color getColor() {
        int a = new Random().nextInt(255);
        int b = new Random().nextInt(255);
        int c = new Random().nextInt(255);

        return new Color(a, b, c);
    }

    //坐标维度方法
    private int[] getLine() {

        int x1 = new Random().nextInt(width);

        int y1 = new Random().nextInt(height);

        int x2 = new Random().nextInt(width);

        int y2 = new Random().nextInt(height);

        return new int[]{x1, y1, x2, y2};
    }
}
