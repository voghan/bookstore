package com.voghan.bookstore.services.impl;

import com.day.cq.tagging.Tag;
import com.voghan.bookstore.components.content.BookDetail;
import com.voghan.bookstore.domain.Book;
import com.voghan.bookstore.services.BookService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.query.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Component(metatype = true, label = "Bookstore Book Service")
public class BookServiceImpl implements BookService {

    static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);


    public List<Book> findBooks(List<Tag> tags, ResourceResolver resourceResolver) {

        List<Book> books = new ArrayList<Book>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM [nt:unstructured] AS s")
                .append(" WHERE ISDESCENDANTNODE([/content/bookstore/en/books])")
                .append(" AND [sling:resourceType] = '" + BookDetail.RESOURCE_TYPE + "'");

        if (!tags.isEmpty()) {

            query.append(" AND (");
            String condition = "";
            for (Tag tag : tags) {
                query.append(" " + condition + "[cq:tags] = '" + tag.getTagID() + "'");
                condition = " OR ";
            }
            query.append(")");

        }


        LOG.info(query.toString());

        Iterator<Resource> it = resourceResolver.findResources(query.toString(), Query.JCR_SQL2);

        while (it.hasNext()) {
            Resource resource = it.next();
            Book book = resource.adaptTo(Book.class);
            if (book != null) {
                books.add(book);
            }
        }

        return books;
    }

    public List<Book> search(String keyword, List<Tag> tags, ResourceResolver resourceResolver) {
        List<Book> books = new ArrayList<Book>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM [nt:unstructured] AS s")
                .append(" WHERE ISDESCENDANTNODE([/content/bookstore/en/books])")
                .append(" AND [sling:resourceType] = '" + BookDetail.RESOURCE_TYPE + "'");

        if (keyword != null && !keyword.isEmpty()) {
            query.append("and contains(s.*, '")
                    .append(keyword)
                    .append("')");
        }

        LOG.info("... tags:" + tags.size());
        if (!tags.isEmpty()) {

            query.append(" AND (");
            String condition = "";
            for (Tag tag : tags) {
                LOG.info("... append tag " + tag.getTagID());
                query.append(" " + condition + "[cq:tags] = '" + tag.getTagID() + "'");
                condition = " OR ";
            }
            query.append(")");

        }


        LOG.info(query.toString());

        Iterator<Resource> it = resourceResolver.findResources(query.toString(), Query.JCR_SQL2);

        while (it.hasNext()) {
            Resource resource = it.next();
            Book book = resource.adaptTo(Book.class);
            if (book != null) {
                books.add(book);
            }
        }

        return books;
    }
}
