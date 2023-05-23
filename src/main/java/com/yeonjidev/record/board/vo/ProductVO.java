package com.yeonjidev.record.board.vo;

import lombok.Data;

@Data
public class ProductVO extends CompanyCommonVO {
    private String productName;
    private String productYear;
    private String category;
    private String note;
}
