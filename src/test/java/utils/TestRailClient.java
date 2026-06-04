package utils;

import okhttp3.*;

import java.util.Base64;

public class TestRailClient {

    private static final OkHttpClient client = new OkHttpClient();

    private static String authHeader() {
        String auth = TestRailConfig.USERNAME + ":" + TestRailConfig.API_KEY;
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    }

    public static void addResult(int caseId, int statusId, String comment) throws Exception {

        String url = TestRailConfig.BASE_URL +
                "index.php?/api/v2/add_result_for_case/" +
                TestRailConfig.RUN_ID + "/" + caseId;

        String json = "{"
                + "\"status_id\":" + statusId + ","
                + "\"comment\":\"" + comment + "\""
                + "}";

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", authHeader())
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("TestRail Response: " + response.body().string());
        }
    }

}
