package com.example.demo.service;

import com.example.demo.dao.StoryRepository;
import com.example.demo.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository){
        this.storyRepository = storyRepository;
    }

    public void save(final Story story){
        storyRepository.save(story);
    }

    public Story findById(final String id){
        return storyRepository.findById(id).orElse(null);
    }

    public List<Story> findAll(){
        return storyRepository.findAll();
    }

    public void createStoryIndexBulk(final List<Story> stories){
        storyRepository.saveAll(stories);
    }

    public void createProductIndex(final Story story){
        storyRepository.save(story);
    }

}
