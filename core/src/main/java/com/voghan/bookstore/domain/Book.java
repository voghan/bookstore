package com.voghan.bookstore.domain;


import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;

public class Book extends Base {
    private ComponentNode componentNode;

    public Book(Object o) {
        if (o instanceof Resource) {
            Resource resource = (Resource) o;
            componentNode = resource.adaptTo(ComponentNode.class);
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

    public String getAuthorName() {
        return "Unknown";
    }
}
