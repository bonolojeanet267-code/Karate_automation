function fn() {
    var env = karate.env || 'tst';
    
    var config = {
        baseUrl: 'https://fakestoreapi.com',

        uiUrl: 'https://www.saucedemo.com/',

        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },

        readTimeout: 30000,
        connectTimeout: 30000
    };

    var lambdaTestUsername = karate.properties['LT_USERNAME'] || 'bonolojeanet267';
    var lambdaTestAccessKey = karate.properties['LT_ACCESS_KEY'] || 'LT_ACCESS_KEY';
    var lambdaTestUrl = 'https://' + lambdaTestUsername + ':' + lambdaTestAccessKey + '@hub.lambdatest.com/wd/hub';

     var lambdaTestOptions = {
    build: 'Karate E2E Tests',
    name: 'Test Run',
    network: true,
    video: true,
    visual: true,
    console: true
  };

    var session = {
    capabilities: {
      alwaysMatch: {
        browserName: 'chrome',
        browserVersion: 'latest',
        platformName: 'Windows 11',
        'LT:Options': lambdaTestOptions
      }
    }
  };

config.lambdaTestSession = session;
  config.lambdaTestUrl = lambdaTestUrl;
  
  return config;

    
    if (env == 'dev') {
        config.baseUrl = 'https://fakestoreapi.com';
    } else if (env == 'staging') {
        config.baseUrl = 'https://staging.fakestoreapi.com';
    }
    
    karate.log('Running tests in environment:', env);
    karate.log('Base URL:', config.baseUrl);
    
    return config;
}