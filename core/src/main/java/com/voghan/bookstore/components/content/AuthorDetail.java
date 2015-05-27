package com.voghan.bookstore.components.content;


import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.bedrock.core.constants.ComponentConstants;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;

import java.util.List;

@Component(value = "Author Detail", group = "bookstore", tabs = {
        @Tab(title = "Author"),
        @Tab(title = "Image")
})
public class AuthorDetail extends AbstractComponent {
    public static final String RESOURCE_TYPE = "bookstore/components/content/authordetail";

    @DialogField(fieldLabel = "Author Name", ranking = 1, tab = 1)
    public String getAuthorName() {
        return get("authorName", "");
    }

    @DialogField(fieldLabel = "Author Bio", ranking = 2, tab = 1)
    @RichTextEditor
    public String getAuthorBio() {
        return get("authorBio","");
    }

    @DialogField(fieldLabel = "Author Tags", name = "./cq:tags", ranking = 3, tab = 1)
    @TagInputField
    public List<String> getTags() {
        return getAsList("cq:tags", String.class);
    }

    @DialogField(fieldLabel = "Taggable", name = "./jcr:mixinTypes", defaultValue = "cq:Taggable")
    @Hidden(value = "cq:Taggable")
    public String getTaggable() {
        return "cq:Taggable";
    }

    @DialogField( fieldLabel = "Author Image", tab = 2)
    @Html5SmartImage(fileNameParameter = "image/fileName", fileReferenceParameter = "image/fileReference", allowUpload = false)
    public String getAuthorImage() {
        return getImageSource().or("notfound.png");
    }
}
