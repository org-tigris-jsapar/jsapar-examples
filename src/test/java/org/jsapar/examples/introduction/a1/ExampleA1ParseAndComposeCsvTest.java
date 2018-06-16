package org.jsapar.examples.introduction.a1;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ExampleA1ParseAndComposeCsvTest {

    @Test
    public void parseCsv() throws SchemaException, IOException {
        new ExampleA1ParseAndComposeCsv().parseCsv();
    }

    @Test
    public void composeCsv() throws SchemaException, IOException {
        new ExampleA1ParseAndComposeCsv().composeCsv();
    }
}