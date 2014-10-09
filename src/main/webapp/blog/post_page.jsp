<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Blog Yazısı</title>
</head>
<script type="text/javascript">
    function aktifMi() {
        var yorum = document.getElementById("yorum");
        var button = document.getElementById("submitButton");

        if (yorum.value.length <= 200) {
            button.disabled = false;
        } else if (yorum.value.length > 200) {
            button.disabled = true;
        }
    }
</script>
<body>
<div class="postTitle">
    ${post.baslik } 
</div>
<div class="postContent">
    ${post.icerik } 
</div>
<div class="postDate">
    ${post.tarih}
</div>
<form action="AddCommentServlet" method="post">
    <div class="addComment">
        <textarea rows="2" cols="60" name="yorum" id="yorum" onkeyup="aktifMi();
                                return false;"></textarea>
        <input type="submit" value="Yorum Yap" id="submitButton" />
        <input type="hidden" value="${post.blogID}" name="blogID" />
    </div>
</form>
<div class="comments">
    <c:forEach items="${comments}" var="comment">
        <div class="comment">
            ${comment.icerik}<br/>
            ${authorNames[comment.yazarID]} - ${comment.tarih}
        </div>
    </c:forEach>
</div>

</body>
</html>