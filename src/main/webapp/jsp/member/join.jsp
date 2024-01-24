<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>

	<h2>회원 정보 작성</h2>

	<form method="POST" action="dojoin">
		<div>
			아이디 : <input type="text" placeholder="아이디를 입력해주세요" name="loginId" />
		</div>
		<div>
			비밀번호 :
			<input type="password" placeholder="비밀번호를 입력해주세요" name="loginPw"></input>
		</div>
		<div>
			이름 :
			<input type="text" placeholder="이름을 입력해주세요" name="name"></input>
		</div>
		<button type="submit">작성</button>
	</form>

	<div>
		<a style="color: green" href="../article/list">리스트로 돌아가기</a>
	</div>
	<div>
		<a href="../home/main">메인으로 이동</a>
	</div>

</body>
</html>