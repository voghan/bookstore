<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.content.BookSearch" name="search"/>

<form id="bookSearch">
    <input name="searchKeyword" type="text" id="searchKeyword"></input>

        <c:if test="${not empty search.formTags}">
            <fieldset id="tags">
                <c:forEach items="${search.formTags}" var="tag">
                    <label>${tag.title}</label>
                    <input type="checkbox" name="${tag.title}" value="${tag.tagID}" />
                </c:forEach>
            </fieldset>
        </c:if>
    <input type="submit" value="Search" />
	<input id="clearResults" type="button" value="Clear Results" />
</form>


<div class="book-results">

	<ul class="book-results-list">
    </ul>

    <ul style="display: none;">
        <li id="book-template" class="results-book">
            <p class="results-book-title">
            	<a class="results-book_url" href="#" ><span data-selector="bookTitle"></span></a>
            	by <span data-selector="bookAuthor"></span>
            </p>
        </li>
    </ul>


</div>

<script>

$(document).ready(function () {
	booksSearch.init();
});
</script>