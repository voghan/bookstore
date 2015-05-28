package com.voghan.bookstore.components.content;

import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

import java.util.ArrayList;
import java.util.List;

@Component(value = "Search Books")
public class BookSearch extends AbstractComponent {
    @DialogField(fieldLabel = "Tags")
    @Selection(type = Selection.CHECKBOX, optionsUrl = "/bin/book/search_options")
    public List<String> getOptions() {
        return getAsList("options", String.class);
    }

    public List<Tag> getFormTags() {
        List<Tag> tags = new ArrayList<Tag>();
        List<String> options = getOptions();
        TagManager tagManager = getResource().getResourceResolver().adaptTo(TagManager.class);

        for (String option : options) {
            tags.add(tagManager.resolve(option));
        }

        return tags;
    }
}
