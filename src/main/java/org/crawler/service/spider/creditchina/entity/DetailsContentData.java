/**
  * Copyright 2022 bejson.com 
  */
package org.crawler.service.spider.creditchina.entity;

import lombok.Data;

@Data
public class DetailsContentData {
    private String punishmentStatus;
    private DetailsContentDataData data;
    private DetailsContentHeadEntity headEntity;
    private String rewardStatus;

}