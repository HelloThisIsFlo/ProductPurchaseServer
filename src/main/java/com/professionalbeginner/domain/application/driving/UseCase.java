package com.professionalbeginner.domain.application.driving;

/**
 * Represent a UseCase.
 *
 * A UseCase is a set of actions in the domain reduced to one method.
 * The call to the method is asynchronous and the value is returned using the provided callback.
 *
 */
public interface UseCase<T> {

    T execute();
}
