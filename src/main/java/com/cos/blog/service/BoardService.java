package com.cos.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.BoardReq;
import com.cos.blog.dto.BoardReq.BoardDto;
import com.cos.blog.handler.CommonAppException;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    /**
     * 게시글 등록
     * 
     * @param board
     * @param user
     */
    @Transactional
    public Integer addPost(BoardDto boardDto, User user) { // title, content
        Board board = boardDto.toEntity();
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
        return board.getId();
    }

    /**
     * 게시글 전체 조회
     * 
     * @param pageable
     * @return
     */
    public Page<Board> getPosts(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    /**
     * 게시글 단건 조회
     * 
     * @param id
     * @return
     * @throws CommonAppException
     */
    public Board getPost(int id) throws CommonAppException {
        return boardRepository.findById(id)
                .orElseThrow(() -> new CommonAppException(String.format("글 상세보기 실패 : 게시글을 찾을 수 없습니다. id=%s", id)));
    }

    /**
     * 게시글 단건 조회(entity graph)
     * 
     * @param id
     * @return
     * @throws CommonAppException
     */
    public Board getPostByEntityGraph(int id) throws CommonAppException {
        return boardRepository.findEntityGraphBoardById(id)
                .orElseThrow(() -> new CommonAppException(String.format("글 상세보기 실패 : 게시글을 찾을 수 없습니다. id=%s", id)));
    }

    /**
     * 게시글 단건 조회(fetch join) read 함수이나 @Transactional을 명시하지 않으면 조회할 (content) CLOB
     * 컬럼 관련해서 Unable to access lob stream; nested exception is
     * org.hibernate.HibernateException: Unable to access lob stream 이 발생한다.
     * 
     * @param id
     * @return
     * @throws CommonAppException
     */
    public Board getPostByFetchJoin(int id) throws CommonAppException {
        return boardRepository.findFetchJoinBoardById(id)
                .orElseThrow(() -> new CommonAppException(String.format("글 상세보기 실패 : 게시글을 찾을 수 없습니다. id=%s", id)));
    }

    /**
     * 게시글 삭제
     * 
     * @param id
     */
    @Transactional
    public void deletePost(Integer id) {
        System.out.println("글삭제하기 : " + id);
        boardRepository.deleteById(id);
    }

    /**
     * 게시글 수정
     * 
     * @param id
     * @param requestBoard
     * @throws CommonAppException
     */
    @Transactional
    public void modifyPost(Integer id, BoardDto boardDto) throws CommonAppException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CommonAppException("글 찾기 실패 : 게시글을 찾을 수 없습니다.")); // 영속화 완료
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 1차 캐시 dirty checking - 자동 업데이트가 됨.
        // db flush
    }

    /**
     * 게시글에 대한 댓글 추가
     * 
     * @param replay
     * @param user
     * @throws CommonAppException
     */
    @Transactional
    public Integer addReply(Integer boardId, BoardReq.ReplyDto replyDto, User user) throws CommonAppException {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CommonAppException("글 찾기 실패 : 게시글을 찾을 수 없습니다."));
        Reply reply = replyDto.toEntity();
        reply.setUser(user);
        reply.setBoard(board);
        replyRepository.save(reply);
        return reply.getId();
    }

    /*
     * 게시글에 대한 댓글 삭제
     */
    @Transactional
    public void deleteReply(Integer replyId, User user) throws CommonAppException {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new CommonAppException("글 찾기 실패 : 댓글을 찾을 수 없습니다."));
        User replyUser = reply.getUser();

        if (!replyUser.equals(user)) {
            replyRepository.deleteById(replyId);
        }

    }

}
