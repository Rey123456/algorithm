package com.algorithm.encrypt;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author:
 * @Description: 使用md5加密相同的值肯定得到相同结果，可用于密码判断之类的
 * md5加密不可逆，现有的md5解密可能是有大量md5的库，很多字符串md5后的值相同，因为md5后的结果是有限多的，而md5前的字符串是无限多的
 * @Date: Created in 4:36 PM 1/22/18
 * @Modified by:
 */
public class md5 {
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    public static void main(String[] args) {
        md5 md5tmp = new md5();
        try {
            System.out.println(md5tmp.EncoderByMd5("12345"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
