package cn.advu.workflow.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Created by kai on 16/5/4.
 */
public class FileMD5Tools {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileMD5Tools.class);

    public static String getMd5ByFile(File file)  {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            LOGGER.error("getMd5ByFile() ERROR", e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        return value;
    }



    public static void main(String[] args) throws IOException {

        String path = "/Users/kai/IdeaProjects/rmjyj/branches/doc/create_table.sql";

        String v = getMd5ByFile(new File(path));
        System.out.println("MD5:" + v.toUpperCase());


    }


}
