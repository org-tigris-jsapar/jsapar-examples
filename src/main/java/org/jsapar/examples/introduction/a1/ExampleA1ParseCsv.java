package org.jsapar.examples.introduction.a1;

import org.jsapar.TextParser;
import org.jsapar.error.JSaParException;
import org.jsapar.model.Cell;
import org.jsapar.model.Document;
import org.jsapar.model.Line;
import org.jsapar.model.LineUtils;
import org.jsapar.parse.DocumentBuilderLineEventListener;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ExampleA1ParseCsv {

    public void parseCsv() throws SchemaException, IOException, JSaParException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/introduction/a1/a1-csv-schema.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/introduction/a1/a1-csv-unquoted.csv")) {
            Schema schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineEventListener listener = new DocumentBuilderLineEventListener(document);
            parser.parse(fileReader, listener);

            document.stream().forEach(line->System.out.println(String.valueOf(line)));
            assert 10 == document.size();
            Line firstLine = document.iterator().next();
            assert "Erik".equals( LineUtils.getStringCellValue(firstLine, "First name")) ;
            assert "Erik".equals(firstLine.getNonEmptyCell("First name").map(Cell::getStringValue).orElse("fail"));
            assert "Svensson".equals( LineUtils.getStringCellValue(firstLine, "Last name"));
            assert "true".equals( LineUtils.getStringCellValue(firstLine, "Has dog"));
            assert "Fredrik".equals( LineUtils.getStringCellValue(document.getLine(1), "First name"));
            assert "Larsson".equals( LineUtils.getStringCellValue(document.getLine(1), "Last name"));
            assert "false".equals( LineUtils.getStringCellValue(document.getLine(1), "Has dog"));
            assert !LineUtils.getBooleanCellValue(document.getLine(1), "Has dog").orElseThrow(AssertionError::new);

            assert "Alfred".equals( LineUtils.getStringCellValue(document.getLine(2), "First name"));
            assert "Nilsson".equals( LineUtils.getStringCellValue(document.getLine(2), "Last name"));
            assert "true".equals( LineUtils.getStringCellValue(document.getLine(2), "Has dog"));

            assert "Person".equals( firstLine.getLineType());
            assert "Person".equals( document.getLine(1).getLineType());
        }
    }


    public static void main(String[] args) {
        ExampleA1ParseCsv exampleA1 = new ExampleA1ParseCsv();
        try {
            exampleA1.parseCsv();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
