<%@include file="/apps/bookstore/global.jsp"%>
<%@ page import="com.day.cq.wcm.api.WCMMode,
                 org.apache.commons.lang3.StringEscapeUtils" %><%
    String favIcon = currentDesign.getPath() + "/favicon.ico";
    if (resourceResolver.getResource(favIcon) == null) {
        favIcon = null;
    }
    
    pageContext.setAttribute("wcmmode", WCMMode.fromRequest(slingRequest));
	pageContext.setAttribute("wcmmodeDisabled", WCMMode.DISABLED);
%>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="keywords" content="<%= xssAPI.encodeForHTMLAttr(WCMUtils.getKeywords(currentPage, false)) %>">
    <meta name="description" content="<%= xssAPI.encodeForHTMLAttr(properties.get("jcr:description", "")) %>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

    <cq:include script="/libs/wcm/core/components/init/init.jsp"/>
    <cq:includeClientLib css="bookstore"/>
    <cq:includeClientLib js="bookstore"/>

    <title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>
</head>
