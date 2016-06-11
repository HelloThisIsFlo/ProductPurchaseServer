package com.professionalbeginner.domain.core.executor;

public class FakeMainThreadUseCaseExecutor implements UseCaseExecutor {

    @Override
    public void executeWhenResourceAvailable(Runnable toSchedule) {
        toSchedule.run();
    }
}
