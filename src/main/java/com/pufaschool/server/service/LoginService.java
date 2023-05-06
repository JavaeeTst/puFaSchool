package com.pufaschool.server.service;

import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.result.Result;

public interface LoginService {

    Result login(PuFaUser puFaUser);
}
