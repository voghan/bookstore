package com.voghan.bookstore.services.impl;

import com.day.cq.tagging.Tag;
import com.voghan.bookstore.components.content.AuthorDetail;
import com.voghan.bookstore.domain.Author;
import com.voghan.bookstore.services.AuthorService;
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
@Component(metatype = true, label = "Bookstore Author Service")
public class AuthorServiceImpl implements AuthorService {

    static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);


    public Author findByTag(Tag authorTag, ResourceResolver resourceResolver) {
        Author author = null;

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM [nt:unstructured] AS s");
        query.append(" WHERE ISDESCENDANTNODE([/content/bookstore/en/authors])");
        query.append(" AND [sling:resourceType] = '" + AuthorDetail.RESOURCE_TYPE + "'");
        query.append(" AND [cq:tags] = '" + authorTag.getTagID() + "'");

        LOG.info(query.toString());

        Iterator<Resource> it = resourceResolver.findResources(query.toString(), Query.JCR_SQL2);

        while(it.hasNext()) {
            Resource resource = it.next();
            LOG.info("...found resource:" + resource.getPath());
            author = resource.adaptTo(Author.class);
        }

        return author;
    }

    public List<Author> findAuthors(List<Tag> tags, ResourceResolver resourceResolver) {
        List<Author> authors = new ArrayList<Author>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM [nt:unstructured] AS s");
        query.append(" WHERE ISDESCENDANTNODE([/content/bookstore/en/authors])");
        query.append(" AND [sling:resourceType] = '" + AuthorDetail.RESOURCE_TYPE + "'");

        if (!tags.isEmpty()) {

            query.append(" AND (");
            String condition = "";
            for(Tag tag : tags) {
                query.append(" " + condition + "[cq:tags] = '" + tag.getTagID() + "'");
                condition = "OR";
            }
            query.append(")");

        }


        LOG.info(query.toString());

        Iterator<Resource> it = resourceResolver.findResources(query.toString(), Query.JCR_SQL2);

        while(it.hasNext()) {
            Resource resource = it.next();
            LOG.info("...found resource:" + resource.getPath());
            Author author = resource.adaptTo(Author.class);
            if ( author != null) {
                authors.add(author);
            }
        }


        return authors;
    }
}
