package com.yeonjidev.record.board.service;

import com.yeonjidev.record.board.dao.BoardMapper;
import com.yeonjidev.record.board.vo.*;
import com.yeonjidev.record.utils.CompanyDepthItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardMapper mapper;

    /**
     * 회사 전체 목록
     * @return
     */
    public List<CompanyVO> selectCompanyList(){
        return mapper.selectCompanyList();
    }

    /**
     * 회사 상세 조회
     * @param companyId
     * @return
     */
    public CompanyVO selectCompanyInfo(Long companyId){
        CompanyVO company = mapper.selectCompanyInfo(companyId);
        List<FinanceVO> financeList = Optional.ofNullable(mapper.selectFinanceList(companyId)).orElse(new ArrayList<>());
        List<ProductVO> productList = Optional.ofNullable(mapper.selectProductList(companyId)).orElse(new ArrayList<>());
        List<BusinessVO> businessList = Optional.ofNullable(mapper.selectBusinessList(companyId)).orElse(new ArrayList<>());
        List<BusinessVO> abilityList = Optional.ofNullable(mapper.selectAbilityList(companyId)).orElse(new ArrayList<>());

        DepthVO depth = new DepthVO();
        depth.setCompanyId(companyId);
        List<List<?>> lists = Arrays.asList(productList, businessList, abilityList);
        setDepthList(lists, depth);

        
        company.setFinanceList(financeList);
        company.setProductList(productList);
        company.setBusinessList(businessList);
        company.setAbilityList(abilityList);

        return company;
    }

    /**
     * 조회 시 상세정보 목록 가져와서 set
     * @param lists
     * @param depth
     */
    private void setDepthList(List<List<?>> lists, DepthVO depth){
        Iterator<List<?>> iterator = lists.iterator();
        while(iterator.hasNext()){
            List<CompanyCommonVO> element = (List<CompanyCommonVO>) iterator.next();
            if(element.size() > 0){
                element.forEach((item) -> {
                    depth.setParent(item.getParent());
                    depth.setParentSeqNo(item.getSeqNo());
                    item.setDepthList(Optional.ofNullable(mapper.selectDepthList(depth)).orElse(new ArrayList<>()));
                });
            }
        }
    }

    /**
     * 회사 수정
     * @param company
     * @return
     */
    @Transactional
    public int updateCompanyInfo(CompanyVO company){
        Long companyId = company.getCompanyId();
        //수정 시 각 목록마다 delete 후 다시 insert
        
        List<FinanceVO> financeList = company.getFinanceList();
        List<ProductVO> productList = company.getProductList();
        List<BusinessVO> businessList = company.getBusinessList();
        List<BusinessVO> abilityList = company.getAbilityList();

        mapper.deleteDepthList(companyId);
        DepthVO param = new DepthVO();
        param.setCompanyId(companyId);
        

        mapper.deleteFinance(companyId);
        if(financeList.size() > 0){
            financeList.forEach(finance -> {
                finance.setCompanyId(companyId);
                mapper.insertFinance(finance);
            });
        }

        mapper.deleteProduct(companyId);
        if(productList.size() > 0){
            productList.forEach(bsnsCoop -> {
                bsnsCoop.setCompanyId(companyId);
                mapper.insertProduct(bsnsCoop);
                param.setParentSeqNo(bsnsCoop.getSeqNo());
                param.setParent(CompanyDepthItem.PRODUCT.getCode());
                insertDepth(bsnsCoop.getDepthList(), param);
            });
        }

        mapper.deleteBusiness(companyId);
        if(businessList.size() > 0){
            businessList.forEach(business -> {
                business.setCompanyId(companyId);
                mapper.insertBusiness(business);
                param.setParentSeqNo(business.getSeqNo());
                param.setParent(CompanyDepthItem.BUSINESS.getCode());
                insertDepth(business.getDepthList(), param);
            });
        }

        mapper.deleteAbility(companyId);
        if(abilityList.size() > 0){
            abilityList.forEach(ability -> {
                ability.setCompanyId(companyId);
                mapper.insertAbility(ability);
                param.setParentSeqNo(ability.getSeqNo());
                param.setParent(CompanyDepthItem.ABILITY.getCode());
                insertDepth(ability.getDepthList(), param);
            });
        }
        //회사 정보 update
        return mapper.updateCompany(company);
    }

    /**
     * 상세정보 목록 insert
     * @param depthList
     * @param param
     */
    private void insertDepth(List<DepthVO> depthList, DepthVO param){
        depthList.forEach((depth) -> {
            depth.setCompanyId(param.getCompanyId());
            depth.setParent(param.getParent());
            depth.setParentSeqNo(param.getParentSeqNo());
            mapper.insertDepth(depth);
        });
    }

    /**
     * 회사 삭제
     * @param companyId
     * @return
     */
    @Transactional
    public int deleteCompany(Long companyId){
        mapper.deleteFinance(companyId);
        mapper.deleteProduct(companyId);
        mapper.deleteBusiness(companyId);
        mapper.deleteAbility(companyId);
        mapper.deleteDepthList(companyId);
        return mapper.deleteCompany(companyId);
    }
}
