package com.example.demo.api;

import com.example.demo.model.Story;
import com.example.demo.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/story")
@RestController
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }

    @PostMapping
    public void save(@RequestBody final Story story){
        storyService.save(story);
    }

    @PostMapping
    public void saveAll(@RequestBody final List<Story> stories){
        storyService.saveAll(stories);
    }

    @GetMapping("/{id}")
    public Story findById(@PathVariable final String id){
        return storyService.findById(id);
    }

    @GetMapping
    public List<Story> findAll(){
        return storyService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final String id){
        storyService.deleteById(id);
    }

    @GetMapping("/title/{title}")
    public List<Story> getStoryByTitle(@PathVariable final String title){
        return storyService.findStoryByTitle(title);
    }

    @GetMapping("/description/{description}")
    public List<Story> getStoryByDescription(@PathVariable final String description){
        return storyService.findStoryByDescription(description);
    }
}
