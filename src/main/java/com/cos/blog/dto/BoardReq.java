package com.cos.blog.dto;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;

import lombok.Data;

public class BoardReq {

    @Data
    public static class BoardDto {

        private String title;

        private String content;

        private Integer count;

        public Board toEntity() {
            return Board.builder().title(title).content(content).build();
        }

    }

    @Data
    public static class ReplyDto {

        private String content;

        public Reply toEntity() {
            return Reply.builder().content(content).build();
        }

    }

}
