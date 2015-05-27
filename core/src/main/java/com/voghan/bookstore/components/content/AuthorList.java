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
import com.voghan.bookstore.domain.Author;
import com.voghan.bookstore.services.AuthorService;
import com.voghan.bookstore.utilities.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Component(value = "Author List", group = "bookstore")
public class AuthorList extends AbstractComponent {

    static final Logger LOG = LoggerFactory.getLogger(AuthorList.class);

    private AuthorService authorService;
    private TagManager tagManager;

    @Override
    public void init(ComponentRequest request) {
        tagManager = request.getResourceResolver().adaptTo(TagManager.class);
        authorService = (AuthorService) ServiceFactory.getService(AuthorService.class.getName());
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

    public List<Author> getAuthors() {

        List<Tag> tags = new ArrayList<Tag>();


        for (String tagId : getTags()) {
            LOG.info("... found tag id: " + tagId);
            Tag tag = tagManager.resolve(tagId);

            if (tag != null) {
                tags.add(tag);
            }
        }

        return authorService.findAuthors(tags, getResource().getResourceResolver());
    }
}
