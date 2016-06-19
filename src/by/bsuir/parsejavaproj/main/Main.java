package by.bsuir.parsejavaproj.main;


import by.bsuir.parsejavaproj.actions.CompositeActions;
import by.bsuir.parsejavaproj.composite.*;
import by.bsuir.parsejavaproj.exception.JavaFileReadingException;
import by.bsuir.parsejavaproj.parsing.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ������� on 13.03.2016.
 */
public class Main {
    static Logger logger;
    static final String FILE_PATH = "source/file.java";
    static final String FILE_CHECK_PATH = "output/fileChecked.java";
    static final String OUT_FILE_PATH = "output/file.java";
    static final String OUT_FILE_PATH_SWAPPED = "output/fileSwapped.java";
    static final String OUT_FILE_PATH_SORTED = "output/fileSorted.java";
    static final String OUT_FILE_PATH_SORTED_LEX = "output/fileSortedLex.java";

    static {
        DOMConfigurator.configure("log4j.xml");
        logger = Logger.getLogger(Main.class);
    }

    public static void main(String[] args) {
        String file = null;
        try {
            file = TextParser.readFile(FILE_PATH);
            File fileCheck = new File(FILE_CHECK_PATH);
            if (!fileCheck.exists()) {
                fileCheck.createNewFile();
            }
            PrintWriter output = new PrintWriter(fileCheck.getAbsoluteFile());
            output.print(file);
            output.close();

            CompositeActions actions = CompositeActions.getInstance();
            ClassesFileParserHandler parser = new ClassesFileParserHandler(CompositeType.CLASSES_FILE);
            parser.chain(file);

            TextParser.saveFile(OUT_FILE_PATH, parser.getElement());

            actions.sortOperatorsByLexemes(parser.getElement(), OUT_FILE_PATH_SORTED);

            actions.sortLexemes(parser.getElement(), OUT_FILE_PATH_SORTED_LEX);

            actions.swapLexemes(parser.getElement());

            TextParser.saveFile(OUT_FILE_PATH_SWAPPED, parser.getElement());

        } catch (JavaFileReadingException e) {
            logger.fatal("File not found");
            throw new RuntimeException("File fatal error");
        } catch (FileNotFoundException e) {
            logger.error("File not found");
            throw new RuntimeException("File fatal error");
        } catch (IOException e) {
            logger.error("Error was occupied");
        }


    }
}
