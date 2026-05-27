import { test, expect } from '@playwright/test';

test('SauceDemo full checkout flow', async ({ page }) => {

  console.log('SAUCEDEMO E2E CHECKOUT TEST');

  
  console.log('STEP 1: Login to SauceDemo');

  await page.goto('https://www.saucedemo.com');

  await page.fill('[data-test="username"]', 'standard_user');
  await page.fill('[data-test="password"]', 'secret_sauce');
  await page.click('[data-test="login-button"]');

  await expect(page).toHaveURL(/inventory/);

  console.log('Login successful\n');


  console.log('STEP 2: Add product to cart');

  await page.click('[data-test="add-to-cart-sauce-labs-backpack"]');

  console.log('Product added\n');

  console.log('STEP 3: View cart');

  await page.click('[data-test="shopping-cart-link"]');

  const cartItem = await page.locator('.cart_item').textContent();
  expect(cartItem).toContain('Sauce Labs Backpack');

  console.log('Cart verified\n');


  console.log('STEP 4: Checkout');

  await page.click('[data-test="checkout"]');

  await page.fill('[data-test="firstName"]', 'Bonolo');
  await page.fill('[data-test="lastName"]', 'Tester');
  await page.fill('[data-test="postalCode"]', '2000');

  await page.click('[data-test="continue"]');
  await page.click('[data-test="finish"]');

  console.log('Checkout completed\n');


  const message = await page.locator('.complete-header').textContent();

  expect(message).toContain('Thank you for your order!');

  console.log('STEP 5: Verification');
  console.log('   [OK] ' + message);

  console.log('TEST PASSED!');
});