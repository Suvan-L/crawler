/**
  * Copyright 2022 bejson.com 
  */
package org.crawler.service.spider.creditchina.entity;


import lombok.Data;

@Data
public class DetailsContentApiResult {

    private int status;
    private String message;
    private DetailsContentData data;
}