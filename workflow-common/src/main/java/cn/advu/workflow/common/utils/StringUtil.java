package cn.advu.workflow.common.utils;

import java.util.Random;

/**
 * Created by weiqz on 2017/8/17.
 */
public class StringUtil {
    private static final Random RANDOM = new Random();
    private static final int BASIC_NUM = 100000;

    public static String getRandom6Str() {
        return (RANDOM.nextInt(900000) + BASIC_NUM) + "";
    }

}
