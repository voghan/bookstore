package com.voghan.bookstore.components.content;


import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.voghan.bookstore.domain.Author;
import com.voghan.bookstore.services.AuthorService;
import com.voghan.bookstore.utilities.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

@Component(value = "Book Detail", group = "bookstore", tabs = {
        @Tab(title = "Book"),
        @Tab(title = "Image")
})
public class BookDetail extends AbstractComponent {

    public static final String RESOURCE_TYPE = "bookstore/components/content/bookdetail";

    private TagManager tagManager;

    private AuthorService authorService;

    @Override
    public void init(ComponentRequest request) {
        tagManager = request.getResourceResolver().adaptTo(TagManager.class);
        authorService = (AuthorService)ServiceFactory.getService(AuthorService.class.getName());
    }

    @DialogField(fieldLabel = "Title", ranking = 1)
    public String getBookTitle() {
        return get("bookTitle", "");
    }

    @DialogField(fieldLabel = "Description", ranking = 3)
    @RichTextEditor
    public String getBookDescription() {
        return get("bookDescription", "");
    }

    public List<Author> getBookAuthors() {
        List<Author> authors = new ArrayList<Author>();

        List<String> authorTags = getAuthorTags();

        for(String tagId : authorTags) {
            Tag tag = tagManager.resolve(tagId);
            if (tag != null) {
                Author author = authorService.findByTag(tag, getResource().getResourceResolver());

                if (author != null) {
                    authors.add(author);
                }
            }
        }

        return authors;
    }

    @DialogField(fieldLabel = "Author Tags", ranking = 2)
    @TagInputField
    public List<String> getAuthorTags() {
        return getAsList("authorTags", String.class);
    }

    @DialogField(fieldLabel = "Genre Tags", ranking = 4, name = "./cq:tags")
    @TagInputField
    public List<String> getGenreTags() {
        return getAsList("cq:tags", String.class);
    }

    @DialogField(fieldLabel = "Taggable", name = "./jcr:mixinTypes", defaultValue = "cq:Taggable")
    @Hidden(value = "cq:Taggable")
    public String getTaggable() {
        return "cq:Taggable";
    }

    @DialogField(fieldLabel = "Cover Image", tab = 2)
    @Html5SmartImage(fileReferenceParameter = "image/fileReference", fileNameParameter = "image/fileName", allowUpload = false)
    public String getBookCover() {
        return getImageSource().or("#");
    }
}
