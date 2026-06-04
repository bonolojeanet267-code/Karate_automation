package utils;

import com.intuit.karate.Runner;
import com.intuit.karate.Results;
import java.util.regex.*;

public class TestRailReporter {

    public static void publish(Results results) {

        results.getScenarioResults().forEach(sr -> {

            // Fix: Check if tags are null
            var tagsObj = sr.getScenario().getTags();
            if (tagsObj == null) {
                return; // Skip scenarios without tags
            }

            String tags = tagsObj.toString();

            // Updated pattern: Extract case ID from @C46, @C47 etc (handles optional 'C')
            Pattern pattern = Pattern.compile("@C?(\\d+)");  // The '?' makes 'C' optional
            Matcher matcher = pattern.matcher(tags);

            if (matcher.find()) {

                int caseId = Integer.parseInt(matcher.group(1));

                int statusId = sr.isFailed() ? 5 : 1; // 5 = Failed, 1 = Passed

                String comment = sr.getScenario().getName();

                try {
                    TestRailClient.addResult(caseId, statusId, comment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}