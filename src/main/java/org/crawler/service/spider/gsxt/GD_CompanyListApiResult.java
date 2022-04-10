package org.crawler.service.spider.gsxt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 企业工商信息 API 结果对象
 *      - CompanyListApiResult
 *
 * @author: liushuwei
 * @date: 16:11
 * @version: 1.9
 */
@Data
public class GD_CompanyListApiResult {
    private Integer code;
    private String msg;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public class DataDTO {
        @JsonProperty("records")
        private List<RecordsDTO> records;
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("size")
        private Integer size;
        @JsonProperty("current")
        private Integer current;
        @JsonProperty("orders")
        private List<?> orders;
        @JsonProperty("optimizeCountSql")
        private Boolean optimizeCountSql;
        @JsonProperty("searchCount")
        private Boolean searchCount;
        @JsonProperty("countId")
        private Object countId;
        @JsonProperty("maxLimit")
        private Object maxLimit;
        @JsonProperty("pages")
        private Integer pages;

        @NoArgsConstructor
        @Data
        public class RecordsDTO {
            @JsonProperty("entNo")
            private String entNo;
            @JsonProperty("entName")
            private String entName;
            @JsonProperty("uniSCID")
            private String uniSCID;
            @JsonProperty("regNo")
            private String regNo;
            @JsonProperty("leRep")
            private String leRep;
            @JsonProperty("entType")
            private String entType;
            @JsonProperty("regOrg")
            private String regOrg;
            @JsonProperty("estDate")
            private String estDate;
            @JsonProperty("opState")
            private String opState;
            @JsonProperty("opStateCn")
            private String opStateCn;
            @JsonProperty("regCapital")
            private Double regCapital;
            @JsonProperty("regCapitalCoin")
            private String regCapitalCoin;
            @JsonProperty("smallMicroEnt")
            private String smallMicroEnt;
            @JsonProperty("datasourceKey")
            private String datasourceKey;
            @JsonProperty("isInvalid")
            private String isInvalid;
            @JsonProperty("isSimpleCancel")
            private String isSimpleCancel;
            @JsonProperty("isNotice")
            private String isNotice;
            @JsonProperty("isFoodProd")
            private String isFoodProd;
            @JsonProperty("isSpec")
            private String isSpec;
            @JsonProperty("isAnomaly")
            private String isAnomaly;
            @JsonProperty("hasCase")
            private String hasCase;
            @JsonProperty("hasLicense")
            private String hasLicense;
            @JsonProperty("hasMort")
            private String hasMort;
            @JsonProperty("hasTM")
            private String hasTM;
            @JsonProperty("link")
            private Object link;
        }
    }
}
