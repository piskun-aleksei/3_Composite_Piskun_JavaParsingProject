package by.bsuir.parsejavaproj.parsing;

import by.bsuir.parsejavaproj.composite.CompositeElement;
import by.bsuir.parsejavaproj.composite.CompositeType;

/**
 * Created by Алексей on 13.03.2016.
 */
public abstract class AbstractParseHandler {

    protected AbstractParseHandler successor;
    protected CompositeElement element;
    private CompositeType type;

    public AbstractParseHandler(CompositeType type) {
        this.type = type;
    }

    abstract public void parse(String text);

    public CompositeElement chain(String text) {
        element = new CompositeElement(type);
        parse(text);
        return element;
    }

    public void setSuccessor(AbstractParseHandler successor) {
        this.successor = successor;
    }
}
