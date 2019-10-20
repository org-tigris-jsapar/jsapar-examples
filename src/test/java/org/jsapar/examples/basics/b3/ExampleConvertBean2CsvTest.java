package org.jsapar.examples.basics.b3;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

public class ExampleConvertBean2CsvTest {

    @Test
    public void convertBean2Csv() throws ClassNotFoundException, SchemaException, IOException {
        new ExampleConvertBean2Csv().convertBean2Csv();
    }

    @Test
    public void convertBeanCollection2Csv() throws ClassNotFoundException, SchemaException, IOException {
        new ExampleConvertBean2Csv().convertBeanCollection2Csv();
    }

}