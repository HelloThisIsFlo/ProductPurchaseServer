package com.professionalbeginner.domain.core.executor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ExecutorService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class ExecutorServicesUseCaseExecutorTest {


    UseCaseExecutor useCaseExecutor;

    @Mock
    ExecutorService executorService;
    @Mock
    Runnable useCaseRunnable;
    @Captor
    ArgumentCaptor<Runnable> runnableArgumentCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        useCaseExecutor = new ExecutorServicesUseCaseExecutor(executorService);
    }

    @Test
    public void execute_delegatesToExecutor() throws Exception {
        useCaseExecutor.executeWhenResourceAvailable(useCaseRunnable);
        verify(executorService).execute(any());
    }
}