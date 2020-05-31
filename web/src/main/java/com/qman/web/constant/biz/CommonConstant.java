package com.qman.web.constant.biz;

public class CommonConstant {

    public static final String BASE_URL = "http://103.89.85.32";

    public static final String K_AUTH_TOKEN = "auth_token";

    public static final String K_SECRET_CODE = "secret_code";

    public static final String K_PROFILE_INFO = "profile_info";

    public static final String K_ACTIVATION_TOKEN = "activation_token";

    public static final String K_RECOVER_TOKEN = "recover_token";

    public static final String THYMELEAF_EMAIL = "email";

    public static final String THYMELEAF_GREET_USER_FULL_NAME = "greet_name";

    public static final String THYMELEAF_RECOVER_PASS_URL = "recover_token_url";

    public static final String THYMELEAF_ACCOUNT_ACTIVATION_URL = "activation_email_url";

    public static final String RECOVER_PASS_URL_SCHEME = "/recover_password?" + K_RECOVER_TOKEN + "=";

    public static final String ACCOUNT_ACTIVATION_URL_SCHEME = "/account_activation?" + K_ACTIVATION_TOKEN + "=";

    public static final Integer DAY_TO_SECOND = 86400;

    public static final Integer WEEK_TO_SECOND = DAY_TO_SECOND * 7;

    public static final Long TRILLION = 1000000000000L;
}
