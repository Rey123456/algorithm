package com.algorithm.encrypt;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @Author:
 * @Description: DES加密介绍
    DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
    后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
    24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现

    注意：DES加密和解密过程中，密钥长度都必须是8的倍数

加密解密：Cipher.ENCRYPT_MODE  Cipher.DECRYPT_MODE
密钥生成方式:KeyGenerator， KeyPairGenerator，KeyFactory，SecretKeyFactory
KeyGenerator和SecretKeyFactory，都是javax.crypto包的，生成的key主要是提供给AES，DES，3DES，MD5，SHA1等 对称 和 单向 加密算法。
KeyPairGenerator和KeyFactory，都是java.security包的，生成的key主要是提供给DSA，RSA， EC等 非对称加密算法。

块加密的模式:
Cipher cipher=Cipher.getInstance("DES/CBC/NoPadding");
Cipher加密器初始化需要一个字符串，字符串里提供了三种设置。
一是，加解密算法；二是，加解密模式；三是，是否需要填充。
常见的模式有四种：ECB（电码本模式），CBC（加密块链模式），OFB（输出反馈模式），CFB（加密反馈模式），这些模式的原理在网上都有很多，这里不讲。
ECB模式简单，缺点是块加密的内容容易重复，会被统计分析攻击；
CBC,  OFB,  CFB三个模式，都是根据前面加密块的内容，对key进行新一轮处理后再，再对下一数据块进行处理，如此类推下去，这样一来，加密的强度也有所增强。他们都需要用到初始化向量IV，英文大概是Initialization Vector的缩写吧。
填充：
对于加密，因为DES是块加密，数据长度必须是8的倍数，然而实际上加密前的明文getBytes()后基本不会恰好是8的倍数，所以一般需要进行填充，填充的规则这里不说，想知道的百度吧，反正这个只需要设置参数 PKCS5Padding ，JDK就帮你填充了，若不填充，且数据长度不是8倍数，则会抛异常；
对于解密，一般来说加密的数据长度本身就是8的倍数，所以只需要NoPadding就可以了，若加密的数据长度不是8，就需要用PKCS5Padding，否则解密出来后的明文尾巴的会比原明文的尾巴多出好几位填充数据。（实测其它模式是，会抛 Given final block not properly padded的异常，这个要结合实际切换一下NoPadding 和 PKCS5Padding）

ECB模式：
Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, key);
CBC模式：
Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyBytes));//直接用keyBytes初始化IV
OFB模式：
Cipher cipher=Cipher.getInstance("DES/OFB/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyBytes));//直接用keyBytes初始化IV
CFB模式：
Cipher cipher=Cipher.getInstance("DES/CFB/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyBytes));//直接用keyBytes初始化IV

 * @Date: Created in 4:45 PM 1/22/18
 * @Modified by:
 */

public class DES {
    public DES() {
    }

    /**
     * 加密
     * @param datasource byte[]
     * @param password String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密
     * @param src byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static void printKey(byte[] keyBytes){
        try{
            //第一种，Factory
            DESKeySpec keySpec=new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
            SecretKey key1=keyFactory.generateSecret(keySpec);

            //第二种, Generator
            KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
            keyGenerator.init(56, new SecureRandom(keyBytes));//key为8个字节，实际用了56位； 后面随机数用key作为种子seed生成
            SecretKey key2=keyGenerator.generateKey();

            //第三种， SecretKeySpec
            SecretKey key3=new SecretKeySpec(keyBytes, "DES");//SecretKeySpec类同时实现了Key和KeySpec接口

            //打印
            System.out.println("key1："+ byteToHexString(key1.getEncoded()));
            System.out.println("key2："+ byteToHexString(key2.getEncoded()));
            System.out.println("key3："+ byteToHexString(key3.getEncoded()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //测试
    public static void main(String args[]) {
        //待加密内容
        String str = "测试内容";
        //密码，长度要是8的倍数
        String password = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";

        byte[] result = DES.encrypt(str.getBytes(),password);
        System.out.println("加密后："+ byteToHexString(result));

        //直接将如上内容解密
        try {
            byte[] decryResult = DES.decrypt(result, password);
            System.out.println("解密后："+ byteToHexString(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        printKey(password.getBytes());
    }
}
