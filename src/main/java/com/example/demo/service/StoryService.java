package com.example.demo.service;

import com.example.demo.dao.StoryRepository;
import com.example.demo.model.Story;

import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public StoryService(StoryRepository storyRepository){
        this.storyRepository = storyRepository;
    }

    public Story findById(final String id){
        return storyRepository.findById(id).orElse(null);
    }

    public List<Story> findAll(){
        return storyRepository.findAll();
    }


    public List<Story> findStoryByTitle(final String title){
        return matchBy("title", title);
    }

    public List<Story> findStoryByDescription(final String description){
        return matchBy("description", description);
    }

    public List<Story> findStoryByDate(final String date){
        return matchBy("pubDate", date);
    }

    private List<Story> matchBy(String key, String value){
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery(key, value);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Story> storyHits =
                elasticsearchOperations
                .search(searchQuery,
                        Story.class,
                        IndexCoordinates.of("news_index"));

        return getMatchResults(storyHits);

    }

    private List<Story> getMatchResults(SearchHits<Story> storyHits){
        List<Story> storyMatches = new ArrayList<Story>();
        storyHits.forEach(searchHit -> {
            storyMatches.add(searchHit.getContent());
        });

        return storyMatches;
    }



}
