package org.jsapar.examples.basics.b1;

import org.jsapar.Text2TextConverter;
import org.jsapar.error.JSaParException;
import org.jsapar.model.CellType;
import org.jsapar.schema.CsvSchema;
import org.jsapar.schema.FixedWidthSchema;
import org.jsapar.schema.Schema;

import java.io.*;

/**
 * Example converting a CSV file into a fixed width file.
 */
public class ExampleConvertCsvToFixedWidth {

    /**
     * Loads input and output schemas from xml files and converts a source csv file to a fixed width destination.
     */
    public void convert()
            throws IOException, JSaParException {

        try (Reader inSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b1/csv-schema.xml");
             Reader outSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b1/fw-schema.xml");
             Reader inReader = new FileReader("src/main/java/org/jsapar/examples/basics/b1/csv-unquoted.csv");
             Writer outWriter = new StringWriter()
             ) {
            Schema<?> parseSchema = Schema.ofXml(inSchemaReader);
            Schema<?> composeSchema = Schema.ofXml(outSchemaReader);
            Text2TextConverter converter = new Text2TextConverter(parseSchema, composeSchema);
            converter.convert(inReader, outWriter);
            String output = outWriter.toString();
            System.out.println(output);

            String[] lines = output.split("\\n");
            assert lines[0].equals("Erik             Svensson ");

        }
    }

    /**
     * Exactly the same example as above but this time both the input and the output schemas are built in java code
     * instead of loaded from xml file.
     */
    public void convertWithSchemaFromSource()
            throws IOException, JSaParException {

        try (Reader inReader = new FileReader("src/main/java/org/jsapar/examples/basics/b1/csv-unquoted.csv");
             Writer outWriter = new StringWriter()
        ) {
            Schema<?> parseSchema = CsvSchema.builder()
                    .withLineSeparator("\n")
                    .withLine("Person", line->line.withCellSeparator(",")
                            .withCell("First name")
                            .withCell("Middle name", cell->cell.withIgnoreRead(true))
                            .withCell("Last name")
                            .withCell("Has dog", cell->cell.withType(CellType.BOOLEAN).withPattern("yes;no"))
                    )
                    .build();

            Schema<?> composeSchema = FixedWidthSchema.builder()
                    .withLineSeparator("\n")
                    .withLine("Person", line->line
                            .withCell("First name", 8)
                            .withCell("Middle name", 9, cell->cell.withIgnoreRead(true))
                            .withCell("Last name", 9)
                    )
                    .build();


            Text2TextConverter converter = new Text2TextConverter(parseSchema, composeSchema);
            converter.convert(inReader, outWriter);
            String output = outWriter.toString();
            System.out.println(output);

            String[] lines = output.split("\\n");
            assert lines[0].equals("Erik             Svensson ");

        }
    }


    public static void main(String[] args) {
        ExampleConvertCsvToFixedWidth exampleB1 = new ExampleConvertCsvToFixedWidth();
        try {
            exampleB1.convert();
            exampleB1.convertWithSchemaFromSource();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
