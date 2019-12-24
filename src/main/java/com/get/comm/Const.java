package com.get.comm;

import org.springframework.stereotype.Component;

@Component
public class Const {

    // 加密密码
    public static final String EN_PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

    // 加密cookie
    public static final String EN_COOKIE_KEY = "UI##$$&&()(%^@!)(";


    public static final String LOGIN_SESSION_KEY = "Get_user";

    public static String DES3_KEY = "9964DYByKL967c3308imYtCB";

    public static int COOKIE_TIMEOUT = 30 * 24 * 60 * 60;
}
