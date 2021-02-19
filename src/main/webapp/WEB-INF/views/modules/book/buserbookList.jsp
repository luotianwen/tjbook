<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户图书管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/book/buserbook/">用户图书管理列表</a></li>
		<shiro:hasPermission name="book:buserbook:edit"><li><a href="${ctx}/book/buserbook/form">用户图书管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="buserbook" action="${ctx}/book/buserbook/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户：</label>
				<form:input path="u.name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>书：</label>
				<form:input path="b.title" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户</th>
				<th>书</th>
				<th>创建时间</th>
				<shiro:hasPermission name="book:buserbook:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="buserbook">
			<tr>
				<td><a href="${ctx}/book/buserbook/form?id=${buserbook.id}">
					${buserbook.u.name}
				</a></td>
				<td>
					${buserbook.b.title}
				</td>
				<td>
					<fmt:formatDate value="${buserbook.cratetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="book:buserbook:edit"><td>
    				<a href="${ctx}/book/buserbook/form?id=${buserbook.id}">修改</a>
					<a href="${ctx}/book/buserbook/delete?id=${buserbook.id}" onclick="return confirmx('确认要删除该用户图书管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>