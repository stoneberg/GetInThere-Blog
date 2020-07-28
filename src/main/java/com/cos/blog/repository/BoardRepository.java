package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @EntityGraph(attributePaths = { "user", "replies", "replies.user" })
    @Query("select DISTINCT b from Board b")
    Optional<Board> findEntityGraphBoardById(int id);

    @Query("select DISTINCT b from Board b join fetch b.user join fetch b.replies r join fetch r.user")
    Optional<Board> findFetchJoinBoardById(int id);

}
