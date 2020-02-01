package org.jsapar.examples.basics.b1;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

public class ExampleConvertCsvToFixedWidthTest {

    @Test
    public void convert() throws IOException, SchemaException {
        new ExampleConvertCsvToFixedWidth().convert();
    }

    @Test
    public void convert_schemaFromSource() throws IOException, SchemaException {
        new ExampleConvertCsvToFixedWidth().convertWithSchemaFromSource();
    }

}