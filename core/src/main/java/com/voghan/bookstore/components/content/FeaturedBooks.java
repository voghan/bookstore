package com.voghan.bookstore.components.content;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;

@Component(value = "Feature Book", group = "bookstore", tabs = {
        @Tab(title = "Feature 1"),
        @Tab(title = "Feature 2")
})
public class FeaturedBooks extends AbstractComponent {

    @DialogField( fieldLabel = "Feature Image", tab = 1)
    @Html5SmartImage(fileNameParameter = "image/fileName", fileReferenceParameter = "image/fileReference", allowUpload = false)
    public String getFeatureImage1() {
        return getImageSource().or("notfound.png");
    }

    @DialogField( fieldLabel = "Feature Image", tab = 2)
    @Html5SmartImage(fileNameParameter = "image/fileName", fileReferenceParameter = "image/fileReference", allowUpload = false)
    public String getFeatureImage2() {
        return getImageSource().or("notfound.png");
    }
}
