<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.content.AuthorList" name="authorlist"/>

<div class="author-list">

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>${authorlist.contentTitle}</h1>
            ${authorlist.contentDescription}
        </div>
    </div>
    <div class="row">
        <c:forEach items="${authorlist.authors}" var="author">
            <div class="col-md-4">
                <div class="author-image">
                    <img src="${author.authorImage}" class="img-responsive">
                </div>
                <div class="author-detail">
                    ${author.authorName}
                </div>
            </div>
        </c:forEach>
    </div>
</div>