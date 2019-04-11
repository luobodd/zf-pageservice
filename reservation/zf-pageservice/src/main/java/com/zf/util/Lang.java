package com.zf.util;

import lombok.NonNull;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Lang {
    private Lang() {
        throw new UnsupportedOperationException();
    }


    /**
     * 判断传入的对象是否全部不为 null
     * @param objs 被判断的对象
     * @return 判断结果
     */
    public static boolean nonNulls(Object... objs) {
        for (Object o : objs) {
            if (Objects.isNull(o)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 将参数包装为 RuntimeException 后返回，如果它已经是一个 RuntimeException 则直接返回
     */
    @NonNull
    public static RuntimeException wrapThrow(@NonNull Throwable e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将传入的异常直接抛出<br>
     * @return 永远不会返回任何东西，返回值只是为了方便在必须有返回值的方法中 throw 出去来通过语法检查的。
     */
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> RuntimeException throwAny(@NonNull Throwable e) throws T {
        throw (T) e;
    }

    @FunctionalInterface
    public interface ThrowableConsumer<T> {
        void accept(T param) throws Exception;
    }

    @FunctionalInterface
    public interface ThrowableBiConsumer<K, V> {
        void accept(K param1, V param2) throws Exception;
    }

    @FunctionalInterface
    public interface ThrowableFunction<T, R> {
        R apply(T param) throws Exception;
    }

    /**
     * 允许 Consumer 抛出异常的包装器
     */
    @NonNull
    public static <T> Consumer<T> wrapConsumer(@NonNull ThrowableConsumer<T> c) {
        return (param -> {
            try {
                c.accept(param);
            } catch (Exception e) {
                throw throwAny(e);
            }
        });
    }

    /**
     * 允许 BiConsumer 抛出异常的包装器
     */
    @NonNull
    public static <K, V> BiConsumer<K, V> wrapBiConsumer(@NonNull ThrowableBiConsumer<K, V> c) {
        return ((param1, param2) -> {
            try {
                c.accept(param1, param2);
            } catch (Exception e) {
                throw throwAny(e);
            }
        });
    }

    /**
     * 允许 Function 抛出异常的包装器
     */
    @NonNull
    public static <T, R> Function<T, R> wrapFunction(@NonNull ThrowableFunction<T, R> f) {
        return (param -> {
            try {
                return f.apply(param);
            } catch (Exception e) {
                throw throwAny(e);
            }
        });
    }
}
