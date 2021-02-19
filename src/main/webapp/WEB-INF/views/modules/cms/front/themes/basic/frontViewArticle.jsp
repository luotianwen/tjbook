<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${book.title} -${book.cname}</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});

	</script>
</head>
<body>
	<div class="row">
	   <div class="span2">

		 <h4>推荐阅读</h4>
		 <ol>
			 <c:forEach items="${tjs}" var="article">
				 <li><a href="${ctx}/book/${article.id}"  title="${article.title}">${fns:abbr(article.title,16)}</a></li>
			 </c:forEach>
		 </ol>
	   </div>

	   <div class="span10">
	     <div class="row">
	       <div class="span10">
			<h3 style="color:#555555;font-size:20px;text-align:center;border-bottom:1px solid #ddd;padding-bottom:15px;margin:25px 0;">${book.title}</h3>
			<div>${book.remarks}</div>
			<div style="border-top:1px solid #ddd;padding:10px;margin:25px 0;">&nbsp; 点击数：${book.lll} &nbsp; 发布时间：<fmt:formatDate value="${book.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;  </div>
  	       </div>
  	     </div>

	     <div class="row">
	       <div class="span10">
			<h5>相关文章</h5>
			<ol><c:forEach items="${relationList}" var="article">
				<li style="float:left;width:230px;"><a href="${ctx}/book/${article.id}">${fns:abbr(article.title,30)}-${article.cname}</a></li>
			</c:forEach></ol>
	  	  </div>
  	    </div>
  	  </div>
   </div>
</body>
</html>