package org.jsapar.examples.basics.b1;

import org.jsapar.Text2TextConverter;
import org.jsapar.error.JSaParException;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.*;

/**
 * Example converting a CSV file into a fixed width file.
 */
public class ExampleConvertCsvToFixedWidth {

    public void convert()
            throws IOException, JSaParException, SchemaException {

        File outFile = new File("examples/02_Names_out.txt");
        try (Reader inSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b1/csv-schema.xml");
             Reader outSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b1/fw-schema.xml");
             Reader inReader = new FileReader("src/main/java/org/jsapar/examples/basics/b1/csv-unquoted.csv");
             Writer outWriter = new StringWriter()
             ) {
            Schema parseSchema = Schema.ofXml(inSchemaReader);
            Schema composeSchema = Schema.ofXml(outSchemaReader);
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
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
