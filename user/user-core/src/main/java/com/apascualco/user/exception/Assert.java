package com.apascualco.user.exception;

import java.lang.reflect.Constructor;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

public final  class Assert<E> {

    private final E object;
    private Predicate<E> predicate;

    private Assert(final E object) {
        this.object = object;
    }

    public static <E> Assert<E> assertion(E obecjt) {
        return new Assert<>(obecjt);
    }

    public Assert<E> verify(final Predicate<E> predicate) {
        this.predicate = predicate;
        return this;
    }

    public <X extends Throwable> void thenThrow(Supplier<? extends X> exceptionSupplier) throws Throwable {
        this.thenThrow(exceptionSupplier,null);
    }


    @SuppressWarnings("squid:S112")
    public <X extends Throwable> void thenThrow(Supplier<? extends X> exceptionSupplier, final String message) throws Throwable {
        assert nonNull(predicate) : "The predicate can't be null";
        if(predicate.test(this.object)) {
            Throwable throwable = exceptionSupplier.get();
            if(nonNull(message)) {
                Constructor<?> constructor = exceptionSupplier.get().getClass().getConstructor(String.class);
                throwable = (Throwable) constructor.newInstance(message);
            }
            throw throwable;
        }

    }
}
