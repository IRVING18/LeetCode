package com.jiejiedai.api;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaTest {
    /**
     * 签名
     *
     * @author 王征
     * @param data
     * @param priKey
     * @return
     * @throws Exception
     */
    public static String sign(String data, String priKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        byte[] sign = signature.sign();
        return Base64.encodeBase64String(sign);
    }

    /**
     * 验签
     *
     * @author 王征
     * @param data
     * @param sign
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static boolean verify(String data, String sign, String publicKey) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(pubKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.verify(Base64.decodeBase64(sign));
    }

    private static final Integer MAX_ENCRYPT_BLOCK = 117;
    private static final Integer MAX_DECRYPT_BLOCK = 128;

    /**
     * 解密
     *
     * @author 王征
     * @param publicKey
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(PublicKey publicKey, String content) throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            byte[] contentBytes = Base64.decodeBase64(content);
            int len = contentBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] part;
            int i = 0;

            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            while (len - offSet > 0) {
                if (len - offSet > MAX_DECRYPT_BLOCK) {
                    part = cipher.doFinal(contentBytes, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    part = cipher.doFinal(contentBytes, offSet, len - offSet);
                }
                out.write(part, 0, part.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] output = out.toByteArray();
            out.close();
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * prikey:秘钥 去掉-----BEGIN RSA PRIVATE KEY------  ----END RSA PRIVATE KEY-----
     * pubkey:公钥 去掉-----BEGIN PUBLIC KEY-----   -----END PUBLIC KEY-----
     *
     * @author 王征
     * @param args
     */
    public static void main(String[] args) {
//        String priKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC7culAlFH+6vFKQKEuwKhLvIkQXc345vSXCLa31Xn8lc4eL5QcaluklAzYkms8FzwGmAtfEbvCM7l/qN4wRKVpwMQgTrfSMq+/HlKB+E2tdQbMyhBm81A3tjTBWm8ZI95HP2/S0npOGkHt0nnxmEmtRYulODttX320e/eFLm6UOGTVlKfVqCGp/PlgMt/SLLryquNyPm2dsSte4NXC404BTyR2tB8wRki/pn5uU+HbQoLqjftNDytGuO7p99pH9ZUVEXyMAXQZvsOlMQFHmB/uWy6v+tyY8+NYEHNrwTKwj/pPeJPMHHI7wKG8R0RpKrZWYX5Ku001xCH+Z3N3zqEpAgMBAAECggEAC2r8ekqOlBCLkhJNvyNNHQ/m6XBNU5P94hVNy/Tc8V3OqFgNlY2E94ltHuQMVCS2K1CnoKrb3QB5lCUdI6OKz95GFBG3cL5VpjaaoAAX+zDdsSu9xAeeI0aeAAkaK1Xb/EsI7LaVbJx815eyhdzY4A0UbIP4WrkuVwgWE664XZ7W4LblPj7yVU2atZVprULZXYwY6XoYbzsAcRrFdPjN0jjn3H23cHKouOLigOcx1UByEtCSlqo3DGPITo45gcTsV0r5TMh3zKrSiJ36qsyG9LB74DBU3UvBsTHQqaRqgBQp0esNSfPMoPXKgtEsuCdB+wh/uoEiZFHWIO5N2yymYQKBgQD4E1F5jTVzMih96ig3YCG6+8Ti/wi2XTl2HGY8jSY/ciprggd00JBMlxMuK3G7LJ/6ujHuN2AgBX55out3HhgBsphaS50qS01/e+YowbCn4lweOb5L1jsxpRYv+wrp1+8bHsqy92KwmE3+ME4jnHyMqdRbzJ5lNTYvYD/ujxR9OwKBgQDBb87a19ObNq4BKA3ImDrMouzFGDYbNPXu9+JSbcUWD3tKTx9U0PR/x2HYe7hrp9HGDlSFsdlCPPFOppC+e4mPhep5e0bAf8gE1qmLhO4Glw/1OnSsnb5m/irhvl3HRoBt7zZk3uqVud2jgf1DFk0QY4rn0KefUNQTRiSE0HRE6wKBgGylGUOgwk3nI7CxglduNJeNeBbqFsi3X1kI6wWN07hpqYZX9igEx42jhHTt9etBifbm4MMxYVnkzhU7cuBCP8VOkEbLYtOJEzHH827aFSIRksJyC5NvCZGeeW8eBROQzFkWTTPAAGrS6SVvxmj2Od3o6uYhYSV1/nsVLiquYwzNAoGAf5rjaYiRBkZG+WpT1W1e9JnR+Z8kKkSArHkH6vqQ1iG2YmnZsFj7wcNkr6vGF/aoMrBHX77YJJMRWKIWwCG8uFpOOH9zSA/DgjWduPUjFGPY2hwK1chlz4mB/lNXh9rhZCQ2zMolFEp9hJx+1x/CDLqt6ojB2x42GNTfyGG/IasCgYBoW86qs1osmpVFv1cByS2Qoc6UmBVVmaOKW2b300LwlUYkIERIByVM2c+Dk30PtkykvZTGcisXTtSNHMop6LbFJptd8kQ39POA3SJU4JgGQrAB2JNWw57aCCeUQEJ7uALaFDl5YpnY5dscmXbtWoa6DSrwiSU+tew5E1gNTRKX4w==";

//        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqrL3dl01ISretQcYfM0zKwe5ziENov9EY7iq9hjwjbzfhJ8QQIMWWjMROY4BkXAHDpIkrQLUMNRDsMtYj95dt5DO1MQtT2BgmhOJjBxdGaUE6NOIgdQnqzthCxJE1uiHo4PAX+pjsELkQ/Nk5ivW6VU92kyzOO1XzE/2Q7//8cQIDAQAB";
//        String sign = "U3bn0Eebsm/0PeotKOfvnx/8P/rS5yYLop7Sy0BsT+WS3d0G2WAGYQFAmnwiyVwIj1l+Adjje8MS6oMtBQtyvOXXSZcL/ttQGDw4YCKIdGy3WMTPM9dCT5hSmkrVCTwWsEzRvEh5ODUiCGUaRTv/XPLoRYLValtBgYBhNTIWpZg=";
        try {
//            String data = sign("wangzheng", priKey);

//            boolean data =  verify("wangzheng算法",sign,pubKey);
            System.out.println("222");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
