package com.cos.blog.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.test.service.AcademyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/test/academy")
@RestController
public class AcademyController {

    private final AcademyService academyService;

    /**
     * 일반적인 Lazy 방식의 조회(N + 1 발생). 단, default_batch_fetch_size 설정 시 조건 절에 IN을 써서로 쿼리
     * 실행 수가 줄어됨
     * 
     * @return
     */
    @GetMapping("/v1/subjects")
    public ResponseEntity<?> getAllSubjectNames() {
        return ResponseEntity.ok(academyService.findAllSubjectNames());
    }

    /**
     * fetch join
     * 
     * @return
     */
    @GetMapping("/v2/subjects")
    public ResponseEntity<?> getAllSubjectNames2() {
        return ResponseEntity.ok(academyService.findAllSubjectNames2());
    }

    /**
     * fetch join(하위 entity까지 가져옴)
     * 
     * @return
     */
    @GetMapping("/v3/subjects")
    public ResponseEntity<?> getAllSubjectNames3() {
        return ResponseEntity.ok(academyService.findAllSubjectNames3());
    }

    /**
     * entity graph
     * 
     * @return
     */
    @GetMapping("/v4/subjects")
    public ResponseEntity<?> getAllSubjectNames4() {
        return ResponseEntity.ok(academyService.findAllSubjectNames4());
    }

    /**
     * entity graph(하위 entity까지 가져옴)
     * 
     * @return
     */
    @GetMapping("/v5/subjects")
    public ResponseEntity<?> getAllSubjectNames5() {
        return ResponseEntity.ok(academyService.findAllSubjectNames5());
    }

}
