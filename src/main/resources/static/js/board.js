const blogBoard = {
    
    /**
     * 초기화
     */
    init: function() {
        $("#btn-save").on("click", () => {
            console.log("post-save==========>")
            this.savePost();
        });
        $("#btn-delete").on("click", () => {
            console.log("post-delete========>")
            this.deletePost();
        });
        $("#btn-update").on("click", () => {
            console.log("post-update========>")
            this.updatePost();
        });
        $("#btn-reply-save").on("click", (e) => {
            console.log("reply-save========>")
            this.saveReply();
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

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    /**
     * 게시글 삭제
     */
    deletePost: function() {
        const id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function(resp) {
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
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

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("글수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
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

        $.ajax({
            type: "POST",
            url: "/api/board/" + boardId + "/reply",
            //url: `/api/board/${boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("댓글 작성이 완료되었습니다.");
            location.href = "/board/" + boardId;
            //location.href = `/board/${boardId}`;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
}

blogBoard.init();
