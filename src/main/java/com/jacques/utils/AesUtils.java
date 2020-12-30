package com.jacques.utils;

import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密
 *
 * @info AES加密
 * @version 1.0.0
 * @author Jacques·Fry
 * @date 2020/12/30 10:14
 */
public class AesUtils {

    private static final String AES = "AES";
    private static final String CHAR_SET_NAME1 = "UTF-8";
    private static final String CHAR_SET_NAME2 = "ASCII";
    private static final String CIPHER_KEY = "AES/CBC/PKCS5Padding";

    /**
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String S_KEY = "77acfdcdc7322c93";

    /**
     * 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，需要为16位。
     */
    private static String IV_PARAMETER = "2ea810d7555d09a0";


    /**
     * 加密
     *
     * @author: Jacques Fry
     * @date: 2020/12/21 11:23
     * @param param 需要被加密的数据
     * @return java.lang.String
     */
    public static String encryption(String param) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_KEY);
        SecretKeySpec sKeySpec = new SecretKeySpec(S_KEY.getBytes(), AES);
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
        // 此处使用BASE64做转码。
        return new BASE64Encoder().encode(cipher.doFinal(param.getBytes(CHAR_SET_NAME1)));

    }

    /**
     * 解密
     *
     * @author: Jacques Fry
     * @date: 2020/12/21 11:23
     * @param value 需要解密的数据
     * @return java.lang.String
     */
    public static String decrypt(String value) throws Exception {
        SecretKeySpec sKeySpec = new SecretKeySpec(S_KEY.getBytes(CHAR_SET_NAME2), AES);
        Cipher cipher = Cipher.getInstance(CIPHER_KEY);
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
        // 先用base64解密
        return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(value)), CHAR_SET_NAME1);
    }
}