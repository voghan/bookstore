package com.voghan.bookstore.adapters;

import com.voghan.bookstore.domain.Author;
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
        @Property(name = SlingConstants.PROPERTY_ADAPTER_CLASSES, value = "com.voghan.bookstore.domain.Author")
})
public class AuthorAdapter implements AdapterFactory {
    public <AdapterType> AdapterType getAdapter(Object adaptable, Class<AdapterType> aClass) {

        Author author = new Author(adaptable);
        return (AdapterType)author;
    }
}
