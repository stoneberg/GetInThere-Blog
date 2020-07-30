package com.cos.blog.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.handler.CommonAppException;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // save form 이동 - USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 컨트롤로에서 세션을 어떻게 찾는지?
    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({ "", "/" })
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.getPosts(pageable));
        return "index"; // viewResolver 작동!!
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Integer id, Model model) throws CommonAppException {
        log.info("findById=====================>{}", id);
        // model.addAttribute("board", boardService.getPost(id));
        // Board board = boardService.getPostByEntityGraph(id);
        Board board = boardService.getPostByFetchJoin(id);
        log.info("@board=======>{}", board);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, Model model) throws CommonAppException {
        model.addAttribute("board", boardService.getPost(id));
        return "board/updateForm";
    }

}
