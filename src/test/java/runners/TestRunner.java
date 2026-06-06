package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import utils.TestRailReporter;


class TestRunner {

    @Test
    void testParallel() {

        Results results = Runner.path("classpath:features")
                .parallel(1);

        TestRailReporter.publish(results);

        System.out.println("Test Results: " + results.getFailCount());
    }

    @Test
    void testUIFeatures() {

        Results results =
                Runner.path("classpath:features/ui")
                        .outputCucumberJson(true)
                        .parallel(4);

        assertEquals(0, results.getFailCount(),
                results.getErrorMessages());
    }

    @Karate.Test
    Karate testFakeStoreAPI() {
        return Karate.run("classpath:features/fakeStoreAPI.feature");
    }
    
    @Karate.Test
    Karate testFakeStoreCart() {
        return Karate.run("classpath:features/fakeStoreCart.feature");
    }

    @Karate.Test
    Karate testFakeStoreUsers() {
        return Karate.run("classpath:features/fakeStoreUsers.feature");
    }

    @Karate.Test
    Karate testFakeStoreAuth() {
        return Karate.run("classpath:features/fakeStoreAuthentication.feature");
    }

    @Karate.Test
    Karate testDatabase() {
        return Karate .run("classpath:features/api/database.feature");
    }

    @Test
    void testAllFeatures() {
        Results results = Runner.path("classpath:features")
                .parallel(5);
        System.out.println("Tests completed. Report generated at:");
        System.out.println("target/karate-reports/karate-summary.html");
        assertEquals(0, results.getFailCount(), "Tests failed: " + results.getFailCount());
    }
}
