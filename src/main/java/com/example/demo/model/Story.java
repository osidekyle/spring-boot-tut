package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
