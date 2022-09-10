package org.hse.course.tasks;

import org.hse.course.tasks.service.TicketsProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Определяет количество счастливых билетов
 */
public class Tickets {
    private static Double staticDouble = Double.valueOf(20);

    /**
     * Точка входа
     *
     * @param args аргументы
     */
    public static void main(String[] args) {
        TicketsProcessor.getSixDigitsTicketProcessor().process();
//        TicketsProcessor.getEightDigitsTicketProcessor().process();

        Double d = Double.valueOf(10);

        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.setData(9000);

        /*Function<Double, String> func =
                val -> {
                    System.out.println(lambdaTest.getData());
                    lambdaTest.data = 100500;

                    return val.toString() + " " + staticDouble.toString() + " " + d.toString() + " " + lambdaTest.getData();
                };*/
        Function<Double, String> func = Tickets::doubleToString;

        // Tickets::testMethodReference;
        // Tickets::testMethodReferenceWithParam;
        // Runnable lambdaTestConstructor = LambdaTest::new;
        Consumer<Integer> integerConsumer = lambdaTest::setData;
        integerConsumer.accept(42);

        System.out.println(func.apply(100.0));
        System.out.println(lambdaTest.getData());
    }

    static String doubleToString(final Double val) {
        return val.toString() + " " + staticDouble.toString();
    }

    private void testMethodReference() {
        System.out.println("Test Method Reference id working...");
    }

    private void testMethodReferenceWithParam(LambdaTest t) {
        System.out.println("Test Method Reference id working..." + t.getData().toString());
    }

    static class LambdaTest {
        private Integer data;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }
    }

}
