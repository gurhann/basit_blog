<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <%
        System.out.print("mesaj: " + request.getAttribute("hataMesaji"));
        if (request.getSession(false) != null) {
            response.sendRedirect("index.jsp");
        }
    %>
    <form action="LoginServlet" method="post">
        <table>
            <tr>
                <td>Kullanıcı Adı:</td>
                <td><input type="text" name="k_adi" /></td>
            </tr>
            <tr>
                <td>Şifre</td>
                <td><input type="password" name="sifre" /></td>
            </tr>
            <tr>
                <td colspan="2">
            <input type="submit" value="Giriş Yap" />
            </td>
            </tr>
        </table>
    </form>
<div class="error">
    <%=request.getAttribute("hataMesaji") %>
</div>
${hataMesaji}
</body>
</html>