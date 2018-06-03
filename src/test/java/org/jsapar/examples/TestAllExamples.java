package org.jsapar.examples;

import org.jsapar.examples.basics.b1.ExampleB1ConvertCsvToFixedWidth;
import org.jsapar.examples.introduction.a1.ExampleA1ParseAndComposeCsv;
import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

public class TestAllExamples {
    @Test
    public void testA1() throws SchemaException, IOException {
        new ExampleA1ParseAndComposeCsv().parseCsv();
        new ExampleA1ParseAndComposeCsv().composeCsv();
    }

    @Test
    public void testB1() throws SchemaException, IOException {
        new ExampleB1ConvertCsvToFixedWidth().convert();
    }

}
