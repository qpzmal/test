package cn.advu.workflow.common.constant;

/**
 * Created by kai on 15/8/5.
 */
public class Constants {




    public static class Login {

        public static final String LOGIN_COOKIE_KEY = "/index";

        public static final String LOGIN_URL = "/login/in";

        public static final String LOGIN_COOKIE_SPLIT = "\001";

        public static final String INDEX_URL = "/index";

        public static final String LOGIN_USER_ATTR_KEY = "loginuser";

        public static final String LOGIN_TOKEN_KEY = "captchaToken";

        public static final String NO_ACCESS_URL = "/login/noaccess";
        /**
         * cookie 30天
         */
        public static final Integer COOKIE_EXPIRY_30_DAYS = 30 * 24 * 60;

    }


}
