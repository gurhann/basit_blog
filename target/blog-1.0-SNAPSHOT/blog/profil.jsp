<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <title>Profil</title>
</head>
<body>
    ${authorName} adlı kişinin yazılarını görmektesiniz.
    <c:forEach items="${posts}" var="post">
    <div class="postTitles">
        <a href="/blog/PostPageServlet?post_id=${post.blogID}">${post.baslik}</a> - ${post.tarih} 
    </div>
</c:forEach>
</body>
</html>