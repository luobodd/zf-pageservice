package com.zf.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 */
@Configuration
@MapperScan(MvcConfig.BASE_PACKAGE + ".**.mapper")
public class DataConfigure {
}
