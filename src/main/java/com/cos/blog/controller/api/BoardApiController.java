package com.cos.blog.controller.api;

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
import com.cos.blog.handler.CommonAppException;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    /**
     * 게시글 API
     */

    /**
     * 게시글 조회: 단건
     * 
     * @param id
     * @return
     * @throws CommonAppException
     */
    @GetMapping("/api/v1/board/{id}")
    public ResponseDto<?> findPostByIdV1(@PathVariable("id") Integer id) throws CommonAppException {
        Board board = boardService.getPostByFetchJoin(id);
        return new ResponseDto<>(HttpStatus.OK, board);
    }

    /**
     * 게시글 조회: 목록
     * 
     * @param id
     * @return
     * @throws CommonAppException
     */
    @GetMapping("/api/v2/board/{id}")
    public ResponseDto<?> findPostByIdV2(@PathVariable("id") Integer id) throws CommonAppException {
        Board board = boardService.getPostByEntityGraph(id);
        return new ResponseDto<>(HttpStatus.OK, board);
    }

    /**
     * 게시글 작성
     * 
     * @param boardDto
     * @param principal
     * @return
     */
    @PostMapping("/api/board")
    public ResponseDto<?> savePost(@RequestBody BoardDto boardDto, @AuthenticationPrincipal PrincipalDetail principal) {
        return new ResponseDto<>(HttpStatus.CREATED, boardService.addPost(boardDto, principal.getUser()));
    }

    /**
     * 게시글 삭제
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<?> deletePostById(@PathVariable("id") Integer id) {
        boardService.deletePost(id);
        return new ResponseDto<>(HttpStatus.OK, id);
    }

    /**
     * 게시글 수정
     * 
     * @param id
     * @param board
     * @return
     * @throws CommonAppException
     */
    @PutMapping("/api/board/{id}")
    public ResponseDto<?> updatePost(@PathVariable("id") Integer id, @RequestBody BoardDto boardDto) throws CommonAppException {
        log.info("BoardApiController : update : id : " + id);
        log.info("BoardApiController : update : board : " + boardDto.getTitle());
        log.info("BoardApiController : update : board : " + boardDto.getContent());
        boardService.modifyPost(id, boardDto);
        return new ResponseDto<>(HttpStatus.OK, id);
    }

    /**
     * 댓글 관련 API
     */

    /**
     * 댓글 추가
     * 
     * @param boardId
     * @param replyDto
     * @param principal
     * @return
     * @throws CommonAppException
     */
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<?> saveReply(@PathVariable("boardId") Integer boardId, @RequestBody BoardReq.ReplyDto replyDto,
            @AuthenticationPrincipal PrincipalDetail principal) throws CommonAppException {
        return new ResponseDto<>(HttpStatus.OK, boardService.addReply(boardId, replyDto, principal.getUser()));
    }


    /**
     * 댓글 삭제
     * 
     * @param replyId
     * @param principal
     * @return
     * @throws CommonAppException
     */
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<?> deleteReplyById(@PathVariable("replyId") Integer replyId, @AuthenticationPrincipal PrincipalDetail principal)
            throws CommonAppException {
        boardService.deleteReply(replyId, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK);
    }

}
