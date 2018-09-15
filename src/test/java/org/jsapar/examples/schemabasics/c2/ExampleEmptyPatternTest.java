package org.jsapar.examples.schemabasics.c2;

import org.junit.Test;

import java.io.IOException;

public class ExampleEmptyPatternTest {

    @Test
    public void parseCsv() throws IOException {
        new ExampleEmptyPattern().parseCsv();
    }
}