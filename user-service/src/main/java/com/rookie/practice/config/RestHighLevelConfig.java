package com.rookie.practice.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: niezhiqiang
 * @date: 2020/9/28 11:38
 * @description:
 */
@Configuration
public class RestHighLevelConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("111.231.228.138", 9200, "http"));
        builder.setRequestConfigCallback(builder1 -> {
            builder1.setSocketTimeout(60 * 60 * 1000);
            return builder1;
        });
        return new RestHighLevelClient(builder);
    }
}
