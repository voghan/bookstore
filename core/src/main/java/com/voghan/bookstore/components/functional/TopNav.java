package com.voghan.bookstore.components.functional;


import com.citytechinc.aem.bedrock.api.link.NavigationLink;
import com.citytechinc.aem.bedrock.api.page.PageDecorator;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.bedrock.core.constants.ComponentConstants;
import com.citytechinc.cq.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value = "Breadcrumb", group = ComponentConstants.GROUP_HIDDEN)
public class TopNav extends AbstractComponent {

    public String getBrandHref() {
        return getAsHref("brandPath").or("#");
    }

    public String getBrandTitle() {
        return get("brandTitle", "Bookstore");
    }

    public List<NavigationLink> getTopNavLinks() {
        List<NavigationLink> links = new ArrayList<NavigationLink>();

        PageDecorator navRootPage =  this.getCurrentPage().getAbsoluteParent(2);

        if (navRootPage != null) {
            for (PageDecorator child : navRootPage.getChildren()) {
                if (!child.isHideInNav()) {

                    boolean activePage = getCurrentPage().getPath().equals(child.getPath());
                    links.add(child.getLinkBuilder().setActive(activePage).buildNavigationLink());
                }
            }
        }

        return links;
    }
}
