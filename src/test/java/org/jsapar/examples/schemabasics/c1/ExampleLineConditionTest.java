package org.jsapar.examples.schemabasics.c1;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ExampleLineConditionTest {

    @Test
    public void parseCsv() throws IOException {
        new ExampleLineCondition().parseCsv();
    }

    @Test
    public void parseFixedWidth() throws IOException {
        new ExampleLineCondition().parseFixedWidth();
    }

    @Test
    public void convert() throws IOException {
        new ExampleLineCondition().convert();
    }

}