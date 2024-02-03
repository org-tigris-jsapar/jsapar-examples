package org.jsapar.examples.basics.b2;

import org.jsapar.Text2BeanConverter;
import org.jsapar.parse.CollectingConsumer;
import org.jsapar.schema.Schema;
import org.jsapar.schema.SchemaException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.atomic.AtomicInteger;

public class ExampleConvertCsvToBean {

    public void convertToBeanLambda()
            throws IOException, SchemaException {
        try (Reader inSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b2/csv-schema.xml");
                Reader inReader = new FileReader("src/main/java/org/jsapar/examples/basics/b2/csv-employees.csv")) {
            Schema<?> parseSchema = Schema.ofXml(inSchemaReader);
            Text2BeanConverter<Employee> converter = new Text2BeanConverter<>(parseSchema);
            AtomicInteger counter = new AtomicInteger(
                    0); // This is just to be able to count calls to lambda expression.
            converter.convertForEach(inReader, bean -> {
                int c = counter.incrementAndGet();
                System.out.printf("Employee with count %d: %s\n", c, bean);
            });
            assert counter.get() == 3;
        }
    }

    /**
     * Same example as above but using {@link CollectingConsumer}
     */
    public void convertToBeanCollection()
            throws IOException, SchemaException {
        try (Reader inSchemaReader = new FileReader("src/main/java/org/jsapar/examples/basics/b2/csv-schema.xml");
                Reader inReader = new FileReader("src/main/java/org/jsapar/examples/basics/b2/csv-employees.csv")) {
            Schema<?> parseSchema = Schema.ofXml(inSchemaReader);
            Text2BeanConverter<Employee> converter = new Text2BeanConverter<>(parseSchema);
            CollectingConsumer<Employee> listener = new CollectingConsumer<>();
            converter.convertForEach(inReader, listener);
            assert listener.size() == 3;
            listener.forEach(employee -> System.out.println("Employee " + employee));
        }
    }

    public static void main(String[] args) {
        ExampleConvertCsvToBean exampleB1 = new ExampleConvertCsvToBean();
        try {
            exampleB1.convertToBeanLambda();
            exampleB1.convertToBeanCollection();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
