package com.yeonjidev.record.board.vo;

import lombok.Data;

@Data
public class DepthVO extends CompanyCommonVO {
    private String parent;
    private int parentSeqNo;
    private String content;

}
