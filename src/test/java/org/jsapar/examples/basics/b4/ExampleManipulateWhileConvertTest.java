package org.jsapar.examples.basics.b4;

import org.jsapar.schema.SchemaException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ExampleManipulateWhileConvertTest {

    @Test
    public void convert() throws IOException, SchemaException {
        new ExampleManipulateWhileConvert().convert();
    }
}