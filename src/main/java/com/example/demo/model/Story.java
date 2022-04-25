package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "news_index")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Story {
    @Id
    private String id;
    private String title;
    private String description;
    private String pubDate;
    private String guid;
}
