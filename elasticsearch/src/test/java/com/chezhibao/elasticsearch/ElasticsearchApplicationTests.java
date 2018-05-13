package com.chezhibao.elasticsearch;

import com.chezhibao.elasticsearch.utils.Elasticsearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Test
    public void contextLoads() {
        Elasticsearch.connect();
        System.out.println(Elasticsearch.deleteIndex());
    }
}
