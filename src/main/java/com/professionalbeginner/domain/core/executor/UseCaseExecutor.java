package com.professionalbeginner.domain.core.executor;

import com.professionalbeginner.domain.application.driving.UseCase;

public interface UseCaseExecutor {



    void executeWhenResourceAvailable(Runnable toSchedule);
}
