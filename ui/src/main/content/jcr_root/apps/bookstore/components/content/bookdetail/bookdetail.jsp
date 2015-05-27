<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.content.BookDetail" name="book"/>
<div class="book">
    <div class="book-image">
        <img src="${book.bookCover}">
    </div>
    <div class="book-detail">
        ${book.bookTitle}

        <div class="book-authors">
            <c:forEach items="${book.bookAuthors}" var="author">
                <a href="${author.href}">${author.authorName}</a>
            </c:forEach>
        </div>

        ${book.bookDescription}
    </div>


    <div class="book-authors">
        <c:forEach items="${book.authorTags}" var="tag">
            ${tag}
        </c:forEach>

        <c:forEach items="${book.genreTags}" var="tag">
            ${tag}
        </c:forEach>
    </div>
</div>