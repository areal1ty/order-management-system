package com.ecom.tech.ordermanagementservice.service

import org.junit.platform.suite.api.IncludeClassNamePatterns
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(
    DeliveryServiceTest::class,
    ItemServiceTest::class,
    PaymentServiceTest::class
)
@IncludeClassNamePatterns(".*Test")
class ServiceTestSuite