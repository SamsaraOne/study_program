package com.lx.msmservice.service;

import java.util.Map;

public interface MsmService {

    boolean sendMsm(String phone, Map<String, Object> param);
}
