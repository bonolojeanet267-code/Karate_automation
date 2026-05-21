package uitests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoCheckoutTest {

    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
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


        // Login
        System.out.println("STEP 1: Login to SauceDemo");
        page.navigate("https://www.saucedemo.com");
        page.fill("[data-test=\"username\"]", "standard_user");
        page.fill("[data-test=\"password\"]", "secret_sauce");
        page.click("[data-test=\"login-button\"]");
        assertTrue(page.url().contains("inventory"));
        System.out.println("   [OK] Login successful\n");

        // Add to cart
        System.out.println("STEP 2: Add product to cart");
        page.click("[data-test=\"add-to-cart-sauce-labs-backpack\"]");
        System.out.println("   [OK] Product added\n");

        // Go to cart
        System.out.println("STEP 3: View cart");
        page.click("[data-test=\"shopping-cart-link\"]");
        assertTrue(page.locator(".cart_item").textContent().contains("Sauce Labs Backpack"));
        System.out.println("   [OK] Cart verified\n");

        // Checkout
        System.out.println("STEP 4: Checkout");
        page.click("[data-test=\"checkout\"]");
        page.fill("[data-test=\"firstName\"]", "Bonolo");
        page.fill("[data-test=\"lastName\"]", "Tester");
        page.fill("[data-test=\"postalCode\"]", "2000");
        page.click("[data-test=\"continue\"]");
        page.click("[data-test=\"finish\"]");
        System.out.println("   [OK] Checkout completed\n");

        // Verify
        String message = page.locator(".complete-header").textContent();
        assertTrue(message.contains("Thank you for your order!"));
        System.out.println("STEP 5: Verification");
        System.out.println("   [OK] " + message);

        
        System.out.println("TEST PASSED!");
        
    }
}

