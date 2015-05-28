<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.content.BookList" name="booklist"/>

<div class="book-list">

    <div class="row">
        <div class="col-md-6 col-md-offset-4">
            <h1>${booklist.contentTitle}</h1>
            ${booklist.contentDescription}
        </div>
    </div>
    <div class="row">
        <c:forEach items="${booklist.books}" var="book">
            <div class="col-md-4">
                <div class="book-image">
                    <img src="${book.bookCover}" class="img-responsive">
                </div>
                <div class="book-detail">
                    <p><a href="${book.href}">${book.bookTitle}</a></p>

                    <c:forEach items="${book.bookAuthors}" var="author">
                        <p><a href="${author.href}">${author.authorName}</a></p>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
</div>