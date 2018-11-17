package org.jsapar.examples.basics.b3;

import org.jsapar.Bean2TextConverter;
import org.jsapar.BeanCollection2TextConverter;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.beans.IntrospectionException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ExampleConvertBean2Csv {

    /**
     * Example of converting Employee beans one by one into a CSV defined by a schema.
     */
    public void convertBean2Csv()
            throws IOException, SchemaException {

        Collection<Employee> employees = makeEmployees();

        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b3/csv-schema.xml");
             StringWriter writer = new StringWriter()
        ) {
            Schema composeSchema = Schema.ofXml(schemaReader);


            Bean2TextConverter<Employee> converter = new Bean2TextConverter<>(composeSchema, writer);
            for (Employee employee : employees) {
                converter.convert(employee);
            }

            String result = writer.toString();
            System.out.println("== Example b3 - convertBean2Csv ==");
            System.out.println(result);
            String[] lines = result.split(composeSchema.getLineSeparator());
            assert lines.length == 2;
        }
    }

    /**
     * Example of converting a collection of Employee beans into a CSV defined by a schema.
     */
    public void convertBeanCollection2Csv()
            throws IOException, SchemaException, IntrospectionException, ClassNotFoundException {

        Collection<Employee> employees = makeEmployees();

        try (Reader schemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b3/csv-schema.xml");
             StringWriter writer = new StringWriter()
        ) {
            Schema composeSchema = Schema.ofXml(schemaReader);


            BeanCollection2TextConverter<Employee> converter = new BeanCollection2TextConverter<>(composeSchema);
            converter.convert(employees, writer);

            String result = writer.toString();
            System.out.println("== Example b3 - convertBeanCollection2Csv ==");
            System.out.println(result);
            String[] lines = result.split(composeSchema.getLineSeparator());
            assert lines.length == 2;
        }
    }

    private Collection<Employee> makeEmployees() {
        List<Employee> people = new LinkedList<>();
        Employee testPerson1 = new Employee("Nisse Holgersson", 4, new Address("Goose street 34"), LocalDate.parse("1932-08-07"));
        people.add(testPerson1);

        Employee testPerson2 = new Employee("Jonte Lionheart", 17, new Address("Cave road 12"), LocalDate.parse("1977-03-02"));
        people.add(testPerson2);

        return people;
    }


    public static void main(String[] args) {
        ExampleConvertBean2Csv example = new ExampleConvertBean2Csv();
        try {
            example.convertBean2Csv();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
