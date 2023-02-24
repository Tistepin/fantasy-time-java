package com.xu.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.xu.search.constant.EsConstant;
import org.elasticsearch.client.RequestOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
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
        map.put("defaultImage","http://10.161.139.216/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=1");
        for (int i = 12; i <22 ; i++) {

                UpdateRequest.Builder<Object, Object> defaultImage = new UpdateRequest.Builder<>().index(EsConstant.WORKS_INDEX);
                UpdateRequest<Object, Object> build = defaultImage.id(String.valueOf(i))
                        .doc(map)
                        .build();
                    client.update(build,Object.class);
        }


    }
}
