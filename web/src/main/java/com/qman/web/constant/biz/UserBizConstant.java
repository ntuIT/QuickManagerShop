package com.qman.web.constant.biz;

public class UserBizConstant {

    public static final String KEY_ACCOUNT_ID = "k_account_id";

    public static final String KEY_PASSWORD_EXPIRED_DAYS = "password.experied.days";

    public static final String DEFAULT_SECRET_CODE = "000000";

    // contain only alphabet
    public static final String VALIDATION_ID_CODE = "^[0-9]{9}";

    // contain only alphabet
    public static final String VALIDATION_PHONE = "^[0-9]{10,11}";

    // contain only alphabet
    public static final String VALIDATION_HUMAN_NAME = "[^\u0000-\u007F]{1,20}";

    // contain number, alphabet, length 5-10:
    public static final String VALIDATION_USERNAME = "^[0-9a-zA-Z]{5,10}";

    // https://howtodoinjava.com/regex/java-regex-validate-email-address/
    // 5. Regex to restrict no. of characters in top level domain [Recommended]
    public static final String VALIDATION_EMAIL_REGEX
        = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    // 6-10 characters, at least one letter and one number:
    public static final String VALIDATION_PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";

    // 6 digits from 0 - 9:
    public static final String VALIDATION_SECRET_CODE_REGEX = "^(\\d){6}$";

}
