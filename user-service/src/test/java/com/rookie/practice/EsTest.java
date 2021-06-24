package com.rookie.practice;

import com.alibaba.fastjson.JSON;
import com.rookie.practice.entity.Account;
import com.rookie.practice.entity.SysUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: niezhiqiang
 * @date: 2020/9/27 16:48
 * @description:
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class EsTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;


    @Test
    public void testSave() {
        SysUserInfo user = new SysUserInfo();
        user.setId(13);
        user.setLoginName("apt021010");
        user.setRoleId(1931328);
        user.setUserGuid("88390E66-C8E4-4B74-A56B-403FAADD9EBD");
        user.setUsername("聂志强");
    }

    @Test
    public void testGetAccount() {
        SearchRequest request = new SearchRequest("bank");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchAllQuery());
            sourceBuilder.sort("account_number", SortOrder.ASC);
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("account_number");
            sourceBuilder.highlighter(highlightBuilder);
            request.source(sourceBuilder);
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            List<Account> accounts = new ArrayList<>();
            for (SearchHit hit : hits) {
                accounts.add(JSON.parseObject(hit.getSourceAsString(), Account.class));
            }
            log.info("accounts:{}", JSON.toJSONString(accounts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testGetAccountPhrase() {
        SearchRequest request = new SearchRequest("bank");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchPhraseQuery("address", "mill lane"));
            request.source(sourceBuilder);
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            List<Account> accounts = new ArrayList<>();
            for (SearchHit hit : hits) {
                accounts.add(JSON.parseObject(hit.getSourceAsString(), Account.class));
            }
            log.info("accounts:{}", JSON.toJSONString(accounts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAccountBoolean() {
        SearchRequest request = new SearchRequest("bank");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            // boolean
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.matchQuery("age", 40));
            boolQueryBuilder.mustNot(QueryBuilders.matchQuery("State", "ID"));
            sourceBuilder.query(boolQueryBuilder);
            request.source(sourceBuilder);
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            List<Account> accounts = new ArrayList<>();
            for (SearchHit hit : hits) {
                accounts.add(JSON.parseObject(hit.getSourceAsString(), Account.class));
            }
            log.info("accounts:{}", JSON.toJSONString(accounts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAccountBooleanFilter() {
        SearchRequest request = new SearchRequest("bank");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            // boolean
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.matchAllQuery());
            // >20000 <30000
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("balance").gte(20000).lte(30000));
            sourceBuilder.query(boolQueryBuilder);
            request.source(sourceBuilder);
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = search.getHits().getHits();
            List<Account> accounts = new ArrayList<>();
            for (SearchHit hit : hits) {
                accounts.add(JSON.parseObject(hit.getSourceAsString(), Account.class));
            }
            log.info("accounts:{}", JSON.toJSONString(accounts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAccountAggregations() {
        SearchRequest request = new SearchRequest("bank");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            TermsAggregationBuilder groupAggregationBuilder = AggregationBuilders.terms("by_state").field("state.keyword");
            sourceBuilder.aggregation(groupAggregationBuilder);
            request.source(sourceBuilder);
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            Terms byState = search.getAggregations().get("by_state");
            for (Terms.Bucket bucket : byState.getBuckets()) {
                log.info("key:{},docCount:{}", bucket.getKey(), bucket.getDocCount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 嵌套聚合
     */
    @Test
    public void testGetAccountAggregationsNested() {
        SearchRequest request = new SearchRequest("bank");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            TermsAggregationBuilder groupAggregationBuilder = AggregationBuilders.terms("by_state").field("state.keyword");
            groupAggregationBuilder.subAggregation(AggregationBuilders.avg("average_balance").field("balance"));
            sourceBuilder.aggregation(groupAggregationBuilder);
            request.source(sourceBuilder);
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            Terms byState = search.getAggregations().get("by_state");
            for (Terms.Bucket bucket : byState.getBuckets()) {
                Avg aggregation = bucket.getAggregations().get("average_balance");
                log.info("key:{},docCount:{},value:{}", bucket.getKey(), bucket.getDocCount(), aggregation.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 嵌套聚合
     */
    @Test
    public void testGetAccountAggregationsNested2() {
        ActionListener<SearchResponse> actionListener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                Terms byState = searchResponse.getAggregations().get("by_state");
                for (Terms.Bucket bucket : byState.getBuckets()) {
                    Avg aggregation = bucket.getAggregations().get("average_balance");
                    log.info("key:{},docCount:{},value:{}", bucket.getKey(), bucket.getDocCount(), aggregation.getValue());
                }
            }

            @Override
            public void onFailure(Exception e) {
                System.err.println("查询失败");
            }
        };
        SearchRequest request = new SearchRequest("bank");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder groupAggregationBuilder = AggregationBuilders.terms("by_state").field("state.keyword");
        groupAggregationBuilder.subAggregation(AggregationBuilders.avg("average_balance").field("balance"));
        sourceBuilder.aggregation(groupAggregationBuilder);
        request.source(sourceBuilder);
        restHighLevelClient.searchAsync(request, RequestOptions.DEFAULT, actionListener);
//            Terms byState = search.getAggregations().get("by_state");
//            for (Terms.Bucket bucket : byState.getBuckets()) {
//                Avg aggregation = bucket.getAggregations().get("average_balance");
//                log.info("key:{},docCount:{},value:{}", bucket.getKey(), bucket.getDocCount(), aggregation.getValue());
//            }

    }


}
