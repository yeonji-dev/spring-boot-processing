package com.yeonjidev.record.board.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompanyCommonVO {
    private Long companyId;
    private int seqNo;
    private String parent;  //depth 가져올때 parameter로 사용
    private List<DepthVO> depthList;
    private String regId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime regDate;
    private String updateId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;
}
