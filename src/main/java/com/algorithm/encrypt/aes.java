package com.algorithm.encrypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author:
 * @Description:
 * @Date: Created in 2:42 PM 1/22/18
 * @Modified by:
 */
public class aes {
    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] encrytor(String str, String password) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES"); //创建AES的key生产者

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        kgen.init(128, random);
        //kgen.init(128, new SecureRandom(password.getBytes()));//利用用户密码u作为随机数初始化，password.getBytes()是种子
        SecretKey secretKey = kgen.generateKey();//生成密钥

        byte[] enCodeFormat = secretKey.getEncoded();//返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");//转换为AES专用密钥
        Cipher cipher = Cipher.getInstance("AES");//创建密码器

        byte[] byteContent = str.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);//初始化为加密模式的密码器
        byte[] result = cipher.doFinal(byteContent);//加密
        return result;
    }

    /**
     * 对字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decryptor(byte[] buff, String password) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        kgen.init(128, random);
        //kgen.init(128, new SecureRandom(password.getBytes()));

        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] result = cipher.doFinal(buff);
        return result; // 加密
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        // write your code here
        String content="www.buglife.cn";
        //秘钥
        String password = "buglife-secret";
        aes aestemp = new aes();
        try {
            byte[] encryptResult = aestemp.encrytor(content, password);
            //加密结果
            String encryptResultStr = aestemp.parseByte2HexStr(encryptResult);
            System.out.println(encryptResultStr);

            //解密
            byte[] decryptFrom = aestemp.parseHexStr2Byte(encryptResultStr);
            byte[] decryptResult = aestemp.decryptor(decryptFrom, password);
            //解密结果
            String res = new String(decryptResult);
            System.out.println(res);
        } catch(Exception e){
            System.out.println(e.toString());
        }


    }
}
