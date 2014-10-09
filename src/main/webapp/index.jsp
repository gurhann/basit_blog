<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <title>Ana Sayfa</title>
</head>
<body>

<div class="leftSide">
    <a href="blog/add_post.jsp">Yazı Ekle</a><br/>
    <a href="LogoutServlet">Çıkış Yap</a>
</div>
<div class="rightSide">

    <c:forEach items="${allPost}" var="post">
        <div class="postTitles">
            <a href="PostPageServlet?post_id=${post.blogID}">${post.baslik}</a> 
            <a href="ProfilServlet?user_id=${post.yazarID}">${authorNames[post.yazarID]} </a>  tarafından yazıldı.<br/>
        </div>
    </c:forEach>

</div> 
</body>
</html>