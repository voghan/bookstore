<%@include file="/apps/bookstore/global.jsp"%>

<bedrock:component className="com.voghan.bookstore.components.content.FeaturedBooks" name="featurebooks"/>

<div class="author">
    <div class="author-image">
        <img src="${featurebooks.featureImage1}">
    </div>
    <div class="author-image">
        <img src="{featurebooks.featureImage2}">
    </div>
</div>