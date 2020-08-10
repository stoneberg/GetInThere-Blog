const blogBoard = {
    
    /**
     * 초기화
     */
    init: function() {
        $("#btn-save").on("click", () => {
            console.log("post-save==========>");
            this.savePost();
        });
        $("#btn-delete").on("click", () => {
            console.log("post-delete========>");
            this.deletePost();
        });
        $("#btn-update").on("click", () => {
            console.log("post-update========>");
            this.updatePost();
        });
        $("#btn-reply-save").on("click", () => {
            console.log("reply-save========>");
            this.saveReply();
        });
        $(".remove-reply").on("click", (e) => {
            console.log("reply-delete========>");
            const $this = $(e.currentTarget);
            this.deleteReply($this);
        });
    },

    /**
     * 게시글 저장
     */
    savePost: function()  {
        const data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        
        if (!data.title) {
            alert("title must not be empty!");
            return;
        }
        
        if (!data.content) {
            alert("content must not be empty!");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            console.log("res==========>", res);
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error.responseJSON.data));
        });
    },

    /**
     * 게시글 삭제
     */
    deletePost: function() {
        const id = $("#id").text();
        // confirm
        if (!confirm("삭제 시 하위 댓글까지 모두 삭제됩니다. 삭제하시겠습니까?")) {
            return;
        }

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function(res) {
            console.log("res==========>", res);
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error.responseJSON.data));
        });
    },

    /**
     * 게시글 수정
     */
    updatePost: function() {
        const id = $("#id").val();
        const data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        
        if (!data.title) {
            alert("title must not be empty!");
            return;
        }
        
        if (!data.content) {
            alert("content must not be empty!");
            return;
        }

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            console.log("res==========>", res);
            alert("글수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error.responseJSON.data));
        });
    },

    /**
     * 댓글 작성
     */
    saveReply: function() {
        const boardId = $("#boardId").val();
        const data = {
            content: $("#reply-content").val()
        };
        
        if (!data.content) {
            alert("content must not be empty!");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/api/board/" + boardId + "/reply",
            //url: `/api/board/${boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            console.log("res==========>", res);
            alert("댓글 작성이 완료되었습니다.");
            location.href = "/board/" + boardId;
            //location.href = `/board/${boardId}`;
        }).fail(function(error) {
            alert(JSON.stringify(error.responseJSON.data));
        });
    },
    
    /**
     * 댓글 삭제
     */
     deleteReply: function($this) {
        const boardId = $this.closest('li').data('board-id')
        const replyId = $this.closest('li').data('reply-id')
        console.log("@boardId/replyId====>", boardId, replyId);

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + boardId + "/reply/" + replyId,
            dataType: "json"
        }).done(function(res) {
            console.log("res==========>", res);
            alert("삭제가 완료되었습니다.");
            location.href = "/board/" + boardId;
            //location.href = `/board/${boardId}`;
        }).fail(function(error) {
            alert(JSON.stringify(error.responseJSON.data));
        });
    },
}

blogBoard.init();
