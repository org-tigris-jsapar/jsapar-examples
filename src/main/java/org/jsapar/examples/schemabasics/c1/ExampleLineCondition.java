package org.jsapar.examples.schemabasics.c1;

import org.jsapar.Text2TextConverter;
import org.jsapar.TextParser;
import org.jsapar.error.JSaParException;
import org.jsapar.model.Document;
import org.jsapar.model.Line;
import org.jsapar.model.LineUtils;
import org.jsapar.parse.DocumentBuilderLineConsumer;
import org.jsapar.schema.Schema;

import java.io.*;
import java.time.LocalDate;

public class ExampleLineCondition {

    /**
     * This example shows how to parse a csv where first line denotes the type of the line.
     *
     * @throws IOException In case of error reading file.
     */
    public void parseCsv() throws IOException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c1/csv-linecondition-schema.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c1/csv-linecondition.csv")) {
            Schema<?> schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineConsumer documentBuilder = new DocumentBuilderLineConsumer(document);
            parser.parseForEach(fileReader, documentBuilder);

            Line headerLine = document.getLine(0);
            assert "csv-linecondition.csv".equals(LineUtils.getStringCellValue(headerLine, "FileName"));
            assert LocalDate.parse("2007-07-07").equals(LineUtils.getLocalDateCellValue(headerLine, "Created date").orElse(null));
            assert "Header".equals(headerLine.getLineType());
            Line line1 = document.getLine(1);
            assert "Person".equals(line1.getLineType());
            assert "Svensson".equals(LineUtils.getStringCellValue(line1, "Last name"));
            assert "Erik".equals(LineUtils.getStringCellValue(line1, "First name"));
            assert "Svensson".equals(LineUtils.getStringCellValue(line1, "Last name"));
            Line line2 = document.getLine(2);
            assert "Fredrik".equals(LineUtils.getStringCellValue(line2, "First name"));
            assert "Larsson".equals(LineUtils.getStringCellValue(line2, "Last name"));
            Line footerLine = document.getLine(3);
            assert "2".equals(LineUtils.getStringCellValue(footerLine, "Rowcount"));
        }
    }

    /**
     * This example shows how to parse a csv where first line denotes the type of the line.
     *
     * @throws IOException In case of error reading file.
     */
    public void parseFixedWidth() throws IOException {
        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c1/fw-linecondition-schema.xml");
             Reader fileReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c1/fw-linecondition.txt")) {
            Schema<?> schema = Schema.ofXml(schemaReader);
            TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineConsumer documentBuilder = new DocumentBuilderLineConsumer(document);
            parser.parseForEach(fileReader, documentBuilder);

            Line headerLine = document.getLine(0);
            assert "Example c1".equals(LineUtils.getStringCellValue(headerLine, "FileName"));
            assert LocalDate.parse("2007-07-07").equals(LineUtils.getLocalDateCellValue(headerLine, "Created date").orElse(null));
            assert "Header".equals(headerLine.getLineType());
            Line line1 = document.getLine(1);
            assert "Person".equals(line1.getLineType());
            assert "Svensson".equals(LineUtils.getStringCellValue(line1, "Last name"));
            assert "Erik".equals(LineUtils.getStringCellValue(line1, "First name"));
            assert "Svensson".equals(LineUtils.getStringCellValue(line1, "Last name"));
            Line line2 = document.getLine(2);
            assert "Fredrik".equals(LineUtils.getStringCellValue(line2, "First name"));
            assert "Larsson".equals(LineUtils.getStringCellValue(line2, "Last name"));
            Line petLine = document.getLine(3);
            assert "The mouse".equals(LineUtils.getStringCellValue(petLine, "Name"));
            Line footerLine = document.getLine(4);
            assert "2".equals(LineUtils.getStringCellValue(footerLine, "Rowcount"));
        }
    }

    /**
     * This example shows an example of converting from a fixed width file with line conditions to a csv.
     * <p>
     * It shows that line types are matched by name in the input and output schemas and when there is no matching line
     * type (as with Pet) the line is ignored in the output.
     *
     * @throws IOException     In case of error reading the file
     * @throws JSaParException In case of parse or compose error.
     */
    public void convert()
            throws IOException, JSaParException {

        try (Reader inSchemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c1/fw-linecondition-schema.xml");
             Reader outSchemaReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c1/csv-linecondition-schema.xml");
             Reader inReader = new FileReader("src/main/java/org/jsapar/examples/schemabasics/c1/fw-linecondition.txt");
             Writer outWriter = new StringWriter()
        ) {
            Schema<?> parseSchema = Schema.ofXml(inSchemaReader);
            Schema<?> composeSchema = Schema.ofXml(outSchemaReader);
            Text2TextConverter converter = new Text2TextConverter(parseSchema, composeSchema);
            converter.convert(inReader, outWriter);
            String output = outWriter.toString();
            System.out.println(output);

            String[] lines = output.split("\\n");
            assert lines[0].equals("H;Example c1;2007-07-07");
            assert lines[1].equals("B;\"Erik\";;Svensson");
        }

    }

}
