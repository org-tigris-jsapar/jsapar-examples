package org.jsapar.examples.introduction.a1;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

public class ExampleParseAndComposeCsvTest {

    @Test
    public void parseCsv() throws SchemaException, IOException {
        new ExampleParseAndComposeCsv().parseCsv();
    }

    @Test
    public void composeCsv() throws SchemaException, IOException {
        new ExampleParseAndComposeCsv().composeCsv();
    }
}