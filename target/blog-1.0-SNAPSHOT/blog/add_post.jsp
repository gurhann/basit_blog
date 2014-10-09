<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Yazı Ekle</title>
</head>
<body>
    <form action="/blog/AddPostServlet" method="post">
        Baslik : <input type="text" name="blogBaslik" /><br />
        İçerik : <textarea rows="3" cols="50" name="blogIcerik"></textarea> <br />
        <input type="submit" value="Gönder" />
    </form>
    ${mesaj}
</body>
</html>