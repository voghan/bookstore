package com.voghan.bookstore.services;

import com.day.cq.tagging.Tag;
import com.voghan.bookstore.domain.Author;
import org.apache.sling.api.resource.ResourceResolver;

public interface AuthorService {

    Author findByTag(Tag authorTag, ResourceResolver resourceResolver);
}
