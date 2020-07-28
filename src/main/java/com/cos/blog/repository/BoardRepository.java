package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @EntityGraph(value = "board.replies.user", type = EntityGraphType.LOAD)
    Optional<Board> findBoardById(int id);

}
