package by.bsuir.parsejavaproj.parsing;

import by.bsuir.parsejavaproj.composite.CompositeType;
import by.bsuir.parsejavaproj.composite.LexemeLeaf;
import by.bsuir.parsejavaproj.composite.LexemeType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ������� on 16.03.2016.
 */
public class ClassElementParserHandler extends AbstractParseHandler {
    static Logger logger = Logger.getLogger(ClassElementParserHandler.class);
    private static final String OPERATOR_REGEX = "(\\s*)([.[^\\{};]]*)(;)";
    private static final String SIGNATURE_REGEX = "(\\s*)([.[^\\{};]]*)(\\{)";

    public ClassElementParserHandler(CompositeType type) {
        super(type);

    }

    @Override
    public void parse(String text) {
        Pattern sentencePattern = Pattern.compile(SIGNATURE_REGEX);
        Matcher sentenceMatch = sentencePattern.matcher(text);
        this.setSuccessor(new OperatorParserHandler(CompositeType.SIGNATURE));
        while (sentenceMatch.find()) {
            element.add(successor.chain(sentenceMatch.group()));
            logger.debug("Signature found and added");
        }
        sentencePattern = Pattern.compile(OPERATOR_REGEX);
        sentenceMatch = sentencePattern.matcher(text);
        this.setSuccessor(new OperatorParserHandler(CompositeType.OPERATOR));
        while (sentenceMatch.find()) {
            element.add(successor.chain(sentenceMatch.group()));
            logger.debug("Operator found and added");
        }
    }

}
