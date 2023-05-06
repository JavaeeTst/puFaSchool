package com.pufaschool.conn.utils;


import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {

    //过期时间  (毫秒值）
    private static final long EXPIRE = 7 * 24 * 60 * 60 * 1000;
    //秘钥 (约定的字符串） 或者叫签名
    private static final String SECRET = "onlyone";
    //秘钥前缀  （也是一个字符串，目的：进一步加强破解难度）
    private static final String SECRET_PREFFIX = "dmr";
    //颁布者
    private static final String SUBJECT = "dmr";


    /**
     * 操作1：生成 token  (也就是加密后的字符串）
     * 因为 token内部存放的是用户登陆信息，需要传递user对象
     *
     * @param user
     * @return
     */
    public static String generatorToken(PuFaUser user) {
        String token = Jwts.builder().setSubject(SUBJECT)  //设置颁布者
                .setIssuedAt(new Date())  //设置token 有效期的开始时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //过期时间
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .claim("avatar", user.getAvatar())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();//字符串紧凑  已char[] 数组类型得到该数据
        token = SECRET_PREFFIX + token;
        return token;
    }

    //操作2：解析 token
    public static Claims checkToken(String token) {

        //ctrl+alt+t
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(SECRET_PREFFIX, ""))
                    .getBody();
            return claims;
        } catch (MalformedJwtException e) {
            e.printStackTrace();

        }
        return null;
    }


}
