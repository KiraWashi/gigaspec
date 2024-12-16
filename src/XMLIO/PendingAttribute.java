package XMLIO;

import metaModel.Entity;
import org.w3c.dom.Element;

public class PendingAttribute {
    private Entity entity;
    private Element attributeElement;

    public PendingAttribute(Entity entity, Element attributeElement) {
        this.entity = entity;
        this.attributeElement = attributeElement;
    }

    public Entity getEntity() {
        return entity;
    }

    public Element getAttributeElement() {
        return attributeElement;
    }
}