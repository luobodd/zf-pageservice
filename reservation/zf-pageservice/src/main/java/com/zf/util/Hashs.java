package com.zf.util;

import lombok.NonNull;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash 工具类
 */
public final class Hashs {
    private Hashs() {
        throw new UnsupportedOperationException();
    }

    private static final Charset defaultCharset = Charset.defaultCharset();
    private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f' };


    /**
     * 将 byte 数组转换为 hex 字符串
     * @param data 被转换的 byte 数组
     * @return 转换结果
     */
    @NonNull
    public static String bin2hex(@NonNull byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int h = (b >> 4) & 0xF;
            int l = b & 0xF;
            sb.append(HEX[h]);
            sb.append(HEX[l]);
        }

        return sb.toString();
    }

    /**
     * 计算输入数据的 hash，结果以 byte 数组的形式输出
     * @param hashName hash 算法的名称
     * @param data 要进行计算的数据
     * @return 计算结果
     */
    @NonNull
    public static byte[] hash_bin(@NonNull String hashName, @NonNull byte[] data) {
        try {
            return MessageDigest.getInstance(hashName).digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 计算输入数据的 hash
     * @param hashName hash 算法的名称
     * @param data 要进行计算的数据
     * @return 计算结果
     */
    @NonNull
    public static String hash(@NonNull String hashName, @NonNull byte[] data) {
        try {
            return bin2hex(MessageDigest.getInstance(hashName).digest(data));
        } catch (NoSuchAlgorithmException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 计算输入数据的 sha1
     * @param str 要进行计算的字符串
     * @return 计算结果
     */
    @NonNull
    public static String sha1(@NonNull String str) {
        return sha1(str.getBytes(defaultCharset));
    }

    /**
     * 计算输入数据的 sha1
     * @param data 要进行计算的数据
     * @return 计算结果
     */
    @NonNull
    public static String sha1(@NonNull byte[] data) {
        return hash("SHA-1", data);
    }

    /**
     * 计算输入数据的 sha256
     * @param str 要进行计算的字符串
     * @return 计算结果
     */
    @NonNull
    public static String sha256(@NonNull String str) {
        return sha256(str.getBytes(defaultCharset));
    }

    /**
     * 计算输入数据的 sha256
     * @param data 要进行计算的数据
     * @return 计算结果
     */
    @NonNull
    public static String sha256(@NonNull byte[] data) {
        return hash("SHA-256", data);
    }

    /**
     * 计算输入数据的 md5
     * @param str 要进行计算的字符串
     * @return 计算结果
     */
    @NonNull
    public static String md5(@NonNull String str) {
        return md5(str.getBytes(defaultCharset));
    }

    /**
     * 计算输入数据的 md5
     * @param data 要进行计算的数据
     * @return 计算结果
     */
    @NonNull
    public static String md5(@NonNull byte[] data) {
        return hash("MD5", data);
    }
}
