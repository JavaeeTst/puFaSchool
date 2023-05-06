package com.pufaschool.conn.utils;

import java.security.MessageDigest;

public class Md5Util {
    private static final String SALT = "mayikt";

    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
//public static String encode(String plainText) {
//    byte[] secretBytes = null;
//    try {
//        secretBytes = MessageDigest.getInstance("md5").digest(
//                plainText.getBytes());
//    } catch (NoSuchAlgorithmException e) {
//        throw new RuntimeException("没有md5这个算法！");
//    }
//    String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
//    // 如果生成数字未满32位，需要前面补0
//    for (int i = 0; i < 32 - md5code.length(); i++) {
//        md5code = "0" + md5code;
//    }
//    return md5code;
//}


//    /**
//     * 解密
//     * @param inStr
//     * @return
//     */
//    public static String convertMD5(String inStr){
//
//        char[] a = inStr.toCharArray();
//        for (int i = 0; i < a.length; i++){
//            a[i] = (char) (a[i] ^ 't');
//        }
//        String s = new String(a);
//        return s;
//
//    }


    public static void main(String[] args) {
        System.out.println(Md5Util.encode("123456"));
    }
}
