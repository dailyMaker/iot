<%@ page language="java" contentType="text/plain; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="result" value="${code}" />
<c:choose>
    <c:when test="${result eq '1'}">
        ${user.uid}님 가입을 환영합니다.
    </c:when>
    <c:otherwise>
        가입에 실패했습니다.
    </c:otherwise>
</c:choose>

{
	"code":"${ code }"
}    