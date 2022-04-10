/**
  * Copyright 2022 bejson.com 
  */
package org.crawler.service.spider.creditchina.entity;

/**
 * Auto-generated: 2022-04-10 17:1:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SearchHomeApiResult {

    private int status;
    private String message;
    private SearchHomeData data;
    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setData(SearchHomeData data) {
         this.data = data;
     }
     public SearchHomeData getData() {
         return data;
     }

}