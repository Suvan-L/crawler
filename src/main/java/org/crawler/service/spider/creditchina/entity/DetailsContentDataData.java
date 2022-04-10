/**
  * Copyright 2022 bejson.com 
  */
package org.crawler.service.spider.creditchina.entity;
import java.util.List;

/**
 * Auto-generated: 2022-04-10 18:53:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DetailsContentDataData {

    private List<String> columnList;
    private DetailsContentDataDataSencesMap sencesMap;
    private String dataSource;
    private String data_catalog;
    private String table_name;
    private DetailsContentDataDataEntity entity;
    public void setColumnList(List<String> columnList) {
         this.columnList = columnList;
     }
     public List<String> getColumnList() {
         return columnList;
     }

    public void setSencesMap(DetailsContentDataDataSencesMap sencesMap) {
         this.sencesMap = sencesMap;
     }
     public DetailsContentDataDataSencesMap getSencesMap() {
         return sencesMap;
     }

    public void setDataSource(String dataSource) {
         this.dataSource = dataSource;
     }
     public String getDataSource() {
         return dataSource;
     }

    public void setData_catalog(String data_catalog) {
         this.data_catalog = data_catalog;
     }
     public String getData_catalog() {
         return data_catalog;
     }

    public void setTable_name(String table_name) {
         this.table_name = table_name;
     }
     public String getTable_name() {
         return table_name;
     }

    public void setEntity(DetailsContentDataDataEntity entity) {
         this.entity = entity;
     }
     public DetailsContentDataDataEntity getEntity() {
         return entity;
     }

}