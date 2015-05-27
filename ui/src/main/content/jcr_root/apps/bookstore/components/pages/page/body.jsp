<!--  body.jsp -->
<%@page import="org.apache.sling.settings.SlingSettingsService"%>
<%@include file="/apps/bookstore/global.jsp"%>

<body>

    <div class="container">
        <cq:include path="topnav" resourceType="bookstore/components/content/topnav"/>
        <cq:include script="content.jsp"/>
        <cq:include script="footer.jsp"/>
    </div>

</body>
