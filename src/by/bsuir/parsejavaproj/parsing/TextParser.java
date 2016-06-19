package by.bsuir.parsejavaproj.parsing;

import by.bsuir.parsejavaproj.composite.*;
import by.bsuir.parsejavaproj.exception.JavaFileReadingException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.JarException;

/**
 * Created by ������� on 13.03.2016.
 */
public class TextParser {
    static Logger logger = Logger.getLogger(TextParser.class);
    private static PrintWriter output;

    public static String readFile(String fileName) throws JavaFileReadingException {
        StringBuilder builder = new StringBuilder();
        try (Scanner fileInput = new Scanner(new File(fileName))) {
            while (fileInput.hasNextLine()) {
                builder.append(fileInput.nextLine());
            }
        } catch (IOException e) {
            logger.fatal("Read file error", e);
            throw new JavaFileReadingException("Read File Error");
        }
        return builder.toString();
    }

    public static void saveFile(String fileName, CompositeElement element) throws JavaFileReadingException {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            output = new PrintWriter(file.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            logger.fatal("File not found");
            throw new JavaFileReadingException("Write file error");
        } catch (IOException e) {
            logger.error("Error was occupied");
        }
        for (int i = 0; i < element.getContent().size(); i++) {
            saveElement(element.getContent().get(i));
        }
        output.close();
    }

    private static void saveElement(Entity element) {
        if (!element.getClass().toString().equals(LexemeLeaf.class.toString())) {
            CompositeElement compositeElement = (CompositeElement) element;
            for (int i = 0; i < compositeElement.getContent().size(); i++) {
                saveElement(compositeElement.getContent().get(i));
            }
            if(compositeElement.getType() == CompositeType.OPERATOR || compositeElement.getType() == CompositeType.SIGNATURE){
                output.println();
            }
            if(compositeElement.getType() == CompositeType.METHOD || compositeElement.getType() == CompositeType.CONSTRUCTOR
                    || compositeElement.getType() == CompositeType.STATIC_FIELD){
                output.println('}');
            }
            if(compositeElement.getType() == CompositeType.CLASS){
                output.println('}');
            }
        } else {
            output.print(element.getContent() + " ");
        }
    }


}
