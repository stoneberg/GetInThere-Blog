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
import com.cos.blog.dto.BoardReq;
import com.cos.blog.dto.BoardReq.BoardDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시글 API
     */

    @GetMapping("/api/v1/board/{id}")
    public ResponseDto<?> findPostByIdV1(@PathVariable("id") Integer id) {
        Board board = boardService.getPostByFetchJoin(id);
        return new ResponseDto<>(HttpStatus.OK, board);
    }

    @GetMapping("/api/v2/board/{id}")
    public ResponseDto<?> findPostByIdV2(@PathVariable("id") Integer id) {
        Board board = boardService.getPostByEntityGraph(id);
        return new ResponseDto<>(HttpStatus.OK, board);
    }

    @PostMapping("/api/board")
    public ResponseDto<?> savePost(@RequestBody BoardDto boardDto, @AuthenticationPrincipal PrincipalDetail principal) {
        return new ResponseDto<>(HttpStatus.CREATED, boardService.addPost(boardDto, principal.getUser()));
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<?> deletePostById(@PathVariable("id") Integer id) {
        boardService.deletePost(id);
        return new ResponseDto<>(HttpStatus.OK, id);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<?> updatePost(@PathVariable("id") Integer id, @RequestBody Board board) {
        log.info("BoardApiController : update : id : " + id);
        log.info("BoardApiController : update : board : " + board.getTitle());
        log.info("BoardApiController : update : board : " + board.getContent());
        boardService.modifyPost(id, board);
        return new ResponseDto<>(HttpStatus.OK, id);
    }

    /**
     * 댓글 관련 API
     */

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<?> saveReply(@PathVariable("boardId") Integer boardId, @RequestBody BoardReq.ReplyDto replyDto,
            @AuthenticationPrincipal PrincipalDetail principal) {
        return new ResponseDto<>(HttpStatus.OK, boardService.addReply(boardId, replyDto, principal.getUser()));
    }

}
