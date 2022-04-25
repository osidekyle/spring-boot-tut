package com.example.demo.dao;

import com.example.demo.model.Story;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface StoryRepository extends ElasticsearchRepository<Story, String> {

    List<Story> findAll();


}
