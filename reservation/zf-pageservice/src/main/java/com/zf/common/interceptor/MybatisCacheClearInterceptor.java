package com.zf.common.interceptor;

import co.legu.modules.common.util.Springs;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 负责清理 Mybatis 查询缓存的拦截器
 */
public class MybatisCacheClearInterceptor implements HandlerInterceptor {
    private SqlSession sqlSession = Springs.getBean(SqlSession.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        this.sqlSession.clearCache();
        return true;
    }
}
