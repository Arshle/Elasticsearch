package com.chezhibao.elasticsearch;

import com.chezhibao.elasticsearch.entity.CloudEnvironment;
import com.chezhibao.elasticsearch.utils.Elasticsearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Test
    public void contextLoads() {
        Elasticsearch.connect();
        CloudEnvironment environment = new CloudEnvironment();
        environment.setBranchName("UDEP-103");
        environment.setDomain("sit.mychebao.com");
        environment.setIngressIp("172.16.12.34");
        environment.setCreationTime(new Date());
        environment.setDescription("UDEP-103分支");
        environment.setStatus(1);
        System.out.println(Elasticsearch.addDocument("cloud","cloud_environment",environment));
    }
}
