package cn.advu.workflow.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kai on 16/8/3.
 */
public class RegexUtil {

    public static boolean checkSearchRegex(CharSequence param) {
        Pattern p = Pattern.compile("[^%$&@><'\"\\+|]{1,}");
        Matcher m = p.matcher(param);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(RegexUtil.checkSearchRegex("+"));
    }

}
