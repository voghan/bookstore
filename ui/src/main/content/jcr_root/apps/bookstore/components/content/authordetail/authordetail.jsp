<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.content.AuthorDetail" name="author"/>
<div class="author">
    <div class="author-image">
        <img src="${author.authorImage}">
    </div>
    <div class="author-detail">
        ${author.authorName}
        ${author.authorBio}
    </div>


    <div class="author-tags">
        <c:forEach items="${author.tags}" var="tag">
            ${tag}
        </c:forEach>


    </div>
</div>