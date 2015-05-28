package com.voghan.bookstore.servlets;

import com.citytechinc.aem.bedrock.core.servlets.AbstractJsonResponseServlet;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.voghan.bookstore.domain.Book;
import com.voghan.bookstore.services.BookService;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SlingServlet(paths = "/bin/books/search", methods = "GET")
public class BookSearchServlet extends AbstractJsonResponseServlet {
    static final Logger LOG = LoggerFactory.getLogger(BookSearchServlet.class);

    @Reference
    private BookService bookService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            String searchKeyword = "";
            if (request.getRequestParameter("searchKeyword") != null) {
                searchKeyword = request.getRequestParameter("searchKeyword").getString();
            }
            RequestParameter[] tags = request.getRequestParameters("tags[]");
            List<Tag> searchTags = new ArrayList<Tag>();

            TagManager tagManager = request.getResourceResolver().adaptTo(TagManager.class);
            if (searchKeyword != null) {
                if (tags != null) {
                    for (RequestParameter tag : tags) {
                        Tag resolvedTag = tagManager.resolve(tag.getString());
                        if (resolvedTag != null) {
                            searchTags.add(resolvedTag);
                        }
                    }
                }

            }

            List<Book> books = bookService.findBooks(searchTags, request.getResourceResolver());
            writeJsonResponse(response, books);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
