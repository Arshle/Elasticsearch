/*
 * FileName: Elasticsearch.java
 * Author:   Arshle
 * Date:     2018年05月13日
 * Description:
 */
package com.chezhibao.elasticsearch.utils;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * 〈Elasticsearch客户端〉<br>
 * 〈Elasticsearch客户端〉
 *
 * @author Arshle
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class Elasticsearch {

    private static Logger logger = LoggerFactory.getLogger(Elasticsearch.class);
    private static Client client;
    /**
     * 默认连接
     */
    public static void connect(){
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name","elasticsearch")
                .put("client.transport.ingnore_cluster_name",false)
                .put("client.transport.ping_timeout",3,TimeUnit.SECONDS)
                .put("client.transport.nodes_sampler_interval",2,TimeUnit.SECONDS)
                .put("client.transport.sniff",true)
                .build();
        connect(settings);
    }
    /**
     * 连接到es集群
     * @param settings 连接配置
     */
    public static void connect(Settings settings){
        try {
            client = TransportClient.builder().settings(settings).build().addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName("node1"),9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("node2"),9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("node3"),9300));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
    /**
     * 创建索引
     * @return 是否成功
     */
    public static boolean createIndex(){
        try {
            XContentBuilder mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("settings")
                    .field("number_of_shards",1)
                    .field("number_of_replicas",0)
                    .endObject()
                    .endObject()
                    .startObject()
                    .startObject("simple")
                    .startObject("properties")
                    .startObject("type")
                    .field("type","string").field("store","yes")
                    .endObject()
                    .startObject("eventCount")
                    .field("type","long").field("store","yes")
                    .endObject()
                    .startObject("eventDate")
                    .field("type","date").field("format","dateOptionalTime").field("store","yes")
                    .endObject()
                    .startObject("message")
                    .field("type","string").field("index","not_analyzed").field("store","yes")
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject();
            CreateIndexRequestBuilder cirb = client.admin().indices()
                    .prepareCreate("esstart")
                    .setSource(mapping);

            CreateIndexResponse response = cirb.execute().actionGet();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return false;
    }
    /**
     * 增加文档
     * @return 文档ID
     */
    public static String addDocument(){
        try {
            IndexResponse response = client.prepareIndex("esstart","simple","1")
                    .setSource(jsonBuilder().startObject()
                    .field("type","syslog")
                    .field("eventCount",1)
                    .field("eventDate",new Date())
                    .field("message","secilog insert doc test")
                    .endObject()).get();
            return response.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "";
    }
    /**
     * 修改文档
     * @return 修改的文档ID
     */
    public static String updateDocument(){
        try {
            IndexRequest request = new IndexRequest("esstart","simple","3")
                    .source(jsonBuilder().startObject()
                    .field("type","syslog")
                    .field("eventCount",2)
                    .field("eventDate",new Date())
                    .field("message","secilog update test")
                    .endObject());
            UpdateRequest updateRequest = new UpdateRequest("esstart","simple","3")
                    .doc(jsonBuilder().startObject().field("type","file").endObject())
                    .upsert(request);
            UpdateResponse updateResponse = client.update(updateRequest).get();
            return updateResponse.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "";
    }
    /**
     * 查询文档
     * @return 文档内容
     */
    public static String getDocument(){
        GetResponse response = client.prepareGet("esstart","simple","1").get();
        return response.getSource().toString();
    }
    /**
     * 删除文档
     * @return 是否成功
     */
    public static boolean deleteDocument(){
        DeleteResponse response = client.prepareDelete("esstart","simple","4").get();
        return response.isFound();
    }
    /**
     * 删除索引
     * @return 是否成功
     */
    public static boolean deleteIndex(){
        DeleteIndexRequest request = new DeleteIndexRequest("esstart");
        ActionFuture<DeleteIndexResponse> delete = client.admin().indices().delete(request);
        return delete.actionGet().isAcknowledged();
    }
}
