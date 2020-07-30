package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @EntityGraph(attributePaths = { "user", "replies", "replies.user" }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select DISTINCT b from Board b where b.id=:id")
    Optional<Board> findEntityGraphBoardById(@Param("id") Integer id);

    @Query("select DISTINCT b from Board b left join fetch b.user left join fetch b.replies r left join fetch r.user where b.id=:id")
    Optional<Board> findFetchJoinBoardById(@Param("id") Integer id);

}
