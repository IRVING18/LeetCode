package com.jiejiedai.api;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;

public class AESUtil {

    /**
     * AES 加密
     * @param content
     * @param key
     * @param iv
     * @return
     */
    public static String encrypt(String content, String key, String iv) {
        byte[] originalContent = content.getBytes();
        byte[] encryptKey = key.getBytes();
        byte[] ivByte = iv.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(ivByte));
            byte[] encrypted = cipher.doFinal(originalContent);
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     * @param content
     * @param key
     * @param iv
     * @return
     */
    public static String decrypt(String content, String key, String iv) {
        byte[] contentByte = Base64.decodeBase64(content);
        byte[] keyByte = key.getBytes();
        byte[] ivByte = iv.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(contentByte);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // 生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }


    public static void main(String[] args) {
        String s1 = encrypt("13249867541", "9C6DA40CFE6D6EAA", "3343F01D45F3C60C");
        String s2 = decrypt("uNOAhn+uhDtuqE6eP0dbrQ==", "9C6DA40CFE6D6EAA", "3343F01D45F3C60C");

        System.out.println(s1);
        System.out.println(s2);

    }

}