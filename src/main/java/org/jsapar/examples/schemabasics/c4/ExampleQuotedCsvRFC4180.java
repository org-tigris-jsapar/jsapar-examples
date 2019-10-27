package org.jsapar.examples.schemabasics.c4;

import org.jsapar.TextComposer;
import org.jsapar.TextParser;
import org.jsapar.error.JSaParException;
import org.jsapar.model.Document;
import org.jsapar.model.Line;
import org.jsapar.model.LineUtils;
import org.jsapar.model.StringCell;
import org.jsapar.parse.DocumentBuilderLineEventListener;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

/**
 * This example demonstrates parsing and composing CSV that conforms to RFC4180
 */
public class ExampleQuotedCsvRFC4180 {

    public Document parseCsv() throws SchemaException, IOException, JSaParException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c4/csv-schema.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c4/csv-quoted.csv")) {
            Schema schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineEventListener listener = new DocumentBuilderLineEventListener(document);
            parser.parse(fileReader, listener);

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

    public void composeCsv()
            throws SchemaException, IOException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c4/csv-schema.xml");
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
        ExampleQuotedCsvRFC4180 exampleA1 = new ExampleQuotedCsvRFC4180();
        try {
            exampleA1.parseCsv();
            exampleA1.composeCsv();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
