package org.hse.course.tasks.service;

import org.hse.course.tasks.data.Ticket;

/**
 * Обработчик билетов
 */
//todo дать задание на усовершенствование интерфейса TicketsProcessor по аналогии с Ticket и фабрикой билетов
public interface TicketsProcessor {
    /**
     * @return экземпляр {@link TicketsProcessor}
     */
    static TicketsProcessor getSixDigitsTicketProcessor() {
        return new SixDigitTicketsProcessorImpl();
    }

    static TicketsProcessor getEightDigitsTicketProcessor() {
        return new EightDigitsTicketsProcessorImpl();
    }

    /**
     * Возвращает результат обработки
     */
    void process();
}

/**
 * Реализация {@link TicketsProcessor} для подсчёта количества шестизначных счастливых билетов
 */
class SixDigitTicketsProcessorImpl implements TicketsProcessor {
    private static final int DIGITS_COUNT = 6;

    @Override
    public void process() {
        int result = 0;
        Ticket.TicketFactory factory = Ticket.getSixDigitsTicketFactory();
        for(int number = 0; number < Math.pow(10, DIGITS_COUNT); number++) {
            if (!factory.getInstance(number).isLucky()) {
                continue;
            }

            result++;
        }

        System.out.println(result);
    }
}

/**
 * Реализация {@link TicketsProcessor} для подсчёта количества восьмизгачных счастливых билетов
 */
class EightDigitsTicketsProcessorImpl implements TicketsProcessor {
    private static final int DIGITS_COUNT = 8;

    @Override
    public void process() {
        int result = 0;
        Ticket.TicketFactory factory = Ticket.getEightDigitsTicketFactory();
        for(int number = 0; number < Math.pow(10, DIGITS_COUNT); number++) {
            if (!factory.getInstance(number).isLucky()) {
                continue;
            }

            result++;
        }

        System.out.println(result);
    }
}
