package cn.advu.workflow.common.utils.md5;

import cn.advu.workflow.common.exception.IWanviException;
import cn.advu.workflow.common.utils.ImportExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 字符串MD5加密
 * @author DIKEPU
 *
 */
public class StrMD5 {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImportExcelUtil.class);
	private static StrMD5 instance;

	private static Object keyObject = new Object();

	private StrMD5() {
	}

	public static StrMD5 getInstance() {
		if (instance == null) {
			synchronized (keyObject) {
				if (null == instance)
					instance = new StrMD5();
			}
		}
		return instance;
	}

	/**
	 * 加密字符串
	 * @param key
	 * @return
	 */
	public String getStringMD5(String key) {
		try {
			MessageDigest currentAlgorithm = MessageDigest.getInstance("MD5");
			return computeDigest(currentAlgorithm, loadBytes(key));
		} catch (NoSuchAlgorithmException e) {
			throw new IWanviException("MD5 algorithm not available.", e);
		}
	}

	/**
	 * 接口调用签名, 响应内容签名
	 * 
	 * @author rainyhao
	 * @since 2015-3-10 上午10:01:53
	 * @param args
	 *            签名内容, 按签名参数顺序传入, 自动把空值替换成空字符串
	 * @return
	 */
	public String signature(Object... args) {
		String signtext = "";
		for (int i = 0; i < args.length; i++) {
			signtext += null == args[i] ? "" : args[i];
		}
		return getStringMD5(signtext);
	}

	public String signWithCharset(String charset, Object... args) {
		String signtext = "";
		for (int i = 0; i < args.length; i++) {
			signtext += null == args[i] ? "" : args[i];
		}
		return getMD5Str(signtext, charset);
	}

	/**
	 * 加密密码
	 * 
	 * @author rainyhao
	 * @since 2015-3-19 上午10:25:19
	 * @param pwd
	 *            明文
	 * @param salt
	 *            附加加密字符串
	 * @return
	 */
	public String encrypt(String pwd, String salt) {
		String first = getStringMD5(pwd + salt);
		String second = getStringMD5(first);
		String third = getStringMD5(second + salt);
		return third;
	}

	public String getMD5Str(String key, String charset) {
		try {
			MessageDigest currentAlgorithm = MessageDigest.getInstance("MD5");
			return computeDigest(currentAlgorithm, loadBytes(key, charset));
		} catch (NoSuchAlgorithmException e) {
			throw new IWanviException("MD5 algorithm not available.", e);
		}
	}

	static private byte[] loadBytes(String name) {
		byte[] buffer = name.getBytes();
		return (buffer);
	}

	static private byte[] loadBytes(String name, String charset) {
		byte[] buffer = null;
		try {
			buffer = name.getBytes(charset);
		} catch (Exception e) {
		}

		return (buffer);
	}

	private String computeDigest(MessageDigest currentAlgorithm, byte[] b) {
		currentAlgorithm.reset();
		currentAlgorithm.update(b);
		byte[] hash = currentAlgorithm.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hash.length; i += 2) { // format with 2-byte
													// words with spaces.
			sb.append(getHexString(hash[i]));
			sb.append(getHexString(hash[i + 1]));
		}
		return sb.toString().trim().toLowerCase();
	}

	private String getHexString(byte value) {
		int usbyte = value & 0xFF;// byte-wise AND converts signed byte to
									// unsigned.
		StringBuilder sb = new StringBuilder();
		if (usbyte < 16)
			sb.append("0");
		sb.append(Integer.toHexString(usbyte));// pad on left if single hex
												// digit.
		return sb.toString();
	}

    public static void main(String[] args) {
        String md5Pass = StrMD5.getInstance().encrypt("111111", "advu"); // 111111-advu:f7ad7cc1c8ee8431b0a8c3abb872a33c
        md5Pass = StrMD5.getInstance().encrypt("123456", "advu"); // 123456-advu:93d6f2648c6f590a7bc8cd0be9243149

        System.out.println(md5Pass);
    }
}
