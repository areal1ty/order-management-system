package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.PaymentDTO
import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import com.ecom.tech.ordermanagementservice.service.PaymentService
import com.fasterxml.jackson.databind.ObjectMapper
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(PaymentController::class)
class PaymentControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var paymentService: PaymentService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var samplePaymentDto: PaymentDTO

    @BeforeEach
    fun setUp() {
        samplePaymentDto = PaymentDTO(
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
        )
    }

    @Test
    fun `createPayment should return 200 OK and PaymentDTO`() {
        mockMvc.perform(
            post("/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(samplePaymentDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.transaction").value("TestTransaction"))
            .andExpect(jsonPath("$.currency").value("USD"))
            .andExpect(jsonPath("$.amount").value(500))
    }

    @Test
    fun `getPaymentById should return 200 OK and PaymentDTO`() {
        `when`(paymentService.getPaymentById(1L)).thenReturn(samplePaymentDto)

        mockMvc.perform(get("/payment/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.transaction").value("TestTransaction"))
            .andExpect(jsonPath("$.currency").value("USD"))
            .andExpect(jsonPath("$.amount").value(500))
    }

    @Test
    fun `updatePayment should return 200 OK and updated PaymentDTO`() {
        val updatedPaymentDto = samplePaymentDto.copy(amount = 700)
        `when`(paymentService.updatePayment(1L, samplePaymentDto)).thenReturn(updatedPaymentDto)

        mockMvc.perform(
            put("/payment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(samplePaymentDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.amount").value(700))
    }
/*
    @Test
    fun `updatePaymentStatus should return 204 No Content`() {
        val paymentStatus = PaymentStatus.PAID
        doNothing().`when`(paymentService).updatePaymentStatus(1L, paymentStatus)

        mockMvc.perform(
            put("/payment/1/status")
                .param("status", paymentStatus.value)
        )
            .andExpect(status().isNoContent)
    }

 */

    @Test
    fun `deletePayment should return 204 No Content`() {
        doNothing().`when`(paymentService).deletePayment(1L)

        mockMvc.perform(delete("/payment/1"))
            .andExpect(status().isNoContent)
    }
}