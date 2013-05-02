<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<table border="solid">
	<tr>
        <td>_id</td>
		<td>名称</td>
		<td>标题</td>
	</tr>
	<c:forEach items="${crawlDatas}" var="crawlData">
		<tr>
			<td>${crawlData.name}</td>
			<td><a href="/icr/QueryCutomerRiskInfoContent.do?id=${crawlData._id}" target="_blank">${crawlData.title}</a></td>
		</tr>
	</c:forEach>
</table>