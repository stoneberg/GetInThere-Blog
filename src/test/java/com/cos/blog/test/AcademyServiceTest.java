package com.cos.blog.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.test.model.Academy;
import com.cos.blog.test.model.Subject;
import com.cos.blog.test.repository.AcademyRepository;
import com.cos.blog.test.service.AcademyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyServiceTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyService academyService;

    @After
    public void cleanAll() {
        academyRepository.deleteAll();
    }

    @Before
    public void setup() {
        List<Academy> academies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Academy academy = Academy.builder().name("강남스쿨" + i).build();

            academy.addSubject(Subject.builder().name("자바웹개발" + i).build());
            academies.add(academy);
        }

        academyRepository.saveAll(academies);
    }

    @Test
    @Transactional
    public void Academy여러개를_조회시_Subject가_N1_쿼리가발생한다() throws Exception {
        // given
        List<String> subjectNames = academyService.findAllSubjectNames();

        // then
        assertThat(subjectNames.size()).isEqualTo(10);
    }
}