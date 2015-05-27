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
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
        </ul>
    </div>
</nav>