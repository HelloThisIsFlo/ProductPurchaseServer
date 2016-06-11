package com.professionalbeginner.domain.core.executor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Florian on 10/06/16.
 */
public class FakeMainThreadUseCaseExecutorTest {

    UseCaseExecutor executor;

    @Mock
    Runnable runnable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        executor = new FakeMainThreadUseCaseExecutor();
    }

    @Test
    public void doNotUseThread_callDirectlyRunnable() throws Exception {
        executor.executeWhenResourceAvailable(runnable);
        verify(runnable).run();
    }
}