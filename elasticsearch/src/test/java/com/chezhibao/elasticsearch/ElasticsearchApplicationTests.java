package com.chezhibao.elasticsearch;

import com.chezhibao.elasticsearch.entity.CloudEnvironment;
import com.chezhibao.elasticsearch.repository.CloudEnvironmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Resource
    private CloudEnvironmentRepository repository;

    @Test
    public void contextLoads() {
        CloudEnvironment environment = new CloudEnvironment();
        environment.setBranchName("DRAGON-1840");
        environment.setStatus(1);
        environment.setDescription("DRAGON-1840");
        environment.setIngressIp("172.16.12.34");
        environment.setCreationTime(new Date());
        environment.setDomain("csf.mychebao.com");
        CloudEnvironment save = repository.save(environment);
        System.out.println(save.getBranchName());
    }
}
