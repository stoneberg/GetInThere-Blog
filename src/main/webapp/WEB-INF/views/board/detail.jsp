<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>

    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br />
    <br />
    <div>
        글 번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i>${board.user.username}
        </i></span>
    </div>
    <br />
    <div>
        <h3>${board.title}</h3>
    </div>
    <hr />
    <div>
        <div>${board.content}</div>
    </div>
    <hr />
    
    <!--  reply -->
    <div class="card">
        <div class="card-body"><textarea class="form-control noresize" maxlength="200"></textarea></div>
        <div class="card-footer"><button class="btn btn-primary">등록</button></div>
    </div>
    
    <!-- view controller에서 반환된 model은 직접 nested object를 호출하지 않는 이상 순환 참조가 발생하지는 않는다. -->
    <!-- board.replies.board로 호출할 경우 순환 참조가 발생함단.  -->
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">
        <c:forEach var="reply" items="${board.replies}">
            <li class="list-group-item d-flex justify-content-between">
                <div>${reply.content}</div>
                <div class="d-flex">
                    <div class="mr-3 font-italic">${reply.user.username}</div>
                    <button class="badge">삭제</button>
                 </div>
            </li>
        </c:forEach>
        </ul>
    </div>
    
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


