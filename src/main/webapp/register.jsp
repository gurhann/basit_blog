<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function sifreDogrula() {
		var sifre1 = document.getElementById("sifre1");
		var sifre2 = document.getElementById("sifre2");
		var mesaj = document.getElementById("mesaj");

		
		if (sifre1.value===sifre2.value) {
			sifre2.style.backgroundColor="#66cc66";
			
			mesaj.innerHTML = "Şifreler Uyuştu";
			
		} else {
			sifre2.style.backgroundColor="#ff6666";
			mesaj.innerHTML = "Şifreler Uyuşmuyor";
		}
	}
</script>
<body>
	<%
		if (request.getSession(false) != null) {
			response.sendRedirect("index.jsp");
		}
	%>
	
	<form action="RegisterServlet" method="post" >
		<table>
			<tr>
				<td>Kullanıcı Adi:</td>
				<td><input type="text" name="k_adi" /></td>
			</tr>
			<tr>
				<td>Şifre:</td>
				<td><input type="password" name="sifre1" id="sifre1" /></td>
			</tr>
			<tr>
				<td>Tekrar Girin: </td>
				<td><input type="password" name="sifre2" id="sifre2" onkeyup="sifreDogrula(); return false;" />
				<span id="mesaj"></span></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="reset" value="Temizle" />
					<input type="submit" value="Kayıt Ol" />			
				</td>
			</tr>
		</table>
		${mesaj}
	</form>
</body>
</html>