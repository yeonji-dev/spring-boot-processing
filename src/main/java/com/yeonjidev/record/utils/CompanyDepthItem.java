package com.yeonjidev.record.utils;

public enum CompanyDepthItem {
    PRODUCT("PD"), ABILITY("AB"), BUSINESS("BS");

    private String code;

    private CompanyDepthItem(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
