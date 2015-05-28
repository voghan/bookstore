package com.voghan.bookstore.adapters;

import com.voghan.bookstore.domain.Book;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.adapter.AdapterFactory;

@Component
@Service(AdapterFactory.class)
@Properties({
        @Property(name = "BookRecipeManagerAdapter", value = "adapter/factory"),
        @Property(name = SlingConstants.PROPERTY_ADAPTABLE_CLASSES, value = "org.apache.sling.api.resource.Resource"),
        @Property(name = SlingConstants.PROPERTY_ADAPTER_CLASSES, value = "com.voghan.bookstore.domain.Book")
})
public class BookAdapter implements AdapterFactory {

    public <AdapterType> AdapterType getAdapter(Object adaptable, Class<AdapterType> aClass) {

        Book book = new Book(adaptable);
        return (AdapterType) book;
    }
}
