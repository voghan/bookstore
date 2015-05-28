package com.voghan.bookstore.components.content;

import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.voghan.bookstore.domain.Book;
import com.voghan.bookstore.services.BookService;
import com.voghan.bookstore.utilities.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Component(value = "Book List", group = "bookstore")
public class BookList extends AbstractComponent {
    static final Logger LOG = LoggerFactory.getLogger(AuthorList.class);

    private BookService bookService;
    private TagManager tagManager;

    @Override
    public void init(ComponentRequest request) {
        tagManager = request.getResourceResolver().adaptTo(TagManager.class);
        bookService = (BookService) ServiceFactory.getService(BookService.class.getName());
    }

    @DialogField(fieldLabel = "Title", ranking = 1)
    public String getContentTitle() {
        return get("contentTitle", "");
    }

    @DialogField(fieldLabel = "Description", ranking = 2)
    @RichTextEditor
    public String getContentDescription() {
        return get("contentDescription", "");
    }

    @DialogField(fieldLabel = "Filter Tags", ranking = 3, name = "./cq:tags")
    @TagInputField
    public List<String> getTags() {
        return getAsList("cq:tags", String.class);
    }

    @DialogField(fieldLabel = "Taggable", name = "./jcr:mixinTypes", defaultValue = "cq:Taggable")
    @Hidden(value = "cq:Taggable")
    public String getTaggable() {
        return "cq:Taggable";
    }

    public List<Book> getBooks() {

        List<Tag> tags = new ArrayList<Tag>();


        for (String tagId : getTags()) {
            LOG.info("... found tag id: " + tagId);
            Tag tag = tagManager.resolve(tagId);

            if (tag != null) {
                tags.add(tag);
            }
        }

        return bookService.findBooks(tags, getResource().getResourceResolver());
    }
}
