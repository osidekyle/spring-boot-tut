package com.example.demo.service;

import com.example.demo.model.Story;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class StoryService {

    private RestHighLevelClient client;

    private ObjectMapper objectMapper;

    @Autowired
    public StoryService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public Story findById(String id) throws Exception {

        GetRequest getRequest = new GetRequest("news_index", id);

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();

        return objectMapper
                .convertValue(resultMap, Story.class);

    }

    public List<Story> findAll() throws Exception {

        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);


    }


    private List<Story> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Story> storyDocuments = new ArrayList<>();

        if (searchHit.length > 0) {
            Arrays.stream(searchHit)
                    .forEach(hit -> storyDocuments
                            .add(objectMapper
                                    .convertValue(hit.getSourceAsMap(),
                                            Story.class))
                    );
        }

        return storyDocuments;
    }

}
