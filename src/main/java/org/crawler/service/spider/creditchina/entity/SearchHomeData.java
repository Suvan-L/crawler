/**
  * Copyright 2022 bejson.com 
  */
package org.crawler.service.spider.creditchina.entity;
import java.util.List;

/**
 * Auto-generated: 2022-04-10 17:1:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SearchHomeData {

    private int page;
    private int total;
    private int totalSize;
    private List<SearchHomeList> list;
    public void setPage(int page) {
         this.page = page;
     }
     public int getPage() {
         return page;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setTotalSize(int totalSize) {
         this.totalSize = totalSize;
     }
     public int getTotalSize() {
         return totalSize;
     }

    public void setList(List<SearchHomeList> list) {
         this.list = list;
     }
     public List<SearchHomeList> getList() {
         return list;
     }

}