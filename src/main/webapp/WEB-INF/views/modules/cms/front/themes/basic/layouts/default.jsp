<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:title default="欢迎光临"/> - ${site.title}  </title>
	<%@include file="/WEB-INF/views/modules/cms/front/include/head.jsp" %>
	<sitemesh:head/>
</head>
<body>
	<div class="navbar navbar-fixed-top" style="position:static;margin-bottom:10px;">
      <div class="navbar-inner">
        <div class="container">
          <c:choose>
   			<c:when test="${not empty site.logo}">
   				<img alt="${site.title}" src="${site.logo}" class="container" onclick="location='${ctx}/index-${site.id}${fns:getUrlSuffix()}'">
   			</c:when>
   			<c:otherwise><a class="brand" href="${ctx}/index-${site.id}${fns:getUrlSuffix()}">${site.title}</a></c:otherwise>
   		  </c:choose>

          <div class="nav-collapse">
           <%-- <ul id="main_nav" class="nav nav-pills">
             	<li class="${not empty isIndex && isIndex ? 'active' : ''}"><a href="${ctx}/index-1${fns:getUrlSuffix()}"><span>${site.id eq '1'?'首　 页':'返回主站'}</span></a></li>
				<c:forEach items="${fnc:getMainNavList(site.id)}" var="category" varStatus="status"><c:if test="${status.index lt 6}">
                    <c:set var="menuCategoryId" value=",${category.id},"/>
		    		<li class="${requestScope.category.id eq category.id||fn:indexOf(requestScope.category.parentIds,menuCategoryId) ge 1?'active':''}"><a href="${category.url}" target="${category.target}"><span>${category.name}</span></a></li>
		    	</c:if></c:forEach>

            </ul>--%>
			   <ul id="main_nav" class="nav nav-pills navbar-form pull-right">

				   <c:if test="${ empty sessionScope.buser}">
				   <li> <a href="${ctx}/tologin"><span>登录</span></a></li>
				   <li> <a href="${ctx}/toregister"><span>注册</span></a></li>
				   </c:if>
				   <c:if test="${not empty sessionScope.buser}">
					   <li><a  ><span>欢迎${sessionScope.buser.name}</span></a></li>
					   <li> <a href="${ctx}/loginout"><span>退出</span></a></li>
				   </c:if>
			   </ul>


            <form class="navbar-form pull-right" action="${ctx}/search" method="get">
              	<input type="text" name="q" maxlength="20" style="width:65px;" placeholder="全站搜索..." value="${q}">
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
	<div class="container">
		<div class="content">
			<sitemesh:body/>
		</div>
		<hr style="margin:20px 0 10px;">
		<footer>
			 	<div class="pull-right">${fns:getDate('yyyy年MM月dd日 E')}</div><div class="copyright">${site.copyright}</div>
      	</footer>
    </div> <!-- /container -->
</body>
</html>