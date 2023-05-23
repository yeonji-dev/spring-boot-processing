package com.yeonjidev.record.board.vo;

import lombok.Data;

@Data
public class BusinessVO extends CompanyCommonVO {
    private String businessYear;
    private String content;
}
