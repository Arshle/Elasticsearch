/*
 * FileName: CloudEnvironmentRepository.java
 * Author:   Arshle
 * Date:     2018年05月22日
 * Description:
 */
package com.chezhibao.elasticsearch.repository;

import com.chezhibao.elasticsearch.entity.CloudEnvironment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author Arshle
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public interface CloudEnvironmentRepository extends ElasticsearchRepository<CloudEnvironment,Long> {

    List<CloudEnvironment> findByDomain(String domain);

    List<CloudEnvironment> findByBranchName(String branchName);

    public List<CloudEnvironment> findByIngressIp(String ingressIp);
}
