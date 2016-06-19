package by.bsuir.parsejavaproj.composite;

import java.util.ArrayList;

/**
 * Created by Алексей on 13.03.2016.
 */
public class LexemeLeaf implements Entity {
    private String lexeme;
    private LexemeType type;

    public LexemeLeaf() {
    }

    public LexemeLeaf(String lexeme, LexemeType type) {
        this.type = type;
        this.lexeme = lexeme;
    }

    @Override
    public String getContent() {
        return lexeme;
    }

    @Override
    public LexemeType getType() {
        return type;
    }
}
