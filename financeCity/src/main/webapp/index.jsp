<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<h2>Hello World!</h2>
<s:form action="test" method="post">
	<input type="text" name="txt">
	<input type="submit">
</s:form>
</body>
</html>