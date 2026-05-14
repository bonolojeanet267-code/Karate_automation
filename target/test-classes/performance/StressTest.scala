package performance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class StressTest extends Simulation {

  val protocol = karateProtocol(
    "/products" -> Nil,
    "/users" -> Nil,
    "/carts" -> Nil
  )

  val getProducts = scenario("Stress Test - Get Products")
    .exec(karateFeature("classpath:features/fakeStoreAPI.feature@getProducts"))

  setUp(
    getProducts.inject(
      // Start normal
      atOnceUsers(5),                         // 5 users immediately
      rampUsers(20).during(10.seconds),        // ramp to 20 users
      // Push harder
      rampUsers(50).during(20.seconds),        // ramp to 50 users
      // Peak stress
      rampUsers(100).during(30.seconds)        // ramp to 100 users
    )
  ).protocols(protocol)
   .assertions(
      global.responseTime.max.lt(5000),        // max 5 seconds
      global.responseTime.mean.lt(2000),       // average under 2 seconds
      global.successfulRequests.percent.gt(90) // 90%+ must pass
   )
}