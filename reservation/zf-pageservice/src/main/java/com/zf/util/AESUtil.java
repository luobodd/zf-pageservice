package com.zf.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * AES对称加密和解密
 */
public class AESUtil {

    private static final String RULES= "fsJFKL4J23A21nv0";

    /**
     * 加密
     * @param content 需要加密的内容
     * @return 加密后的内容和秘钥
     * @throws Exception
     */
    public static Map<String, String> AESEncode(String content) throws Exception {
        try {
            // 构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            // 根据ecnodeRules规则初始化密钥生成器
            // 生成一个128位的随机源,根据传入的字节数组
            keygen.init(256, new SecureRandom(RULES.getBytes()));
            // 产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            // 根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            // 根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            // 初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode = content.getBytes(StandardCharsets.UTF_8);
            // 根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
            // 将加密后的数据转换为字符串
            String AES_encode = new String(Base64Utils.encode(byte_AES));
            // 将字符串返回
            Map<String, String> map = new HashMap<>();
            map.put("secretKey", Base64Utils.encode(key.getEncoded()));
            map.put("secretContent", AES_encode);
            return map;
        } catch (NoSuchAlgorithmException | BadPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        // 如果有错就返加 null
        return null;
    }
    /**
     * 解密
     */
    public static String AESDncode(String content, String secretKey) throws Exception {
        try {
            SecretKey key = new SecretKeySpec(Base64Utils.decode(secretKey), "AES");

            // 根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            // 初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 将加密并编码后的内容解码成字节数组
            byte [] byte_content= Base64Utils.decode(content);
            byte [] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }
}
