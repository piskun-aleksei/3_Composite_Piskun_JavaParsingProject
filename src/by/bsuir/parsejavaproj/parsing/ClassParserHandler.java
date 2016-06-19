package by.bsuir.parsejavaproj.parsing;

import by.bsuir.parsejavaproj.composite.CompositeType;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ������� on 15.03.2016.
 */
public class ClassParserHandler extends AbstractParseHandler {
    static Logger logger = Logger.getLogger(ClassParserHandler.class);
    private static final String CLASS_REGEX = "(public|private|protected)([A-z ]+)(class)([A-z ]+)(\\{)";
    private static final String METHOD_REGEX =
            "(\\p{Blank}*)(public|private)(\\p{Blank}*)([A-z]+)(\\s+)([A-z ]+)(\\(([A-z ]*)\\))(\\p{Blank}*)(\\{)([.[^\\}]]*)(\\})";
    private static final String FIELD_REGEX = "(public|private|protected)([.[^;\\{\\(\\}\\)]]*)(;)";
    private static final String STATIC_FIELD_REGEX = "(static)(\\p{Blank}*)(\\{)([.[^\\}]]*)(\\})";
    private static final String CONSTRUCTOR_REGEX = "(\\p{Blank}*)(public|private)(\\p{Blank}*)([A-z]*)(\\p{Blank}*)(\\(([A-z ]*)\\))(\\p{Blank}*)(\\{)([.[^\\}]]*)(\\})";

    public ClassParserHandler(CompositeType type) {
        super(type);
    }


    @Override
    public void parse(String text) {
        Pattern sentencePattern = Pattern.compile(CLASS_REGEX);
        Matcher sentenceMatch = sentencePattern.matcher(text);
        this.setSuccessor(new OperatorParserHandler(CompositeType.OPERATOR));
        while (sentenceMatch.find()) {
            element.add(successor.chain(sentenceMatch.group()));
            logger.debug("Class string (operator) found and added");
        }
        sentencePattern = Pattern.compile(FIELD_REGEX);
        sentenceMatch = sentencePattern.matcher(text);
        this.setSuccessor(new OperatorParserHandler(CompositeType.OPERATOR));
        while (sentenceMatch.find()) {
            element.add(successor.chain(sentenceMatch.group()));
            logger.debug("Field (operator) found and added");
        }
        sentencePattern = Pattern.compile(STATIC_FIELD_REGEX);
        sentenceMatch = sentencePattern.matcher(text);
        this.setSuccessor(new ClassElementParserHandler(CompositeType.STATIC_FIELD));
        while (sentenceMatch.find()) {
            element.add(successor.chain(sentenceMatch.group()));
            logger.debug("Static field found and added");
        }
        sentencePattern = Pattern.compile(CONSTRUCTOR_REGEX);
        sentenceMatch = sentencePattern.matcher(text);
        this.setSuccessor(new ClassElementParserHandler(CompositeType.CONSTRUCTOR));
        while (sentenceMatch.find()) {
            element.add(successor.chain(sentenceMatch.group()));
            logger.debug("Constructor found and added");
        }

        sentencePattern = Pattern.compile(METHOD_REGEX);
        sentenceMatch = sentencePattern.matcher(text);
        this.setSuccessor(new ClassElementParserHandler(CompositeType.METHOD));
        while (sentenceMatch.find()) {
            element.add(successor.chain(sentenceMatch.group()));
            logger.debug("Method found and added");
        }
    }
}
