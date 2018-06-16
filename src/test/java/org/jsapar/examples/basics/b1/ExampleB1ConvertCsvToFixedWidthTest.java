package org.jsapar.examples.basics.b1;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ExampleB1ConvertCsvToFixedWidthTest {

    @Test
    public void convert() throws IOException, SchemaException {
        new ExampleB1ConvertCsvToFixedWidth().convert();
    }
}