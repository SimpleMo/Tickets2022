package org.hse.course.tasks.service;

import org.hse.course.tasks.data.Ticket;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Обработчик билетов
 */
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

    static TicketsProcessor getFourDigitsTicketProcessor() {
        return new FourDigitsTicketsProcessorImpl();
    }

    /**
     * @return функция, возвращающая билет по его номеру
     */
    Function<Integer, Ticket> getTicketSupplier();

    /**
     * @return количество цифр в билете
     */
    Integer getDigitsQuantity();

    default Boolean testTicketByNumber(Integer number) {
        return Optional
                .ofNullable(number)
                .map(getTicketSupplier())
                .map(Ticket::isLucky)
//                .orElseThrow(() -> new IllegalArgumentException("Не могу работать в таких условиях! Что за null?!"));
                .orElse(Boolean.FALSE);
    }

    /**
     * Возвращает результат обработки
     */
    default void process() {
        Predicate<Ticket> lucky = Ticket::isLucky;
        var rightExclusive = (int) Math.pow(10, getDigitsQuantity());
        Stream<Integer> integerStream =
                IntStream
                        .range(1, rightExclusive)
                        .parallel()
                        .mapToObj(getTicketSupplier()::apply)
                        .filter(lucky)
                        .map(Ticket::getNumber)
                        .filter(num -> (num % 7) == 0);
        var result = integerStream.reduce(rightExclusive, (acc, item) -> acc > item ? item : acc);
        List<Integer> tickets =
                IntStream
                        .range(1, rightExclusive)
                        .mapToObj(getTicketSupplier()::apply)
                        .filter(lucky)
                        .map(Ticket::getNumber)
                        .filter(num -> (num % 7) == 0)
                        .sorted(Integer::compareTo)
                        .collect(Collectors.toList());

        /*for(var number = 0; number < Math.pow(10, getDigitsQuantity()); number++) {
            Predicate<Ticket> lucky = Ticket::isLucky;
            if (Predicate.not(lucky).test(getTicketSupplier().apply(number))) {
                continue;
            }

            result++;
        }*/

        System.out.println(result);
    }
}

/**
 * Реализация {@link TicketsProcessor} для подсчёта количества шестизначных счастливых билетов
 */
class SixDigitTicketsProcessorImpl implements TicketsProcessor {

    @Override
    public Function<Integer, Ticket> getTicketSupplier() {
        return Ticket.getSixDigitsTicketFactory()::getInstance;
    }

    @Override
    public Integer getDigitsQuantity() {
        return 6;
    }
}

/**
 * Реализация {@link TicketsProcessor} для подсчёта количества восьмизначных счастливых билетов
 */
class EightDigitsTicketsProcessorImpl implements TicketsProcessor {

    @Override
    public Function<Integer, Ticket> getTicketSupplier() {
        return Ticket.getEightDigitsTicketFactory()::getInstance;
    }

    @Override
    public Integer getDigitsQuantity() {
        return 8;
    }
}

/**
 * Реализация {@link TicketsProcessor} для подсчёта количества четырёхзначных счастливых билетов
 */
class FourDigitsTicketsProcessorImpl implements TicketsProcessor {

    @Override
    public Function<Integer, Ticket> getTicketSupplier() {
        return Ticket.getFourDigitsTicketFactory()::getInstance;
    }

    @Override
    public Integer getDigitsQuantity() {
        return 4;
    }
}
