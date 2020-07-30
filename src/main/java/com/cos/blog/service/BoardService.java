package com.cos.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Integer addPost(Board board, User user) { // title, content
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
     */
    public Board getPost(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("글 상세보기 실패 : 게시글을 찾을 수 없습니다. id=%s", id)));
    }

    /**
     * 게시글 단건 조회(entity graph)
     * 
     * @param id
     * @return
     */
    public Board getPostByEntityGraph(int id) {
        return boardRepository.findEntityGraphBoardById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("글 상세보기 실패 : 게시글을 찾을 수 없습니다. id=%s", id)));
    }

    /**
     * 게시글 단건 조회(fetch join) read 함수이나 @Transactional을 명시하지 않으면 조회할 (content) CLOB
     * 컬럼 관련해서 Unable to access lob stream; nested exception is
     * org.hibernate.HibernateException: Unable to access lob stream 이 발생한다.
     * 
     * @param id
     * @return
     */
    public Board getPostByFetchJoin(int id) {
        return boardRepository.findFetchJoinBoardById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("글 상세보기 실패 : 게시글을 찾을 수 없습니다. id=%s", id)));
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
     */
    @Transactional
    public void modifyPost(Integer id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.")); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
    }

    /**
     * 게시글에 대한 댓글 추가
     * 
     * @param replay
     * @param user
     */
    @Transactional
    public Integer addReply(Integer boardId, Reply reply, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다."));
        reply.setUser(user);
        reply.setBoard(board);
        replyRepository.save(reply);
        return reply.getId();
    }

}
