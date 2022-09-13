package org.hse.course.tasks.service;

import org.hse.course.tasks.data.Ticket;

/**
 * Интерфейс Посетитель
 *
 * @param <T> тип сущности, которую можно "посетить"
 * @param <R> результат работы посетителя
 */
public interface Visitor<T, R> {

    R visit(T entity);
}

class CheckEvenVisitor implements Visitor<Ticket, Boolean> {

    @Override
    public Boolean visit(Ticket entity) {
        return (entity.getNumber() % 2) == 0;
    }
}


