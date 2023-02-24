package com.xu.thirdparty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

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
    public void Test() {
        File file1 = new File("C:\\Users\\F3863479\\Desktop\\Test\\间谍过家家\\2");
        boolean delete = file1.delete();
        System.out.println(delete);
    }

    @Test
    public void Test2() {
        int a=10;
        int b=a;
        b=12;
        System.out.println("a"+a);
        System.out.println("b"+b);
    }
}
