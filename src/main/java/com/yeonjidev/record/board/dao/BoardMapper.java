package com.yeonjidev.record.board.dao;

import com.yeonjidev.record.board.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<CompanyVO> selectCompanyList();

    CompanyVO selectCompanyInfo(Long companyId);
    List<FinanceVO> selectFinanceList(Long companyId);
    List<ProductVO> selectProductList(Long companyId);
    List<BusinessVO> selectBusinessList(Long companyId);
    List<BusinessVO> selectAbilityList(Long companyId);
    List<DepthVO> selectDepthList(DepthVO depthVO);
    int updateCompany(CompanyVO companyVO);
    int deleteCompany(Long companyId);
    int deleteFinance(Long companyId);
    int deleteProduct(Long companyId);
    int deleteBusiness(Long companyId);
    int deleteAbility(Long companyId);
    int deleteDepthList(Long companyId);
    int insertFinance(FinanceVO financeVO);
    int insertProduct(ProductVO productVO);
    int insertBusiness(BusinessVO businessVO);
    int insertAbility(BusinessVO businessVO);
    int insertDepth(DepthVO depthVO);
}
