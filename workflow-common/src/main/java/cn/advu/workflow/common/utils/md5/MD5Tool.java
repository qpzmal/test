package cn.advu.workflow.common.utils.md5;


import java.security.NoSuchAlgorithmException;

public class MD5Tool {

	public final static String ENCRYPT_PW_KEY = "key_ikanshu_cn_201507";
	public final static String ENCRYPT_PW_KEY_cxb = "iks_aI77uzUjV@sRnKL&_20160512";
	/**
	 * 将密码用MD5加密
	 *
	 * @param pwd
	 *            密码
	 * @return
	 */
	public static String md5Encode(String pwd, String encoding) {
		return MD5Util.MD5Encode( pwd,encoding);
	}

	/**
	 * 将密码用MD5加密
	 * @param pwd
	 *            密码
	 * @return
	 */
	public static String md5Encode(String pwd) {
		return MD5Util.MD5Encode( pwd);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String key="cnid=3000&uid=949323&imsi=123&imei=123&cnsubid=0&umeng=1&version=4.0.0&oscode=3&model=4&other=5&bid=236353&id=442663key_ikanshu_cn_201507";
		System.out.println(md5Encode(key,"UTF-8"));

	}

	public static String md5EncodeCxb(String pwd) {
		return MD5Util.MD5Encode( pwd +ENCRYPT_PW_KEY_cxb);
	}
	public static String md5EncodePw(String pwd) {
		return MD5Util.MD5Encode( pwd +ENCRYPT_PW_KEY);
	}
}
