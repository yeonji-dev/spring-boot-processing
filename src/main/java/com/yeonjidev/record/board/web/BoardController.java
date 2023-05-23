package com.yeonjidev.record.board.web;

import com.yeonjidev.record.board.service.BoardService;
import com.yeonjidev.record.board.vo.CompanyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService service;
    /**
     * 회사 목록 / 회사 통계
     * @return
     */
    @GetMapping("/company")
    public ResponseEntity<List<CompanyVO>> selectStatics(){
        return new ResponseEntity<>(service.selectCompanyList(), HttpStatus.OK);
    }

    /**
     * 회사 상세 조회
     * @param companyId
     * @return
     */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<CompanyVO> selectCompanyInfo(@PathVariable(name = "companyId") Long companyId){
        return new ResponseEntity<>(service.selectCompanyInfo(companyId), HttpStatus.OK);
    }

    /**
     * 회사 수정
     * @param companyVO
     * @return
     */
    @PostMapping("/company")
    public ResponseEntity<Integer> updateCompanyInfo(@RequestBody CompanyVO companyVO){

        try{
            int result = service.updateCompanyInfo(companyVO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            log.error("회사 수정 실패 : {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 회사 삭제
     * @param companyId
     * @return
     */
    @DeleteMapping("/company/{companyId}")
    public ResponseEntity<Integer> deleteCompany(@PathVariable(name = "companyId") Long companyId) {
        return new ResponseEntity<>(service.deleteCompany(companyId), HttpStatus.OK);
    }
}
