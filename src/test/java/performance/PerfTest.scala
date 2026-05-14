package performance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class PerfTest extends Simulation {

  val protocol = karateProtocol(
    "/products" -> Nil,
    "/users" -> Nil,
    "/carts" -> Nil
  )

  val getProducts = scenario("Get Products")
    .exec(karateFeature("classpath:features/fakeStoreAPI.feature@getProducts"))

  setUp(
    getProducts.inject(
      atOnceUsers(10),                               // 10 users all at once
      rampUsers(20).during(20.seconds)              // 20 users over 10 seconds
    )
  ).protocols(protocol)
   .assertions(
      global.responseTime.max.lt(3000),            // max response time < 3 seconds
      global.successfulRequests.percent.gt(95)     // 95%+ requests must pass
   )
}