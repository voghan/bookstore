<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.content.BookList" name="booklist"/>

<div class="book-list">

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>${booklist.contentTitle}</h1>
            ${booklist.contentDescription}
        </div>
    </div>
    <div class="row">
        <c:forEach items="${booklist.books}" var="book">
            <div class="col-md-3">
                <div class="book-image">
                    <img src="${book.bookCover}" class="img-responsive">
                </div>
                <div class="book-detail">
                    <a href="${book.href}">${book.bookTitle}</a>
                    ${book.authorName}
                </div>
            </div>
        </c:forEach>
    </div>
</div>