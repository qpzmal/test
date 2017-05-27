package cn.advu.workflow.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtil {

    public static String uuidToBase64(UUID uuid) {
		Base64 base64 = new Base64();
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return new String(base64.encode(bb.array()));
	}

	public static String uuidFromBase64(String str) {
		byte[] bytes = Base64.decodeBase64(str.getBytes());
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		UUID uuid = new UUID(bb.getLong(), bb.getLong());
		return uuid.toString();
	}

	public static String generate24UUID() {
		UUID uuid = UUID.randomUUID();
		return uuidToBase64(uuid);
	}

	public static String generateUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-","");
	}
	public static void main(String args[]) {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String shortUUID = uuidToBase64(uuid);
		System.out.println(shortUUID);
		System.out.println(uuidFromBase64(shortUUID));

	}
}
