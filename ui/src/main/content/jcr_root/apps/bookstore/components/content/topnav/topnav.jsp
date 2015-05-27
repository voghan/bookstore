<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.functional.TopNav" name="topNav"/>

<%-- TODO: Implement navigation rendering --%>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <a class="navbar-brand" href="${topNav.brandHref}">
            <c:out value="${topNav.brandTitle}" escapeXml="false" />
        </a>
    </div>

    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <c:forEach items="${topNav.topNavLinks}" var="nav">
                <c:choose>
                    <c:when test="${nav.active}">
                        <li ><a class="active" href="${nav.href}">${nav.title}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li ><a href="${nav.href}">${nav.title}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</nav>