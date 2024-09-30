package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.DeliveryDTO
import com.ecom.tech.ordermanagementservice.model.dto.OrderDTO
import com.ecom.tech.ordermanagementservice.model.dto.PaymentDTO
import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus
import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import com.ecom.tech.ordermanagementservice.service.OrderService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(OrderController::class)
class OrderControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var sampleOrderDto: OrderDTO

    @BeforeEach
    fun setUp() {
        sampleOrderDto = OrderDTO(
            orderUid = "sample-order-uid",
            trackNumber = "123456789",
            entry = "Sample Entry",
            delivery = DeliveryDTO("Test Delivery", "123", "Test City", "Test Address", "Region", "Zip", "Email"),
            payment = PaymentDTO(
                transaction = "TestTransaction",
                currency = "USD",
                provider = "TestProvider",
                amount = 500,
                paymentDt = 123456789L,
                bank = "TestBank",
                deliveryCost = 50,
                goodsTotal = 450,
                customFee = 0,
                status = PaymentStatus.NEW
            ),
            items = listOf(),
            deliveryService = "Sample Delivery Service",
            dateCreated = "2023-12-01",
            status = OrderStatus.NEW
        )
    }

    @Test
    fun `getAllOrders should return 200 OK and list of OrderDTOs`() {
        val orderList = listOf(sampleOrderDto)
        `when`(orderService.getAllOrders()).thenReturn(orderList)

        mockMvc.perform(get("/orders"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].orderUid").value("sample-order-uid"))
            .andExpect(jsonPath("$[0].trackNumber").value("123456789"))
            .andExpect(jsonPath("$[0].delivery.city").value("Test City"))
    }

    @Test
    fun `getOrderById should return 200 OK and OrderDTO`() {
        `when`(orderService.getOrderById("sample-order-uid")).thenReturn(sampleOrderDto)

        mockMvc.perform(get("/orders/sample-order-uid"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.orderUid").value("sample-order-uid"))
            .andExpect(jsonPath("$.trackNumber").value("123456789"))
            .andExpect(jsonPath("$.delivery.city").value("Test City"))
    }

    @Test
    fun `createOrder should return 200 OK and created OrderDTO`() {
        mockMvc.perform(
            post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleOrderDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.orderUid").value("sample-order-uid"))
    }

    @Test
    fun `updateOrder should return 200 OK and updated OrderDTO`() {
        val updatedOrderDto = sampleOrderDto.copy(trackNumber = "987654321")
        `when`(orderService.updateOrder("sample-order-uid", any())).thenReturn(updatedOrderDto)

        mockMvc.perform(
            put("/orders/sample-order-uid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleOrderDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.trackNumber").value("987654321"))
    }

    @Test
    fun `deleteOrder should return 204 No Content`() {
        doNothing().`when`(orderService).deleteOrder("sample-order-uid")

        mockMvc.perform(delete("/orders/sample-order-uid"))
            .andExpect(status().isNoContent)
    }
}