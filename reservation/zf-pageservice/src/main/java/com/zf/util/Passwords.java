package com.zf.util;

import co.legu.modules.common.util.Strings;
import lombok.NonNull;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 密码加密、教研工具类，提供一种加盐的密码实现
 */
public final class Passwords {
    private Passwords() {
        throw new UnsupportedOperationException();
    }

    private static final Charset defaultCharset = Charset.defaultCharset();
    // 0 ~ 9，实际加密次数为 2 ** (8 + N)，修改不会影响已加密过的密码的校验
    private static final int N = 3;


    /**
     * 将密码加密
     * @param pwd 密码原文
     * @param uid 用户的 id
     * @return 加密后的密码
     */
    @NonNull
    public static String saltPwd(@NonNull String pwd, @NonNull String uid) {
        pwd = pwd.toLowerCase();

        String salt = Strings.rand(8);//获取8位的随机字符 A-Z,a-z，0-9
        int count = (int) Math.pow(2, 8 + N);//加密次数为2的（8+N）次幂

        byte[] hash = Hashs.hash_bin("md5", (salt + pwd + uid).getBytes(defaultCharset));
        for (int i = count; i > 0; i--) {
            hash = Hashs.hash_bin("md5", ArrayUtils.concat(hash, pwd.getBytes(defaultCharset)));
        }

        return N + salt + Base64.getEncoder().encodeToString(hash).substring(0, 22);
    }

    /**
     * 判断密码是否于加密后的密码匹配
     * @param pwd 密码原文
     * @param pwd2 加密后的密码
     * @param uid 用户的 id
     * @return 密码是否匹配
     */
    public static boolean eqPwd(@NonNull String pwd, @NonNull String pwd2, @NonNull String uid) {
        pwd = pwd.toLowerCase();

        int N = pwd2.charAt(0) - 48;
        int count = (int) Math.pow(2, 8 + N);
        String salt = pwd2.substring(1, 9);
        String hash2 = pwd2.substring(9);

        byte[] hash = Hashs.hash_bin("md5", (salt + pwd + uid).getBytes(defaultCharset));
        for (int i = count; i > 0; i--) {
            hash = Hashs.hash_bin("md5", ArrayUtils.concat(hash, pwd.getBytes(defaultCharset)));
        }

        String _hash = Base64.getEncoder().encodeToString(hash).substring(0, 22);
        return (_hash.equals(hash2));
    }

    public static void main(String [] args) {
        //System.out.println(saltPwd("1111", "001"));//测试密码加密
        System.out.println(eqPwd(Hashs.md5("1"), "3eKTdt421NJoxN07i7001DhSJBIJBhw", "1112574028159647745"));//测试密码比对
    }
}
