package com.professionalbeginner.domain.application.driving;

import com.professionalbeginner.domain.core.executor.UseCaseExecutor;

public abstract class UseCase<T> {


    private final UseCaseExecutor useCaseExecutor;

    public UseCase(UseCaseExecutor useCaseExecutor) {
        this.useCaseExecutor = useCaseExecutor;
    }

    public interface OnSuccessCallback<U> {
        void onSuccess(U result);
    }

    public void execute(OnSuccessCallback<T> onSuccessCallback) {
        useCaseExecutor.executeWhenResourceAvailable(
                new Runnable() {
                    @Override
                    public void run() {
                        doWork(onSuccessCallback);
                    }
                }
        );
    }

    protected abstract void doWork(OnSuccessCallback<T> onSuccessCallback);

}
