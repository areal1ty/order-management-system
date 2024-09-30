package com.ecom.tech.ordermanagementservice.controller

import org.junit.platform.suite.api.IncludeClassNamePatterns
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(
    OrderControllerTest::class,
    DeliveryControllerTest::class,
    ItemControllerTest::class,
    PaymentControllerTest::class
)
@IncludeClassNamePatterns(".*Test")
class ControllerTestSuite {
}