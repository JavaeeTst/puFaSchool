package com.pufaschool.conn.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码加密器
 */
@Component
public class PasswordEncodingConfig implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return Md5Util.encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(Md5Util.encode(charSequence.toString()));
    }
}
