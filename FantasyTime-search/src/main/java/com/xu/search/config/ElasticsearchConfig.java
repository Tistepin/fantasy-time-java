package com.xu.search.config;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐國紀
 * @Name F3863479
 * @create 2022-11-2022/11/17-下午 09:14
 * <p>
 * 1.导入依赖
 * 2.编写配置
 * 3.參照官方文档操作api
 */

@Configuration
public class ElasticsearchConfig {

//    @Value("${spring.elasticsearch.rest.host}")
//    private String host;
//    @Value("${spring.data.elasticsearch.repositories.enabled}")
//    private boolean enable;
//
//    @Value("${spring.elasticsearch.rest.port}")
//    private int port;
//    @Value("${spring.data.elasticsearch.client.reactive.username}")
//    private String userName;
//
//    @Value("${spring.data.elasticsearch.client.reactive.password}")
//    private String passWord;

    //    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        ElasticsearchClient client = new ElasticsearchClient(null);
//        if (enable) {
//            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            //设置账号密码
//            credentialsProvider.setCredentials(
//                    AuthScope.ANY, new UsernamePasswordCredentials(userName, passWord));
//
////        RestClients restClients =
//            RestClient restClient = RestClient.builder(new HttpHost(host, port,"http"))
//                    .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();
//
//            ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//            // And create the API client
//            client = new ElasticsearchClient(transport);
//        }
//        return client;
//    }
    //注入IOC容器
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient client = RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(
                            RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(5000 * 1000) // 连接超时（默认为1秒）
                                .setSocketTimeout(6000 * 1000);// 套接字超时（默认为30秒）//更改客户端的超时限制默认30秒现在改为100*1000分钟
                    }
                }).build();
        ElasticsearchTransport transport = new RestClientTransport(client, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        // builder.addHeader("Authorization", "Bearer " + TOKEN);
        // builder.setHttpAsyncResponseConsumerFactory(
        //         new HttpAsyncResponseConsumerFactory
        //                 .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }
}
