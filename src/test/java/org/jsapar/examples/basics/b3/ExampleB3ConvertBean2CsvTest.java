package org.jsapar.examples.basics.b3;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ExampleB3ConvertBean2CsvTest {

    @Test
    public void convertBean2Csv() throws ClassNotFoundException, SchemaException, IntrospectionException, IOException {
        new ExampleB3ConvertBean2Csv().convertBean2Csv();
    }

    @Test
    public void convertBeanCollection2Csv() throws ClassNotFoundException, SchemaException, IntrospectionException, IOException {
        new ExampleB3ConvertBean2Csv().convertBeanCollection2Csv();
    }

}