import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * Created by Dzmitry Sudalenka on 9/23/2016.
 */
public class TestListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("[METHOD_STARTED] - " + method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println(String.format("[METHOD_FINISHED] - %s >>> %s",
                method.getTestMethod().getMethodName(), testResult.getStatus() == 1 ? "Success" : "Fail"));
    }
}
