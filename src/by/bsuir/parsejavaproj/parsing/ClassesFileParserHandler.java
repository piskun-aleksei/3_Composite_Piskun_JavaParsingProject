package by.bsuir.parsejavaproj.parsing;

import by.bsuir.parsejavaproj.composite.CompositeElement;
import by.bsuir.parsejavaproj.composite.CompositeType;
import by.bsuir.parsejavaproj.exception.ZeroClassesInFileException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ������� on 13.03.2016.
 */

public class ClassesFileParserHandler extends AbstractParseHandler {
    static Logger logger = Logger.getLogger(ClassesFileParserHandler.class);
    private static final String CLASS_REGEX = "(public|private|protected)([A-z ]+)(class)([A-z ]+)(\\{)";
    private static final String IMPORT_REGEX = "(import|package)([.[^\\{\\}\\(\\)\\;]]*)(\\;)";

    private ArrayList<Integer> clazzesEntryPoints;
    private ArrayList<String> clazzes;

    public ClassesFileParserHandler(CompositeType type) {
        super(type);
        clazzesEntryPoints = new ArrayList<Integer>();
        clazzes = new ArrayList<String>();
        this.setSuccessor(new ClassParserHandler(CompositeType.CLASS));
    }

    public CompositeElement getElement() {
        return element;
    }

    @Override
    public void parse(String text) {

        Pattern sentencePattern = Pattern.compile(IMPORT_REGEX);
        Matcher sentenceMatch = sentencePattern.matcher(text);
        while (sentenceMatch.find()) {
            element.add(new OperatorParserHandler(CompositeType.OPERATOR).chain(sentenceMatch.group()));
            logger.debug("Import found and added");
        }
        sentencePattern = Pattern.compile(CLASS_REGEX);
        sentenceMatch = sentencePattern.matcher(text);
        while (sentenceMatch.find()) {
            clazzes.add(sentenceMatch.group());
            clazzesEntryPoints.add(sentenceMatch.start());
        }
        if (clazzes.size() == 1) {
            element.add(successor.chain(text.substring(clazzesEntryPoints.get(0))));
            logger.debug("Class found and added");
        } else {
            if (clazzes.size() == 0) {
                logger.error("No classes in file");
                return;
            }
            for (int i = 0; i < clazzes.size(); i++) {
                if (i < clazzes.size() - 1) {
                    element.add(successor.chain(text.substring(clazzesEntryPoints.get(i), clazzesEntryPoints.get(i + 1))));
                    logger.debug("Class found and added");
                } else {
                    element.add(successor.chain(text.substring(clazzesEntryPoints.get(i))));
                    logger.debug("Class found and added");
                }
            }
        }

    }
}
