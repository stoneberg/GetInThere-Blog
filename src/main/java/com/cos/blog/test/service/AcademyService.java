package com.cos.blog.test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cos.blog.test.model.Academy;
import com.cos.blog.test.repository.AcademyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AcademyService {

    private AcademyRepository academyRepository;

    public AcademyService(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    /**
     * Lazy N + 1
     * 
     * @return
     */
    public List<String> findAllSubjectNames() {
        return extractSubjectNames(academyRepository.findAll());
    }

    /**
     * fetch join
     * 
     * @return
     */
    public List<String> findAllSubjectNames2() {
        return extractSubjectNames(academyRepository.findAllJoinFetch());
    }

    /**
     * sub entity fetch join
     */
    public List<String> findAllSubjectNames3() {
        return extractSubjectNames(academyRepository.findAllWithTeacher());
    }

    /**
     * entity graph
     * 
     * @return
     */
    public List<String> findAllSubjectNames4() {
        return extractSubjectNames(academyRepository.findAllEntityGraph());
    }

    /**
     * sub entity graph
     * 
     * @return
     */
    public List<String> findAllSubjectNames5() {
        return extractSubjectNames(academyRepository.findAllEntityGraphWithTeacher());
    }

    /**
     * Lazy Load를 수행하기 위해 메소드를 별도로 생성 N + 1 발생!!!!!!!
     * https://jojoldu.tistory.com/165
     */
    private List<String> extractSubjectNames(List<Academy> academies) {
        log.info(">>>>>>>>[모든 과목을 추출한다]<<<<<<<<<");
        log.info("Academy Size : {}", academies.size());
        log.info("@academy.subject.names====> N + 1");
        // return academies.stream().map(a ->
        // a.getSubjects().get(0).getName()).collect(Collectors.toList());
        return academies.stream().map(a -> a.getName()).collect(Collectors.toList());
        // return academies.stream().map(a ->
        // a.getSubjects()).flatMap(Collection::stream).map(Subject::getName).collect(Collectors.toList());

    }
}