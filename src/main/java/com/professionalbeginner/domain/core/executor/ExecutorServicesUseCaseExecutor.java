package com.professionalbeginner.domain.core.executor;

import com.professionalbeginner.domain.application.driving.UseCase;

import java.util.concurrent.ExecutorService;

/**
 * Created by Florian on 10/06/16.
 */
public class ExecutorServicesUseCaseExecutor implements UseCaseExecutor {

    private final ExecutorService executorService;

    public ExecutorServicesUseCaseExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void executeWhenResourceAvailable(Runnable toSchedule) {
        executorService.execute(toSchedule);
    }
}
