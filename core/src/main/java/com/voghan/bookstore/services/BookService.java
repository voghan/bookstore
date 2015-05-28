package com.voghan.bookstore.services;

import com.day.cq.tagging.Tag;
import com.voghan.bookstore.domain.Book;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

public interface BookService {

    List<Book> findBooks(List<Tag> tags, ResourceResolver resourceResolver);

    List<Book> search(String keyword, List<Tag> tags, ResourceResolver resourceResolver);
}
