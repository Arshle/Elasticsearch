/*
 * FileName: ElasticsearchConfiguration.java
 * Author:   Arshle
 * Date:     2018年05月22日
 * Description:
 */
package com.chezhibao.elasticsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author Arshle
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.chezhibao.elasticsearch.repository")
public class ElasticsearchConfiguration {

}
