package cn.advu.workflow.common.utils;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtil.class);
    public static final Random RANDOM = new Random();

    public static String replaceBreak(String str, String htmlp,
                                      boolean filterBreak) {
        String start = String.format("<%s>", htmlp);
        String end = String.format("</%s>", htmlp);

        Pattern p = Pattern.compile("[\n-\r]\\s*");
        Matcher m = p.matcher(str.replaceAll("　", ""));

        String html = m.replaceAll(end + start);
        if (StringUtils.isEmpty(html)) {
            html = start + html + end;
        }

        if (filterBreak) {
            html = html.replaceAll(start + end, "");
        }
        return html;
    }

    /**
     * HTML特殊字符转换
     *
     * @param string
     * @param fillFlag 填充标示位, 在br后填充8个空格，用于下一段缩进
     * @return
     */
    public static String stringToHTMLString(String string, boolean fillFlag) {
        StringBuffer sb = new StringBuffer(string.length());
        // true if last char was blank
        boolean lastWasBlankChar = false;
        boolean newline = false; // 新行开始
        int len = string.length();
        char c;

        for (int i = 0; i < len; i++) { // 半角空格
            c = string.charAt(i);
            if (c == ' ') {
                // blank gets extra work,
                // this solves the problem you get if you replace all
                // blanks with &nbsp;, if you do that you loss
                // word breaking
                if (lastWasBlankChar) {
                    lastWasBlankChar = false;
                    sb.append("&nbsp;");
                } else {
                    lastWasBlankChar = true;
                    sb.append(' ');
                }
                if (newline && fillFlag) {
                    newline = false;
                }
            } else if (c == '　') { // 全角空格
                lastWasBlankChar = false;
                newline = false;
                sb.append('　');
            } else {
                lastWasBlankChar = false;
                if (newline && fillFlag) {
                    sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    newline = false;
                }
                //
                // HTML Special Chars
                if (c == '"')
                    sb.append("&quot;");
                else if (c == '&')
                    sb.append("&amp;");
                else if (c == '<')
                    sb.append("&lt;");
                else if (c == '>')
                    sb.append("&gt;");
                else if (c == '\n') {
                    // Handle Newline
                    sb.append("&lt;br/&gt;");
                    newline = true;
                } else {
                    int ci = 0xffff & c;
                    if (ci < 160)
                        // nothing special only 7 Bit
                        sb.append(c);
                    else {
                        // Not 7 Bit use the unicode system
                        sb.append("&#");
                        sb.append(new Integer(ci).toString());
                        sb.append(';');
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String getRandomInt(int num) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        int ibk = 0;
        for (int i = 0; i < num; i++) {
            ibk = rand.nextInt(10);
            if (ibk < 0) {
                ibk = ibk * -1;
            }
            sb.append(ibk);
        }
        return sb.toString();
    }

    public static double formatDouble(Double str) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return Double.parseDouble(df.format(str));
    }

    public static int getRandom(int num) {
        int rdm = RANDOM.nextInt(num);
        if (rdm < 0) {
            rdm = rdm * -1;
        }
        return rdm;
    }

    public static int getRandom() {
        int rdm = RANDOM.nextInt();
        if (rdm < 0) {
            rdm = rdm * -1;
        }
        return rdm;
    }


}
