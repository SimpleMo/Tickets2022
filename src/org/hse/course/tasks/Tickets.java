package org.hse.course.tasks;

import org.hse.course.tasks.service.TicketsProcessor;

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
        TicketsProcessor.getFourDigitsTicketProcessor().process();
        TicketsProcessor.getSixDigitsTicketProcessor().process();
//        TicketsProcessor.getEightDigitsTicketProcessor().process();
        System
            .out
            .println(TicketsProcessor.getFourDigitsTicketProcessor().testTicketByNumber(1001));
        System
            .out
            .println(TicketsProcessor.getFourDigitsTicketProcessor().testTicketByNumber(1011));
        System
            .out
            .println(TicketsProcessor.getFourDigitsTicketProcessor().testTicketByNumber(null));
    }
}
