package com.voghan.bookstore.servlets;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.AbstractOptionsProviderServlet;
import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.Option;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.google.common.base.Optional;
import org.apache.felix.scr.annotations.sling.SlingServlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SlingServlet(paths = "/bin/book/search_options", methods = "POST")
public class BookSearchOptionsServlet extends AbstractOptionsProviderServlet {
    @Override
    protected List<Option> getOptions(ComponentServletRequest csr) {
        List<Option> options = new ArrayList<Option>();
        TagManager tagManager = csr.getResourceResolver().adaptTo(TagManager.class);

        Tag mainTag = tagManager.resolve("/etc/tags/bookstore/genres");

        Iterator<Tag> it = mainTag.listChildren();

        while (it.hasNext()) {
            Tag tag = it.next();
            options.add(new Option(tag.getTagID(), tag.getTitle()));
        }

        return options;
    }

    @Override
    protected Optional<String> getOptionsRoot(ComponentServletRequest csr) {
        return Optional.absent();
    }
}
