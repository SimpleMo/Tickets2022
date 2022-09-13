package org.hse.course.tasks.data;

import org.hse.course.tasks.service.Visitor;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Интерфейс сущности Билет
 */
public interface Ticket {

    /**
     * @return экземпляр {@link SixDigitsTicketFactoryImpl}
     */
    static TicketFactory getSixDigitsTicketFactory() {
        return new SixDigitsTicketFactoryImpl();
    }

    /**
     * @return экземпляр {@link EightDigitsFactoryImpl}
     */
    static TicketFactory getEightDigitsTicketFactory() {
        return new EightDigitsFactoryImpl();
    }

    /**
     * Возвращает количество цифр в номере бидета
     *
     * @return количество цифр
     */
    int getDigitsQuantity();

    /**
     * @return Является ли билет счастливым?
     */
    default boolean isLucky() {
        int firstSum = 0, lastSum = 0;
        int[] digits = getDigits();
        for (int i = 0; i < getDigitsQuantity(); i++) {
            if (i < getDigitsQuantity() / 2) {
                firstSum += digits[i];
                continue;
            }

            lastSum += digits[i];
        }

        return lastSum == firstSum;
    }

    /**
     * Возвращает цифры номера билета
     * @return {@link int[]} цифр
     */
    private int[] getDigits() {
        int[] result = new int[getDigitsQuantity()];
        Arrays.fill(result, 0);

        for (int number = getNumber(), i = 0; number > 0; number = number / 10, i++) {
            result[i] = number % 10;
        }

        return result;
    }

    /**
     * @return номер билета
     */
    int getNumber();

    default <R> R accept(Visitor<Ticket, R> visitor) {
        return visitor.visit(this);
    }

    /**
     * Построитель экземпляров {@link Ticket}
     */
    interface TicketFactory {

        /**
         * Возвращает функцию, конструирующую билеты по номеру
         *
         * @return функция, конструирующая билеты по номеру
         */
        Function<Integer, Ticket> getTicketSupplier();

        /**
         * @param number номер билета
         * @return экзумпляр {@link Ticket}
         */
        default Ticket getInstance(int number) {
            return getTicketSupplier().apply(number);
        }
    }
}

/**
 * Реализация {@link Ticket.TicketFactory} для шестизначных билетов
 */
class SixDigitsTicketFactoryImpl implements Ticket.TicketFactory {

    @Override
    public Function<Integer, Ticket> getTicketSupplier() {
        return SixDigitsTicketImpl::new;
    }

    /**
     * Реализация {@link Ticket} для случая шестизначных билетов
     */
    private static class SixDigitsTicketImpl implements Ticket {
        private static final int DIGITS_COUNT = 6;
        private final int ticketNumber;

        /**
         * @param ticketNumber номер билета
         */
        public SixDigitsTicketImpl(int ticketNumber) {
            this.ticketNumber = ticketNumber;
        }

        @Override
        public int getNumber() {
            return this.ticketNumber;
        }

        @Override
        public int getDigitsQuantity() {
            return DIGITS_COUNT;
        }
    }
}

/**
 * Реализация {@link Ticket.TicketFactory} для восьмизначных билетов
 */
class EightDigitsFactoryImpl implements Ticket.TicketFactory {

    @Override
    public Function<Integer, Ticket> getTicketSupplier() {
        return EightDigitsTicketImpl::new;
    }

    /**
     * Реализация {@link Ticket} для случая восьмизначных билетов
     */
    private static class EightDigitsTicketImpl implements Ticket {
        private static final int DIGITS_COUNT = 8;
        private final int ticketNumber;

        private EightDigitsTicketImpl(int ticketNumber) {
            this.ticketNumber = ticketNumber;
        }

        @Override
        public int getNumber() {
            return this.ticketNumber;
        }

        @Override
        public int getDigitsQuantity() {
            return DIGITS_COUNT;
        }
    }
}
