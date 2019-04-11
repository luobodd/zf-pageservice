package com.zf.util;

import lombok.NonNull;

import java.lang.reflect.Array;

/**
 * 数组操作工具类
 */
public final class ArrayUtils {
    private ArrayUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 合并两个数组
     * @param a 被合并的数组
     * @param b 被合并的数组
     * @return 合并结果
     */
    @NonNull
    public static byte[] concat(@NonNull byte[] a, @NonNull byte[] b) {
        final int alen = a.length;
        final int blen = b.length;
        if (alen == 0) {
            return b;
        }
        if (blen == 0) {
            return a;
        }
        final byte[] result = new byte[alen + blen];
        System.arraycopy(a, 0, result, 0, alen);
        System.arraycopy(b, 0, result, alen, blen);
        return result;
    }

    /**
     * 合并两个数组
     * @param a 被合并的数组
     * @param b 被合并的数组
     * @return 合并结果
     */
    @NonNull
    public static <T> T[] concat(@NonNull T[] a, @NonNull T[] b) {
        final int alen = a.length;
        final int blen = b.length;
        if (alen == 0) {
            return b;
        }
        if (blen == 0) {
            return a;
        }

        @SuppressWarnings("unchecked")
        final T[] result = (T[]) Array.newInstance(a.getClass().getComponentType(), alen + blen);
        System.arraycopy(a, 0, result, 0, alen);
        System.arraycopy(b, 0, result, alen, blen);
        return result;
    }
}
