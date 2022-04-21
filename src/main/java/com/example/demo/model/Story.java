package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "news_index")
public class Story {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "pubDate")
    private String pubDate;

    @Field(type = FieldType.Text, name = "guid")
    private String guid;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
