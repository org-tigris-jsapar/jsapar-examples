package org.jsapar.examples.schemabasics.c2;

import org.jsapar.TextComposer;
import org.jsapar.TextParser;
import org.jsapar.error.JSaParException;
import org.jsapar.model.*;
import org.jsapar.parse.DocumentBuilderLineEventListener;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

/**
 * This example demonstrates the usage of emptycondition in a schema while parsing a source.
 */
public class ExampleEmptyPattern {

    public void parseCsv() throws SchemaException, IOException, JSaParException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c2/csv-schema.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c2/csv-unquoted.csv")) {
            Schema schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineEventListener listener = new DocumentBuilderLineEventListener(document);
            parser.parse(fileReader, listener);

            assert 10 == document.size();
            // Access lines with stream
            document.forEach(line->System.out.println(String.valueOf(line)));
            
            final Line firstLine = document.iterator().next();
            assert "Erik".equals( LineUtils.getStringCellValue(firstLine, "First name")) ;
            // Not empty
            assert "Vidfare".equals( LineUtils.getStringCellValue(firstLine, "Middle name"));
            assert "Person".equals( firstLine.getLineType());

            final Line secondLine = document.getLine(1);
            assert "Fredrik".equals( LineUtils.getStringCellValue(secondLine, "First name"));
            // Contains NULL but is regarded as emtpy
            assert "".equals( LineUtils.getStringCellValue(secondLine, "Middle name"));
            assert "Person".equals( secondLine.getLineType());

            final Line thirdLine = document.getLine(2);
            assert "Alfred".equals( LineUtils.getStringCellValue(thirdLine, "First name"));
            // Contains spaces but is regarded as emtpy
            assert "".equals( LineUtils.getStringCellValue(thirdLine, "Middle name"));

        }
    }

    public static void main(String[] args) {
        ExampleEmptyPattern example = new ExampleEmptyPattern();
        try {
            example.parseCsv();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
