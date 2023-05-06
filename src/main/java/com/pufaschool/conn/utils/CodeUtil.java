package com.pufaschool.conn.utils;

import java.util.Random;

/**
 * 生成验证码
 */
public class CodeUtil {

    public static String code(Integer num) {

        StringBuffer s = new StringBuffer();

        for (int i = 0; i < num; i++) {

            int ran = new Random().nextInt(10);

            s.append(ran);
        }

        return s.toString();
    }
}
