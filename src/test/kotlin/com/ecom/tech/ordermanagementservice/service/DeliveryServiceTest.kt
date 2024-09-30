package com.ecom.tech.ordermanagementservice.service

import com.ecom.tech.ordermanagementservice.exception.delivery.DeliveryNotFoundException
import com.ecom.tech.ordermanagementservice.model.dto.DeliveryDTO
import com.ecom.tech.ordermanagementservice.model.entity.Delivery
import com.ecom.tech.ordermanagementservice.repository.DeliveryRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*

class DeliveryServiceTest {
    @Mock
    private lateinit var deliveryRepository: DeliveryRepository

    @InjectMocks
    private lateinit var deliveryService: DeliveryService

    private lateinit var testDelivery: Delivery
    private lateinit var testDeliveryDto: DeliveryDTO

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testDelivery = Delivery(
            id = 1L,
            name = "John Doe",
            phone = "123456789",
            city = "New York",
            address = "123 Street",
            region = "NY",
            zip = "10001",
            email = "john@example.com"
        )

        testDeliveryDto = DeliveryDTO(
            name = "John Doe",
            phone = "123456789",
            city = "New York",
            address = "123 Street",
            region = "NY",
            zip = "10001",
            email = "john@example.com"
        )
    }

    @Test
    fun `getAllDeliveries should return all deliveries`() {
        `when`(deliveryRepository.findAll()).thenReturn(listOf(testDelivery))
        val deliveries = deliveryService.getAllDeliveries()
        assertEquals(1, deliveries.size)
        assertEquals("John Doe", deliveries[0].name)
    }

    @Test
    fun `getDeliveryById should return a delivery by ID`() {
        `when`(deliveryRepository.findById(1L)).thenReturn(Optional.of(testDelivery))
        val delivery = deliveryService.getDeliveryById(1L)
        assertEquals("John Doe", delivery.name)
    }

    @Test
    fun `getDeliveryById should throw DeliveryNotFoundException for invalid ID`() {
        `when`(deliveryRepository.findById(2L)).thenReturn(Optional.empty())
        assertThrows<DeliveryNotFoundException> {
            deliveryService.getDeliveryById(12313)
        }
    }

    @Test
    fun `createDelivery should save and return the new delivery`() {
        `when`(deliveryRepository.save(any(Delivery::class.java))).thenReturn(testDelivery)
        val createdDelivery = deliveryService.createDelivery(testDeliveryDto)
        assertEquals("John Doe", createdDelivery.name)
    }

    @Test
    fun `updateDelivery should update existing delivery data`() {
        `when`(deliveryRepository.findById(1L)).thenReturn(Optional.of(testDelivery))
        `when`(deliveryRepository.save(testDelivery)).thenReturn(testDelivery)

        testDeliveryDto.city = "Los Angeles"
        val updatedDelivery = deliveryService.updateDelivery(1L, testDeliveryDto)
        assertEquals("Los Angeles", updatedDelivery.city)
    }
}