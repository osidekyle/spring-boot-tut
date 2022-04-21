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

    @GetMapping("/{id}")
    public Story save(@PathVariable final String id){
        return storyService.findById(id);
    }
}
