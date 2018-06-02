package org.jsapar.examples;

import org.jsapar.examples.introduction.a1.ExampleA1ParseCsv;
import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

public class TestAllExamples {
    @Test
    public void testAll() throws SchemaException, IOException {
        new ExampleA1ParseCsv().parseCsv();
    }
}
