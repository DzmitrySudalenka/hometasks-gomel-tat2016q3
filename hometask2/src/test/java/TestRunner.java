import org.testng.TestNG;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dzmitry Sudalenka on 9/23/2016.
 */
public class TestRunner {
    public static void main(String[] args) {
        final String SUIT_PATH = "./src/test/resources/suites/";
        final String BOY_TESTS = "boy.xml";
        final String GIRL_TESTS = "girl.xml";
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(
                SUIT_PATH + BOY_TESTS,
                SUIT_PATH + GIRL_TESTS);
        testNG.setTestSuites(files);
        testNG.run();
    }
}
