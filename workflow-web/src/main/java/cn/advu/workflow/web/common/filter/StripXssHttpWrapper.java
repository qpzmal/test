package cn.advu.workflow.web.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Date: 13-7-18
 * To change this template use File | Settings | File Templates.
 */
class StripXssHttpWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @throws IllegalArgumentException if the request is null
     */
    public StripXssHttpWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String paramString) {
        String[] arrayOfString1 = super.getParameterValues(paramString);
        if (arrayOfString1 == null)
            return null;
        int i = arrayOfString1.length;
        String[] arrayOfString2 = new String[i];
        for (int j = 0; j < i; j++)
            arrayOfString2[j] = cleanXSS(arrayOfString1[j]);
        return arrayOfString2;
    }

    public String getParameter(String paramString) {
        String str = super.getParameter(paramString);
        if (str == null)
            return null;
        return cleanXSS(str);
    }


    private String cleanXSS(String paramString) {
        if (paramString == null)
            return "";
        String str = paramString;
        str = str.replaceAll("", "");
        Pattern localPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE);
        str = localPattern.matcher(str).replaceAll("");
        str = str.replaceAll("<", "").replaceAll("\\\\","").replaceAll("'","").replaceAll(";","").replaceAll(">", "").replaceAll("\"", "").replaceAll("=", "");
        return str;
    }
}
