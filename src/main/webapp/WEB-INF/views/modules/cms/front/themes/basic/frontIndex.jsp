<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content=" ${site.description}" />
	<meta name="keywords" content=" ${site.keywords}" />
</head>
<body>

    <div class="row">
      <div class="span12">
        <h4> 推荐书</h4>
		<ul><c:forEach items="${books}" var="article">
			<li><span class="pull-right"><fmt:formatDate value="${article.createDate}" pattern="yyyy.MM.dd"/></span><a href="${ctx}/book/${article.id}"  >${fns:abbr(article.title,28)}</a></li>
		</c:forEach></ul>
      </div>

    </div>
</body>
</html>