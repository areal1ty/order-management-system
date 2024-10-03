package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.DeliveryDTO
import com.ecom.tech.ordermanagementservice.service.DeliveryService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(DeliveryController::class)
class DeliveryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var deliveryService: DeliveryService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var sampleDeliveryDto: DeliveryDTO

    @BeforeEach
    fun setUp() {
        sampleDeliveryDto = DeliveryDTO(
            id = 1L,
            name = "John Doe",
            phone = "123-456-7890",
            zip = "12345",
            city = "New York",
            address = "123 Test St.",
            region = "NY",
            email = "john.doe@example.com"
        )
    }

    @Test
    fun `getAllDeliveries should return 200 OK and list of DeliveryDTOs`() {
        val deliveryList = listOf(sampleDeliveryDto)
        `when`(deliveryService.getAllDeliveries()).thenReturn(deliveryList)

        mockMvc.perform(get("/delivery"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("Test Delivery"))
            .andExpect(jsonPath("$[0].city").value("Test City"))
    }

    @Test
    fun `getDeliveryById should return 200 OK and DeliveryDTO`() {
        `when`(deliveryService.getDeliveryById(1L)).thenReturn(sampleDeliveryDto)

        mockMvc.perform(get("/delivery/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Test Delivery"))
            .andExpect(jsonPath("$.city").value("Test City"))
    }

    @Test
    fun `createDelivery should return 200 OK and created DeliveryDTO`() {
        `when`(deliveryService.createDelivery(any())).thenReturn(sampleDeliveryDto)

        mockMvc.perform(
            post("/delivery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDeliveryDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Test Delivery"))
            .andExpect(jsonPath("$.phone").value("1234567890"))
    }

    @Test
    fun `updateDelivery should return 200 OK and updated DeliveryDTO`() {
        val updatedDeliveryDto = sampleDeliveryDto.copy(city = "Updated City")
        `when`(deliveryService.updateDelivery(1L, any())).thenReturn(updatedDeliveryDto)

        mockMvc.perform(
            put("/delivery/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDeliveryDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.city").value("Updated City"))
    }
}