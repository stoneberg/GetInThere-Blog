package com.cos.blog.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.test.model.Academy;

/**
 * JoinFetch는 Inner Join, Entity Graph는 Outer Join이라는 차이점
 */
public interface AcademyRepository extends JpaRepository<Academy, Integer> {

    /**
     * 1. join fetch를 통한 조회
     */
    // @Query("select a from Academy a join fetch a.subjects") // => 카타시안 곱 발생
    @Query("select DISTINCT a from Academy a join fetch a.subjects")
    List<Academy> findAllJoinFetch();

    /**
     * 2. Academy+Subject+Teacher를 join fetch로 조회
     */
    // @Query("select a from Academy a join fetch a.subjects s join fetch
    // s.teacher") // => 카타시안 곱 발생
    @Query("select DISTINCT a from Academy a join fetch a.subjects s join fetch s.teacher")
    List<Academy> findAllWithTeacher();

    /**
     * 3. @EntityGraph
     */
    @EntityGraph(attributePaths = "subjects")
    // @Query("select a from Academy a") // => 쿼리에서는 카타시안 곱 발생은 하지만 결과는 distinct해서
    // 가져옴
    @Query("select DISTINCT a from Academy a")
    List<Academy> findAllEntityGraph();

    /**
     * 4. Academy+Subject+Teacher를 @EntityGraph 로 조회
     */
    @EntityGraph(attributePaths = { "subjects", "subjects.teacher" })
    // @Query("select a from Academy a") // => 쿼리에서는 카타시안 곱 발생은 하지만 결과는 distinct해서
    // 가져옴
    @Query("select DISTINCT a from Academy a")
    List<Academy> findAllEntityGraphWithTeacher();

}
