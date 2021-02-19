<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>全站搜索</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="${site.description}" />
	<meta name="keywords" content="${site.keywords}" />
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<style type="text/css">
		form.search{margin:12px 20px 5px;} .page{margin:20px;}
		form.search input.txt{padding:3px;font-size:16px;width:300px;margin:5px;}
		form.search select.txt{padding:3px;font-size:16px;width:308px;margin:5px;}
		form.search input.txt.date{width:133px;}
		form.search .sel{margin-bottom:8px;padding:0 0 10px 5px;border-bottom:1px solid #efefef;font-size:14px;} form.search .act{font-weight:bold;}
		form.search .btn{padding:3px 18px;*padding:1px 0 0;font-size:16px;}
		dl.search{line-height:25px;border-bottom:1px solid #efefef;margin:10px 20px 20px;}
		dl.search dt{border-top:1px solid #efefef;padding:8px 5px 0px;font-size:16px;}
		dl.search dt a.title{color:#0000cc;text-decoration:underline;}
		dl.search dd{margin:0 5px 10px;font-size:14px;color:#555}
		dl.search dd span,dl.search dd a{font-size:12px;color:#008000;}
		dl.search .highlight{color:#DF0037;}
		dl.search dd span.highlight{color:#DF0037;font-size:14px;}
		dl.search dd span.info span.highlight{color:#DF0037;font-size:13px;}
	</style>
	<c:if test="${not empty message}"><script type="text/javascript">alert("${message}");</script></c:if>
</head>
<body>
	<form:form id="searchForm" method="get" class="search">
		<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
				<input type="text" name="q" value="${q}" class="txt"/>
				<input type="submit" value="搜  索" class="btn"  />

	</form:form>
	<dl class="search">

			<c:forEach items="${page.list}" var="article">
				<dt><a href="${ctx}/book/${article.id}" class="title" target="_blank">${article.title}</a></dt>
				<dd>${article.remarks}<span class="info"><br/> &nbsp; 浏览数：${article.lll} &nbsp; 发布时间：<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>  </span>
					&nbsp;&nbsp;<a href="${ctx}/book/${article.id}" target="_blank">查看全文</a><br/></dd>
			</c:forEach>


		<c:if test="${fn:length(page.list) eq 0}">
			<dt><c:if test="${empty q}">请键入要查找的关键字。</c:if><c:if test="${not empty q}">抱歉，没有找到与“${q}”相关内容。</c:if><br/><br/>建议：</dt>
			<dd><ul><li>检查输入是否正确；</li><li>简化输入词；</li><li>尝试其他相关词，如同义、近义词等。</li></ul></dd>
		</c:if>
	</dl>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</body>
</html>