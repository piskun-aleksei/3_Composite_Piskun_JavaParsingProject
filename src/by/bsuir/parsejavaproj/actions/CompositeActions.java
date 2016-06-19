package by.bsuir.parsejavaproj.actions;

import by.bsuir.parsejavaproj.composite.CompositeElement;
import by.bsuir.parsejavaproj.composite.CompositeType;
import by.bsuir.parsejavaproj.composite.Entity;
import by.bsuir.parsejavaproj.composite.LexemeLeaf;
import by.bsuir.parsejavaproj.exception.JavaFileReadingException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Алексей on 27.03.2016.
 */
public class CompositeActions {
    static Logger logger = Logger.getLogger(CompositeActions.class);
    private static CompositeActions instance;
    private static ArrayList<CompositeElement> elements;
    private static ArrayList<String> lexemes;

    private CompositeActions() {
        elements = new ArrayList<CompositeElement>();
        lexemes = new ArrayList<String>();
    }

    public static CompositeActions getInstance() {
        if (instance == null) {
            instance = new CompositeActions();
        }
        return instance;
    }

    public static void swapLexemes(CompositeElement element) {
        for (int i = 0; i < element.getContent().size(); i++) {
            swapLexemesInOperator(element.getContent().get(i));
        }
    }

    private static void swapLexemesInOperator(Entity element) {
        CompositeElement tempElement = (CompositeElement) element;
        if (tempElement.getType() == CompositeType.OPERATOR || tempElement.getType() == CompositeType.SIGNATURE) {
            if (tempElement.getType() != CompositeType.SIGNATURE) {
                Collections.swap(tempElement.getContent(), 0, tempElement.getContent().size() - 1);
                logger.debug("Lexemes swapped");
            }
        } else {
            for (int i = 0; i < tempElement.getContent().size(); i++) {
                swapLexemesInOperator(tempElement.getContent().get(i));
            }
        }

    }

    public static void sortOperatorsByLexemes(CompositeElement element, String fileName) throws JavaFileReadingException {
        for (int i = 0; i < element.getContent().size(); i++) {
            findOperator(element.getContent().get(i));
        }
        for (int i = 0; i < elements.size() - 1; i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(j).getContent().size() < elements.get(i).getContent().size()) {
                    Collections.swap(elements, i, j);
                }
            }
        }
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter output = new PrintWriter(file.getAbsoluteFile());
            for (int i = 0; i < elements.size(); i++) {
                for (int j = 0; j < elements.get(i).getContent().size(); j++) {
                    output.print(elements.get(i).getContent().get(j).getContent() + " ");
                }
                output.println();
            }
            output.close();
        } catch (FileNotFoundException e) {
            logger.fatal("File not found");
            throw new JavaFileReadingException("Write file error");
        } catch (IOException e) {
            logger.error("Error was occupied");
        }
    }

    private static void findOperator(Entity element) {
        CompositeElement tempElement = (CompositeElement) element;
        if (tempElement.getType() == CompositeType.OPERATOR || tempElement.getType() == CompositeType.SIGNATURE) {
            if (tempElement.getType() != CompositeType.SIGNATURE) {
                elements.add(tempElement);
            }
        } else {
            for (int i = 0; i < tempElement.getContent().size(); i++) {
                findOperator(tempElement.getContent().get(i));
            }
        }
    }

    public static void sortLexemes(CompositeElement element, String fileName) throws JavaFileReadingException {
        for (int i = 0; i < element.getContent().size(); i++) {
            findLexeme(element.getContent().get(i));
        }
        for (int i = 0; i < lexemes.size() - 1; i++) {
            for (int j = i + 1; j < lexemes.size(); j++) {
                if (lexemes.get(j).charAt(0) < lexemes.get(i).charAt(0)) {
                    Collections.swap(lexemes, i, j);
                }
            }
        }
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter output = new PrintWriter(file.getAbsoluteFile());
            char symbol = lexemes.get(0).charAt(0);
            for (int i = 0; i < lexemes.size(); i++) {
                if (lexemes.get(i).charAt(0) != symbol) {
                    symbol = lexemes.get(i).charAt(0);
                    output.println();
                }
                output.print(lexemes.get(i) + " ");
            }
            output.close();
        } catch (FileNotFoundException e) {
            logger.fatal("File not found");
            throw new JavaFileReadingException("Write file error");
        } catch (IOException e) {
            logger.error("Error was occupied");
        }
    }

    private static void findLexeme(Entity element) {
        if (!element.getClass().toString().equals(LexemeLeaf.class.toString())) {
            CompositeElement tempElement = (CompositeElement) element;
            for (int i = 0; i < tempElement.getContent().size(); i++) {
                findLexeme(tempElement.getContent().get(i));
            }
        } else {
            LexemeLeaf leaf = (LexemeLeaf) element;
            lexemes.add(leaf.getContent());
        }
    }
}
