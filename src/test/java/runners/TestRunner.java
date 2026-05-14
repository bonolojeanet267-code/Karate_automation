package runners;

import com.intuit.karate.junit5.Karate;

class TestRunner {
    
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
    Karate testAllFeatures() {
        return Karate.run("classpath:features");
    }
}