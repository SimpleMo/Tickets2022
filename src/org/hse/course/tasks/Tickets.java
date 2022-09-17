package org.hse.course.tasks;

import org.hse.course.tasks.data.Ticket;
import org.hse.course.tasks.service.TicketsProcessor;
import org.hse.course.tasks.service.Visitor;

import java.util.function.Supplier;

/**
 * Определяет количество счастливых билетов
 */
public class Tickets {
    /**
     * Точка входа
     *
     * @param args аргументы
     */
    public static void main(String[] args) {
        var sixDigitsTicketProcessor = TicketsProcessor.getSixDigitsTicketProcessor();
        var fourDigitsTicketProcessor = TicketsProcessor.getFourDigitsTicketProcessor();
        //todo добавить пример использования ещё одного посетитетеля (см. комментарий в Visitor)
        Supplier<Visitor<Ticket, Boolean>> strategy = () -> {
            var visitorFactory = Visitor.getEvenVisitorFactory();
            return visitorFactory.getInstance();
        };

        sixDigitsTicketProcessor.setStrategy(strategy);
        sixDigitsTicketProcessor.process();
//        TicketsProcessor.getEightDigitsTicketProcessor().process();
        fourDigitsTicketProcessor.setStrategy(strategy);
        fourDigitsTicketProcessor.process();

        System
            .out
            .println(fourDigitsTicketProcessor.testTicketByNumber(1001));
        System
            .out
            .println(fourDigitsTicketProcessor.testTicketByNumber(1011));
        System
            .out
            .println(fourDigitsTicketProcessor.testTicketByNumber(null));
    }
}
