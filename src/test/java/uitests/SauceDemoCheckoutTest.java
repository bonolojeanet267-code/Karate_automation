package uitests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoCheckoutTest {

    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void launchBrowser() throws Exception {
        playwright = Playwright.create();
        
        String ltUsername = System.getenv("LT_USERNAME");
        String ltAccessKey = System.getenv("LT_ACCESS_KEY");
        
        // Check if running on LambdaTest (Jenkins will provide these)
        if (ltUsername != null && ltAccessKey != null && !ltUsername.isEmpty() && !ltAccessKey.isEmpty()) {
            System.out.println("Running on LambdaTest cloud!");
            System.out.println("Username: " + ltUsername);
            
            Map<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("user", ltUsername);
            ltOptions.put("accessKey", ltAccessKey);
            ltOptions.put("build", "Jenkins Build #" + System.getenv("BUILD_NUMBER"));
            ltOptions.put("name", "SauceDemo Checkout Test");
            ltOptions.put("platformName", "Windows 11");
            ltOptions.put("browserName", "Chromium");
            ltOptions.put("browserVersion", "latest");
            ltOptions.put("network", true);
            ltOptions.put("video", true);
            ltOptions.put("console", true);
            
            String caps = "{\"alwaysMatch\":" + new com.google.gson.Gson().toJson(ltOptions) + "}";
            String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + URLEncoder.encode(caps, StandardCharsets.UTF_8);
            
            browser = playwright.chromium().connect(cdpUrl);
        } else {
            // Run locally (for development)
            System.out.println("Running locally!");
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
    }

    @BeforeEach
    void createPage() {
        page = browser.newPage();
    }

    @AfterEach
    void closePage() {
        if (page != null) page.close();
    }

    @AfterAll
    static void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Test
    void testFullCheckout() {
        System.out.println("SAUCEDEMO E2E CHECKOUT TEST");

        System.out.println("STEP 1: Login to SauceDemo");
        page.navigate("https://www.saucedemo.com");
        page.fill("[data-test=\"username\"]", "standard_user");
        page.fill("[data-test=\"password\"]", "secret_sauce");
        page.click("[data-test=\"login-button\"]");
        assertTrue(page.url().contains("inventory"));
        System.out.println("Login successful\n");

        System.out.println("STEP 2: Add product to cart");
        page.click("[data-test=\"add-to-cart-sauce-labs-backpack\"]");
        System.out.println("Product added\n");

        System.out.println("STEP 3: View cart");
        page.click("[data-test=\"shopping-cart-link\"]");
        assertTrue(page.locator(".cart_item").textContent().contains("Sauce Labs Backpack"));
        System.out.println("Cart verified\n");

        System.out.println("STEP 4: Checkout");
        page.click("[data-test=\"checkout\"]");
        page.fill("[data-test=\"firstName\"]", "Bonolo");
        page.fill("[data-test=\"lastName\"]", "Tester");
        page.fill("[data-test=\"postalCode\"]", "2000");
        page.click("[data-test=\"continue\"]");
        page.click("[data-test=\"finish\"]");
        System.out.println("Checkout completed\n");

        String message = page.locator(".complete-header").textContent();
        assertTrue(message.contains("Thank you for your order!"));
        System.out.println("STEP 5: Verification");
        System.out.println("   [OK] " + message);
        
        // Set test status on LambdaTest
        String ltUsername = System.getenv("LT_USERNAME");
        if (ltUsername != null) {
            page.evaluate("_ => {}", "lambdatest_action: { \"action\": \"setTestStatus\", \"arguments\": { \"status\":\"passed\", \"remark\": \"Test completed successfully\" } }");
        }
        
        System.out.println("TEST PASSED!");
    }
}