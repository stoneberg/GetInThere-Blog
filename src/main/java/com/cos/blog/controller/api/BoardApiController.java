package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/api/v1/board/{id}")
    public ResponseDto<?> findByIdV1(@PathVariable("id") Integer id) {
        Board board = boardService.getPostByFetchJoin(id);
        log.info("@board=======>{}", board);
        return new ResponseDto<>(HttpStatus.OK.value(), board);
    }

    @GetMapping("/api/v2/board/{id}")
    public ResponseDto<?> findByIdV2(@PathVariable("id") Integer id) {
        Board board = boardService.getPostByEntityGraph(id);
        log.info("@board=======>{}", board);
        return new ResponseDto<>(HttpStatus.OK.value(), board);
    }

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.addPost(board, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.deletePost(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        System.out.println("BoardApiController : update : id : " + id);
        System.out.println("BoardApiController : update : board : " + board.getTitle());
        System.out.println("BoardApiController : update : board : " + board.getContent());
        boardService.modifyPost(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
