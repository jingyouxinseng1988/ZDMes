package com.zhengda.platform.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;


public class MD5Utils {
    public static String md5(String text) {
        // 加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text);
        return encodeStr;
    }

    public static boolean verify(String text, String md5) {
        // 根据传入的密钥进行验证
        String md5Text = md5(text);
        if (md5Text.equalsIgnoreCase(md5)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        String liwujie = MD5Utils.md5("liwujie");
        System.out.println(liwujie);

//		String TARGET="56";
//		byte[] b = Base64.encodeBase64(TARGET.getBytes(), true);
//		String str = new String(b);
//		System.out.println("BASE64:     "+str);
//
//		//解密
//		byte[] b1 = Base64.decodeBase64(str);
//		System.out.println("解密之后内容为：  "+new String(b1));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));

    }
}
