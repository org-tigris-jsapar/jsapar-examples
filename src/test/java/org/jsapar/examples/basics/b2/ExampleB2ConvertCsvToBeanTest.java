package org.jsapar.examples.basics.b2;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ExampleB2ConvertCsvToBeanTest {

    @Test
    public void convertToBeanLambda()
            throws ClassNotFoundException, SchemaException, IntrospectionException, IOException {
        new ExampleB2ConvertCsvToBean().convertToBeanLambda();
    }

    @Test
    public void convertToBeanCollection()
            throws ClassNotFoundException, SchemaException, IntrospectionException, IOException {
        new ExampleB2ConvertCsvToBean().convertToBeanCollection();
    }

}