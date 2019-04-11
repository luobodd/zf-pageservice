package com.zf.util;

import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流工具类
 */
public final class Streams {
    private Streams() {
        throw new UnsupportedOperationException();
    }

    /**
     * 将输入流的数据拷贝到输出流
     * @param in 输入流
     * @param out 输出流
     * @param autoClose 拷贝完成后是否自动关闭两个流
     */
    public static void copy(@NonNull InputStream in, @NonNull OutputStream out, boolean autoClose) throws IOException {
        try {
            byte[] buf = new byte[4096];
            int len = 0;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } finally {
            if (autoClose) {
                Streams.closeQuiet(in, out);
            }
        }
    }

    /**
     * 将输入流的数据拷贝到输出流
     * @param in 输入流
     * @param out 输出流
     */
    public static void copy(@NonNull InputStream in, @NonNull OutputStream out) throws IOException {
        Streams.copy(in, out, false);
    }

    /**
     * 安静的关闭任何实现了 AutoCloseable 接口的对象，关闭失败不会抛出任何异常
     * @param closeables 要关闭的对象列表
     */
    public static void closeQuiet(@NonNull AutoCloseable... closeables) {
        for(AutoCloseable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
                // quiet
            }
        }
    }
}
