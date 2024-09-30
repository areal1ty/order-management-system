package com.ecom.tech.ordermanagementservice.service

import com.ecom.tech.ordermanagementservice.exception.order.OrderModificationException
import com.ecom.tech.ordermanagementservice.model.dto.DeliveryDTO
import com.ecom.tech.ordermanagementservice.model.dto.OrderDTO
import com.ecom.tech.ordermanagementservice.model.dto.PaymentDTO
import com.ecom.tech.ordermanagementservice.model.entity.Delivery
import com.ecom.tech.ordermanagementservice.model.entity.Order
import com.ecom.tech.ordermanagementservice.model.entity.Payment
import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus
import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import com.ecom.tech.ordermanagementservice.repository.OrderRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*

class OrderServiceTest {
    @Mock
    private lateinit var orderRepository: OrderRepository

    @InjectMocks
    private lateinit var orderService: OrderService

    private lateinit var testOrder: Order
    private lateinit var testOrderDto: OrderDTO

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)

        val delivery = Delivery(
            id = 1L,
            name = "John Doe",
            phone = "123456789",
            city = "New York",
            address = "123 Street",
            region = "NY",
            zip = "10001",
            email = "john@example.com"
        )

        val payment = Payment(
            id = 1L,
            transaction = "123456",
            currency = "USD",
            provider = "PayPal",
            amount = 100,
            paymentDt = 1623677700,
            bank = "Bank of America",
            deliveryCost = 10,
            goodsTotal = 90,
            customFee = 5,
            status = PaymentStatus.NEW
        )

        testOrder = Order(
            orderUid = "order-001",
            trackNumber = "TN123456",
            entry = "ENTRY",
            delivery = delivery,
            payment = payment,
            items = emptyList(),
            deliveryService = "FedEx",
            dateCreated = "2024-09-30",
            status = OrderStatus.NEW
        )

        testOrderDto = OrderDTO(
            orderUid = "order-001",
            trackNumber = "TN123456",
            entry = "ENTRY",
            delivery = DeliveryDTO(
                name = "John Doe",
                phone = "123456789",
                city = "New York",
                address = "123 Street",
                region = "NY",
                zip = "10001",
                email = "john@example.com"
            ),
            payment = PaymentDTO(
                transaction = "123456",
                currency = "USD",
                provider = "PayPal",
                amount = 100,
                paymentDt = 1623677700,
                bank = "Bank of America",
                deliveryCost = 10,
                goodsTotal = 90,
                customFee = 5,
                status = PaymentStatus.NEW
            ),
            items = emptyList(),
            deliveryService = "FedEx",
            dateCreated = "2024-09-30",
            status = OrderStatus.NEW
        )
    }

    @Test
    fun `getAllOrders should return all orders`() {
        `when`(orderRepository.findAll()).thenReturn(listOf(testOrder))
        val orders = orderService.getAllOrders()
        assertEquals(1, orders.size)
        assertEquals("order-001", orders[0].orderUid)
    }

    @Test
    fun `getOrderById should return order by ID`() {
        `when`(orderRepository.findById("order-001")).thenReturn(Optional.of(testOrder))
        val order = orderService.getOrderById("order-001")
        assertEquals("order-001", order.orderUid)
    }

    @Test
    fun `getOrderById should throw exception for invalid ID`() {
        `when`(orderRepository.findById("invalid-id")).thenReturn(Optional.empty())
        assertThrows(IllegalArgumentException::class.java) {
            orderService.getOrderById("invalid-id")
        }
    }

    @Test
    fun `createOrder should save and return new order`() {
        `when`(orderRepository.save(any(Order::class.java))).thenReturn(testOrder)
        val createdOrder = orderService.createOrder(testOrderDto)
        assertEquals("order-001", createdOrder.orderUid)
    }

    @Test
    fun `updateOrder should update existing order`() {
        `when`(orderRepository.findById("order-001")).thenReturn(Optional.of(testOrder))
        `when`(orderRepository.save(any(Order::class.java))).thenReturn(testOrder)

        testOrderDto.trackNumber = "TN654321"
        val updatedOrder = orderService.updateOrder("order-001", testOrderDto)
        assertEquals("TN654321", updatedOrder.trackNumber)
    }

    @Test
    fun `deleteOrder should remove the order by ID`() {
        `when`(orderRepository.findById("order-001")).thenReturn(Optional.of(testOrder))
        orderService.deleteOrder("order-001")
        verify(orderRepository, times(1)).delete(testOrder)
    }

    @Test
    fun `validateOrderForItemDeletion should throw exception for non-active order`() {
        testOrder.status = OrderStatus.COMPLETED
        `when`(orderRepository.findById("order-001")).thenReturn(Optional.of(testOrder))
        assertThrows(OrderModificationException::class.java) {
            orderService.validateOrderForItemDeletion("order-001")
        }
    }
}