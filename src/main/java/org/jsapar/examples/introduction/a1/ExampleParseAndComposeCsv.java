package org.jsapar.examples.introduction.a1;

import org.jsapar.TextComposer;
import org.jsapar.TextParser;
import org.jsapar.error.JSaParException;
import org.jsapar.model.*;
import org.jsapar.parse.DocumentBuilderLineConsumer;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

public class ExampleParseAndComposeCsv {

    public void parseCsv() throws IOException, JSaParException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/introduction/a1/csv-schema.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/introduction/a1/csv-unquoted.csv")) {
            Schema<?> schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineConsumer documentBuilder = new DocumentBuilderLineConsumer(document);
            parser.parseForEach(fileReader, documentBuilder);

            assert 10 == document.size();
            // Access lines with stream
            document.forEach(System.out::println);
            
            // Access lines with iterator
            Line firstLine = document.iterator().next();
            assert "Erik".equals( LineUtils.getStringCellValue(firstLine, "First name")) ;
            assert "Erik".equals(firstLine.getNonEmptyCell("First name").map(Cell::getStringValue).orElse("fail"));
            assert "Svensson".equals( LineUtils.getStringCellValue(firstLine, "Last name"));
            assert "true".equals( LineUtils.getStringCellValue(firstLine, "Has dog"));
            
            // Access lines by index
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

    public void composeCsv()
            throws SchemaException, IOException{
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/introduction/a1/csv-schema.xml");
             StringWriter writer = new StringWriter()) {
            Schema<?> schema = Schema.ofXml(schemaReader);
            TextComposer composer = new TextComposer(schema, writer);
            // You can add cells with specific cell constructor
            Line line1 = new Line("Person")
                    .addCell(new StringCell("First name", "Erik"))
                    .addCell(new StringCell("Middle name", "Vidfare"));

            // or you can add cells with the help of LineUtils class
            LineUtils.setStringCellValue(line1, "Last name", "Svensson");

            // For each line, call composeLine
            composer.composeLine(line1);

            composer.composeLine(new Line("Person")
                    .addCell(new StringCell("First name", "Fredrik"))
                    .addCell(new StringCell("Last name", "Larsson"))
                    .addCell(new BooleanCell("Has dog", false)));

            String output = writer.toString();
            System.out.println(output);
            String[] lines = output.split("\\n");
            assert 2 == lines.length;
            assert "Erik,Vidfare,Svensson,".equals( lines[0]);
            assert "Fredrik,,Larsson,no".equals( lines[1]);
        }
    }
    public static void main(String[] args) {
        ExampleParseAndComposeCsv exampleA1 = new ExampleParseAndComposeCsv();
        try {
            exampleA1.parseCsv();
            exampleA1.composeCsv();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
