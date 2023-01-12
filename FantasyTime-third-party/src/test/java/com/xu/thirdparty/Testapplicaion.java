package com.xu.thirdparty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/11-上午 10:15
 */
@SpringBootTest
@ContextConfiguration
public class Testapplicaion {
    @Test
    public void Test(){
        File file1 = new File("C:/Users/F3863479/Desktop/Test/a");
        file1.mkdir();
    }
}
