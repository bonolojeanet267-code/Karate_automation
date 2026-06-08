function fn() {
    var env = karate.env || 'tst';
    
    var config = {
        baseUrl: 'https://fakestoreapi.com',

        uiUrl: 'https://www.saucedemo.com/',
        parabankUrl: 'https://parabank.parasoft.com/parabank/index.htm',
        parabankUser: 'john',
        parabankPass: 'smith',

        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        parabankUrl: 'https://parabank.parasoft.com/parabank/index.htm',

        readTimeout: 30000,
        connectTimeout: 30000
    };

    if (env == 'dev') {
        config.baseUrl = 'https://fakestoreapi.com';
    } else if (env == 'staging') {
        config.baseUrl = 'https://staging.fakestoreapi.com';
    }
    
    karate.log('Running tests in environment:', env);
    karate.log('Base URL:', config.baseUrl);
    
    return config;
}