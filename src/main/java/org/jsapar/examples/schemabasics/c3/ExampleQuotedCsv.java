package org.jsapar.examples.schemabasics.c3;

import org.jsapar.TextComposer;
import org.jsapar.TextParser;
import org.jsapar.error.JSaParException;
import org.jsapar.model.*;
import org.jsapar.parse.DocumentBuilderLineConsumer;
import org.jsapar.parse.DocumentBuilderLineEventListener;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

/**
 * This example demonstrate parsing and composing of files with quoted cells.
 */
public class ExampleQuotedCsv {

    /**
     * Demonstrates parsing of quoted csv where quotes are just added first and last of cell
     */
    public Document parseCsv() throws SchemaException, IOException, JSaParException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c3/csv-schema.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c3/csv-quoted.csv")) {
            Schema schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineConsumer documentBuilder = new DocumentBuilderLineConsumer(document);
            parser.parseForEach(fileReader, documentBuilder);

            assert 3 == document.size();
            // Access lines with stream
            document.forEach(line -> System.out.println(String.valueOf(line)));

            // Access lines with iterator
            Line firstLine = document.iterator().next();
            assert "Erik".equals(LineUtils.getStringCellValue(firstLine, "First name"));
            assert "Svensson".equals(LineUtils.getStringCellValue(firstLine, "Last name"));
            assert "Backstreet 43, 123 45  Thecity".equals(LineUtils.getStringCellValue(firstLine, "Address"));

            // Access lines by index
            Line secondLine = document.getLine(1);
            assert "Fredrik".equals(LineUtils.getStringCellValue(secondLine, "First name"));
            assert "Larsson".equals(LineUtils.getStringCellValue(secondLine, "Last name"));
            assert "Goaway 23; 444 44  Downtown".equals(LineUtils.getStringCellValue(secondLine, "Address"));

            Line thirdLine = document.getLine(2);
            assert "Alfred".equals(LineUtils.getStringCellValue(thirdLine, "First name"));
            assert "Nilsson".equals(LineUtils.getStringCellValue(thirdLine, "Last name"));
            assert "Homeway 17\n45678 Willage".equals(LineUtils.getStringCellValue(thirdLine, "Address"));
            return document;
        }
    }

    /**
     * Demonstrates composing of quoted CSV where quotes are just added first and last of cell
     */
    public void composeCsv()
            throws SchemaException, IOException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c3/csv-schema.xml");
             StringWriter writer = new StringWriter()) {
            Schema schema = Schema.ofXml(schemaReader);
            TextComposer composer = new TextComposer(schema, writer);
            // You can add cells with specific cell constructor
            Line line1 = new Line("Person")
                    .addCell(new StringCell("First name", "Erik"))
                    .addCell(new StringCell("Last name", "Eriksson"))
                    .addCell(new StringCell("Address", "Somewhere 23, 12345  City"));

            // For each line, call composeLine
            composer.composeLine(line1);

            composer.composeLine(new Line("Person")
                    .addCell(new StringCell("First name", "Fredrik"))
                    .addCell(new StringCell("Last name", "Larsson"))
                    .addCell(new StringCell("Address", "Stariway 11; 65487  Town")));

            String output = writer.toString();
            System.out.println(output);
            String[] lines = output.split("\\n");
            assert 2 == lines.length;
            // Always quote last name
            // Detect when quoting is needed for address. The default quote behavior for string cells is AUTOMATIC.
            assert "Erik,\"Eriksson\",\"Somewhere 23, 12345  City\"".equals(lines[0]);
            assert "Fredrik,\"Larsson\",Stariway 11; 65487  Town".equals(lines[1]);
        }
    }

    /**
     * This example demonstrates parsing of CSV that conforms to RFC4180
     */
    public Document parseCsvRfc4180() throws SchemaException, IOException, JSaParException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c3/csv-schema-rfc4180.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c3/csv-quoted-rfc4180.csv")) {
            Schema schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineConsumer documentBuilder = new DocumentBuilderLineConsumer(document);
            parser.parseForEach(fileReader, documentBuilder);

            assert 3 == document.size();
            // Access lines with stream
            document.forEach(line -> System.out.println(String.valueOf(line)));

            // Access lines with iterator
            Line firstLine = document.iterator().next();
            assert "Erik".equals(LineUtils.getStringCellValue(firstLine, "First name"));
            assert "Svensson".equals(LineUtils.getStringCellValue(firstLine, "Last name"));
            assert "Backstreet 43, 123 45  Thecity".equals(LineUtils.getStringCellValue(firstLine, "Address"));

            // Access lines by index
            Line secondLine = document.getLine(1);
            assert "Fredrik".equals(LineUtils.getStringCellValue(secondLine, "First name"));
            assert "Larsson".equals(LineUtils.getStringCellValue(secondLine, "Last name"));
            assert "\"Goaway 23\"; 444 44  Downtown".equals(LineUtils.getStringCellValue(secondLine, "Address"));

            Line thirdLine = document.getLine(2);
            assert "Alfred".equals(LineUtils.getStringCellValue(thirdLine, "First name"));
            assert "Nilsson".equals(LineUtils.getStringCellValue(thirdLine, "Last name"));
            assert "Homeway 17\n45678 Willage".equals(LineUtils.getStringCellValue(thirdLine, "Address"));
            return document;
        }
    }

    /**
     * This example demonstrates composing of CSV that conforms to RFC4180
     */
    public void composeCsvRfc4180()
            throws SchemaException, IOException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c3/csv-schema-rfc4180.xml");
             StringWriter writer = new StringWriter()) {
            Schema schema = Schema.ofXml(schemaReader);
            TextComposer composer = new TextComposer(schema, writer);
            // You can add cells with specific cell constructor
            Line line1 = new Line("Person")
                    .addCell(new StringCell("First name", "Erik"))
                    .addCell(new StringCell("Last name", "Eriksson"))
                    .addCell(new StringCell("Address", "\"Somewhere 23\", 12345  City"));

            // For each line, call composeLine
            composer.composeLine(line1);

            composer.composeLine(new Line("Person")
                    .addCell(new StringCell("First name", "Fredrik"))
                    .addCell(new StringCell("Last name", "Larsson"))
                    .addCell(new StringCell("Address", "Stariway 11; 65487  Town")));

            String output = writer.toString();
            System.out.println(output);
            String[] lines = output.split("\\n");
            assert 2 == lines.length;
            // Always quote last name
            // Detect when quoting is needed for address. The default quote behavior for string cells is AUTOMATIC.
            assert "Erik,\"Eriksson\",\"\"\"Somewhere 23\"\", 12345  City\"".equals(lines[0]);
            assert "Fredrik,\"Larsson\",Stariway 11; 65487  Town".equals(lines[1]);
        }
    }


    public static void main(String[] args) {
        ExampleQuotedCsv exampleA1 = new ExampleQuotedCsv();
        try {
            exampleA1.parseCsv();
            exampleA1.parseCsvRfc4180();
            exampleA1.composeCsv();
            exampleA1.composeCsvRfc4180();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
