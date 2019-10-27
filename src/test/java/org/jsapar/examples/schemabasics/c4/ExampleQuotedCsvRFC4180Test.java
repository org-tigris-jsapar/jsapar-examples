package org.jsapar.examples.schemabasics.c4;

import org.jsapar.examples.schemabasics.c3.ExampleQuotedCsv;
import org.jsapar.model.Document;
import org.jsapar.model.Line;
import org.jsapar.model.LineUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ExampleQuotedCsvRFC4180Test {

    @Test
    public void parseCsv() throws IOException {
        Document document = new ExampleQuotedCsvRFC4180().parseCsv();
        // Access lines with iterator
        Line firstLine = document.iterator().next();
        assertEquals("Erik", LineUtils.getStringCellValue(firstLine, "First name")) ;
        assertEquals("Svensson", LineUtils.getStringCellValue(firstLine, "Last name"));
        assertEquals("Backstreet 43, 123 45  Thecity", LineUtils.getStringCellValue(firstLine, "Address"));

        // Access lines by index
        Line secondLine = document.getLine(1);
        assertEquals("Fredrik", LineUtils.getStringCellValue(secondLine, "First name"));
        assertEquals("Larsson", LineUtils.getStringCellValue(secondLine, "Last name"));
        assertEquals("\"Goaway 23\"; 444 44  Downtown", LineUtils.getStringCellValue(secondLine, "Address"));

        Line thirdLine = document.getLine(2);
        assertEquals("Alfred", LineUtils.getStringCellValue(thirdLine, "First name"));
        assertEquals("Nilsson", LineUtils.getStringCellValue(thirdLine, "Last name"));
        assertEquals("Homeway 17\n45678 Willage",  LineUtils.getStringCellValue(thirdLine, "Address"));

    }

    @Test
    public void composeCsv() throws IOException {
        new ExampleQuotedCsvRFC4180().composeCsv();
    }

}