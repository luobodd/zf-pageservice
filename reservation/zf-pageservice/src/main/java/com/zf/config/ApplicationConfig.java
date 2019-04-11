package com.zf.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用配置
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
@Accessors(chain = true)
public class ApplicationConfig {
    /**
     * 是否为调试模式
     */
    private Boolean debug;
}
