package com.yeonjidev.record.board.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompanyVO {

    private Long companyId;
    private String companyName;
    private String homepage;
    private String estYr;
    private String region;
    private String address;
    private String ceoName;
    private String resource;
    private String credit;
    private String category;
    private List<BusinessVO> businessList;
    private List<FinanceVO> financeList;
    private List<BusinessVO> abilityList;
    private List<ProductVO> productList;
    private String note;
    private String regId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime regDate;
    private String updateId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;

}