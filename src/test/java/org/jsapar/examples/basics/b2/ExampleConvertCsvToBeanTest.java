package org.jsapar.examples.basics.b2;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

public class ExampleConvertCsvToBeanTest {

    @Test
    public void convertToBeanLambda()
            throws ClassNotFoundException, SchemaException, IOException {
        new ExampleConvertCsvToBean().convertToBeanLambda();
    }

    @Test
    public void convertToBeanCollection()
            throws ClassNotFoundException, SchemaException, IOException {
        new ExampleConvertCsvToBean().convertToBeanCollection();
    }

}