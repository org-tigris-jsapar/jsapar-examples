package org.jsapar.examples.basics.b4;

import org.jsapar.Text2TextConverter;
import org.jsapar.error.JSaParException;
import org.jsapar.model.LineUtils;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.*;

/**
 * Example converting a CSV file into a fixed width file.
 * <p>
 * This example uses a lambda expression but you could just as well use a class that implements the
 * LineManipulator interface.
 * <p>
 * The example uses two existing cells ("First name" and "Last name" ) to create the value of a new cell called
 */
public class ExampleManipulateWhileConvert {

    public void convert() throws IOException, JSaParException, SchemaException {

        File outFile = new File("examples/02_Names_out.txt");
        try (Reader inSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b4/csv-schema.xml");
                Reader outSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b4/fw-schema.xml");
                Reader inReader = new FileReader("src/main/java/org/jsapar/examples/basics/b4/csv-unquoted.csv");
                Writer outWriter = new StringWriter()) {

            Schema<?> parseSchema = Schema.ofXml(inSchemaReader);
            Schema<?> composeSchema = Schema.ofXml(outSchemaReader);
            Text2TextConverter converter = new Text2TextConverter(parseSchema, composeSchema);

            // This is where we add the manipulator
            converter.addLineManipulator(line -> {
                final String name = LineUtils.getStringCellValue(line, "First name") + " " + LineUtils
                        .getStringCellValue(line, "Last name");

                LineUtils.setStringCellValue(line, "Name", name);

                // Return true for all lines that should be in output
                // This line returns all Edgar from output.
                return !name.startsWith("Edgar");
            });
            converter.convert(inReader, outWriter);
            String output = outWriter.toString();
            System.out.println(output);

            String[] lines = output.split("\\n");
            assert lines[0].equals("Erik Svensson            Y");

        }

    }

    public static void main(String[] args) {
        ExampleManipulateWhileConvert exampleB1 = new ExampleManipulateWhileConvert();
        try {
            exampleB1.convert();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
