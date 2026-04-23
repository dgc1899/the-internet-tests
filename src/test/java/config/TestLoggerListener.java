package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestLoggerListener extends TestListenerAdapter {
    private static final Logger logger = LogManager.getLogger(TestLoggerListener.class);

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.info(String.format("Test %s.%s was successful",tr.getTestClass(), tr.getMethod().getMethodName()));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger.error(String.format("Test %s.%s execution failed. Exception: %s",tr.getTestClass(),
                tr.getMethod().getMethodName(), tr.getThrowable()));
    }

    @Override
    public void onTestStart(ITestResult tr) {
        logger.error(String.format("Test %s.%s execution started", tr.getTestClass(), tr.getMethod().getMethodName()));
    }
}
