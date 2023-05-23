package com.yeonjidev.record.board.vo;

import lombok.Data;

@Data
public class FinanceVO extends CompanyCommonVO {
    private String year;
    private Float sales;
    private Float income;
}
