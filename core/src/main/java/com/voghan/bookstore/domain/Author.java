package com.voghan.bookstore.domain;

import com.citytechinc.aem.bedrock.api.node.ComponentNode;
import org.apache.sling.api.resource.Resource;

public class Author extends Base {

    private ComponentNode componentNode;

    public Author(Object o) {
        if (o instanceof Resource) {
            Resource resource = (Resource) o;
            componentNode = resource.adaptTo(ComponentNode.class);
        }
    }

    public String getAuthorName() {
        return componentNode.get("authorName", "");
    }

    public String getAuthorImage() {
        return componentNode.getImageSource().or("#");
    }

    public String getHref() {
        return componentNode.getHref();
    }

}
