package com.xu.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.xu.common.constant.systemEnum;
import com.xu.search.constant.EsConstant;
import org.elasticsearch.client.RequestOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/13-下午 04:35
 */
@SpringBootTest
public class FantasyTimeSearchTest {

    @Autowired
    private ElasticsearchClient client;
    @Test
    public void Test1() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("defaultImage","http://"+ systemEnum.USERIP.getMsg() +"/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=1");
        UpdateRequest.Builder<Object, Object> defaultImage = new UpdateRequest.Builder<>().index(EsConstant.WORKS_INDEX);
        UpdateRequest<Object, Object> build = defaultImage.id(String.valueOf(1))
                .doc(map)
                .build();
        client.update(build,Object.class);

    }

    @Test
    public void Test2() throws IOException {
        float a=1.4F;
        BigDecimal bigDecimal = BigDecimal.valueOf(a);
        System.out.println(bigDecimal);
    }

    @Test
    public void Test3() throws IOException {


    }
}
