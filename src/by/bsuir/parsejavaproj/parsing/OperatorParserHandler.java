package by.bsuir.parsejavaproj.parsing;

import by.bsuir.parsejavaproj.composite.CompositeType;
import by.bsuir.parsejavaproj.composite.LexemeLeaf;
import by.bsuir.parsejavaproj.composite.LexemeType;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ������� on 21.03.2016.
 */
public class OperatorParserHandler extends AbstractParseHandler {
    static Logger logger = Logger.getLogger(OperatorParserHandler.class);
    private static final String ACCESS_MOD_REGEX = "(public|private|protected)";
    private static final String FIELD_MOD_REGEX = "(static|final|abstract)(\\s*)(\\{)?";

    public OperatorParserHandler(CompositeType type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        String[] lexemes = text.trim().split("\\s+");
        Pattern sentencePattern;
        Matcher sentenceMatch;
        boolean elementAdded = false;
        for (int i = 0; i < lexemes.length; i++) {
            sentencePattern = Pattern.compile(ACCESS_MOD_REGEX);
            sentenceMatch = sentencePattern.matcher(lexemes[i]);
            if (sentenceMatch.find()) {
                element.add(new LexemeLeaf(sentenceMatch.group(), LexemeType.ACCESS_MOD));
                logger.debug("Access modificator found and added");
                elementAdded = true;
            }
            sentencePattern = Pattern.compile(FIELD_MOD_REGEX);
            sentenceMatch = sentencePattern.matcher(lexemes[i]);
            if (sentenceMatch.find()) {
                element.add(new LexemeLeaf(sentenceMatch.group(), LexemeType.FIELD_MOD));
                logger.debug("Field modificator found and added");
                elementAdded = true;
            }
            if (!elementAdded) {
                element.add(new LexemeLeaf(lexemes[i], LexemeType.LEXEME));
                logger.debug("Lexeme found and added");
            }
            elementAdded = false;
        }

    }
}
