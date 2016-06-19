package by.bsuir.parsejavaproj.composite;

import javax.lang.model.element.Element;
import java.util.ArrayList;

/**
 * Created by ������� on 13.03.2016.
 */
public class CompositeElement implements Entity {
    private CompositeType type;
    private ArrayList<Entity> content;

    public CompositeElement(CompositeType type) {
        content = new ArrayList<Entity>();
        this.type = type;
    }

    public void add(Entity component) {
        content.add(component);
    }

    @Override
    public ArrayList<Entity> getContent() {
        return content;
    }

    public CompositeType getType() {
        return type;
    }
}
