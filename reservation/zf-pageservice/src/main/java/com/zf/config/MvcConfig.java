package com.zf.config;

import co.legu.modules.auth.interceptor.AuthInterceptor;
import co.legu.modules.common.util.LazyInitVar;
import co.legu.modules.common.util.Springs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zf.common.argumentresolver.LocalDateHandlerMethodArgumentResolver;
import com.zf.common.argumentresolver.LocalDateTimeHandlerMethodArgumentResolver;
import com.zf.common.interceptor.MybatisCacheClearInterceptor;

import java.util.List;

@Configuration
public class MvcConfig {
    /**
     * 项目的顶级包
     */
    public static final String BASE_PACKAGE = "com.zf";

    /**
     * WebMvc 配置增强，用于添加拦截器
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                // Mybatis 缓存清理拦截器
                registry.addInterceptor(new MybatisCacheClearInterceptor());
                // 登录拦截器
                registry.addInterceptor(new AuthInterceptor());
            }

            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new LocalDateHandlerMethodArgumentResolver());
                resolvers.add(new LocalDateTimeHandlerMethodArgumentResolver());
            }
        };
    }

    /**
     * 应用是否处于调试模式
     */
    private static final LazyInitVar<Boolean> isDebugValue = LazyInitVar.from(() -> Springs.getBean(ApplicationConfig.class).getDebug());

    /**
     * 获取应用是否处于调试模式
     * @return 应用是否处于调试模式
     */
    public static boolean isDebug() {
        return MvcConfig.isDebugValue.get();
    }
}
