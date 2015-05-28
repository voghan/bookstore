package com.voghan.bookstore.domain;


import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.voghan.bookstore.services.AuthorService;
import com.voghan.bookstore.utilities.ServiceFactory;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class Book extends Base {
    private ComponentNode componentNode;
    private TagManager tagManager;
    private AuthorService authorService;

    public Book(Object o) {
        if (o instanceof Resource) {
            Resource resource = (Resource) o;
            componentNode = resource.adaptTo(ComponentNode.class);
            tagManager = resource.getResourceResolver().adaptTo(TagManager.class);
            authorService = (AuthorService) ServiceFactory.getService(AuthorService.class.getName());
        }
    }

    public String getBookTitle() {
        return componentNode.get("bookTitle", "");
    }

    public String getBookCover() {
        return componentNode.getImageSource().or("#");
    }

    public String getHref() {
        return componentNode.getHref();
    }

    public List<Author> getBookAuthors() {
        List<Author> authors = new ArrayList<Author>();

        List<String> authorTags = getAuthorTags();

        for (String tagId : authorTags) {
            Tag tag = tagManager.resolve(tagId);
            if (tag != null) {
                Author author = authorService.findByTag(tag, componentNode.getResource().getResourceResolver());

                if (author != null) {
                    authors.add(author);
                }
            }
        }

        return authors;
    }

    public List<String> getAuthorTags() {
        return componentNode.getAsList("authorTags", String.class);
    }
}
