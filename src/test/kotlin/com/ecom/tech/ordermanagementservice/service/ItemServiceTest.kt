package com.ecom.tech.ordermanagementservice.service

import com.ecom.tech.ordermanagementservice.exception.item.ItemNotFoundException
import com.ecom.tech.ordermanagementservice.model.dto.ItemDTO
import com.ecom.tech.ordermanagementservice.model.entity.Delivery
import com.ecom.tech.ordermanagementservice.model.entity.Item
import com.ecom.tech.ordermanagementservice.model.entity.Order
import com.ecom.tech.ordermanagementservice.model.entity.Payment
import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus
import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import com.ecom.tech.ordermanagementservice.repository.ItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

class ItemServiceTest {
    @Mock
    private lateinit var itemRepo: ItemRepository

    @Mock
    private lateinit var orderService: OrderService

    @InjectMocks
    private lateinit var itemService: ItemService

    private lateinit var mockMvc: MockMvc

    private lateinit var sampleOrder: Order
    private lateinit var sampleItem: Item
    private lateinit var sampleItemDTO: ItemDTO

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(itemService).build()

        sampleOrder = Order(
            orderUid = "order123",
            trackNumber = "TN123",
            entry = "entry1",
            delivery = mockDelivery(),
            payment = mockPayment(),
            items = emptyList(),
            deliveryService = "DHL",
            dateCreated = "2024-09-30",
            status = OrderStatus.NEW
        )

        sampleItem = Item(
            id = 1,
            chrtId = 101,
            trackNumber = "TN123",
            price = 100,
            rid = "RID001",
            name = "Sample Item",
            sale = 10,
            size = "L",
            totalPrice = 90,
            nmId = 123,
            brand = "Brand A",
            order = sampleOrder
        )

        sampleItemDTO = ItemDTO(
            id = 1,
            chrtId = 101,
            trackNumber = "TN123",
            price = 100,
            rid = "RID001",
            name = "Sample Item",
            sale = 10,
            size = "L",
            totalPrice = 90,
            nmId = 123,
            brand = "Brand A",
            orderUid = sampleOrder.orderUid
        )
    }

    @Test
    fun `getAllItems should return all items`() {
        val items = listOf(sampleItem)
        `when`(itemRepo.findAll()).thenReturn(items)

        val result = itemService.getAllItems()

        assertEquals(1, result.size)
        assertEquals("Sample Item", result[0].name)
        verify(itemRepo, Mockito.times(1)).findAll()
    }

    @Test
    fun `getItemById should return item when found`() {
        `when`(itemRepo.findById(1L)).thenReturn(Optional.of(sampleItem))

        val result = itemService.getItemById(1)

        assertEquals("Sample Item", result.name)
        verify(itemRepo, Mockito.times(1)).findById(1L)
    }

    @Test
    fun `getItemById should throw ItemNotFoundException when item is not found`() {
        `when`(itemRepo.findById(999L)).thenReturn(Optional.empty())

        assertEquals(ItemNotFoundException::class, itemService.getItemById(999))
        verify(itemRepo, Mockito.times(1)).findById(999L)
    }

    @Test
    fun `createItem should save new item and return DTO`() {
        `when`(itemRepo.save(Mockito.any(Item::class.java))).thenReturn(sampleItem)
        val result = itemService.createItem(sampleItemDTO, sampleOrder)

        assertEquals("Sample Item", result.name)
        verify(itemRepo, Mockito.times(1)).save(Mockito.any(Item::class.java))
    }

    @Test
    fun `updateItem should update and return updated DTO`() {
        val updatedItem = sampleItem.copy(name = "Updated Name", price = 150)
        `when`(itemRepo.findById(1L)).thenReturn(Optional.of(sampleItem))
        `when`(itemRepo.save(sampleItem)).thenReturn(updatedItem)

        val result = itemService.updateItem(1L, sampleItemDTO.copy(name = "Updated Name", price = 150))

        assertEquals("Updated Name", result.name)
        assertEquals(150, result.price)
        verify(itemRepo, Mockito.times(1)).findById(1L)
        verify(itemRepo, Mockito.times(1)).save(sampleItem)
    }

    @Test
    fun `deleteItem should delete item if order is validated`() {
        `when`(itemRepo.findById(1L)).thenReturn(Optional.of(sampleItem))
        Mockito.doNothing().`when`(orderService).validateOrderForItemDeletion("order123")
        Mockito.doNothing().`when`(itemRepo).deleteById(1L)

        itemService.deleteItem(1L)

        verify(itemRepo, Mockito.times(1)).findById(1L)
        verify(orderService, Mockito.times(1)).validateOrderForItemDeletion("order123")
        verify(itemRepo, Mockito.times(1)).deleteById(1L)
    }

    private fun mockDelivery() = Delivery(
        id = 1,
        name = "Test Delivery",
        phone = "1234567890",
        city = "Test City",
        address = "Test Address",
        region = "Test Region",
        zip = "12345",
        email = "test@example.com"
    )

    private fun mockPayment() = Payment(
        id = 1,
        transaction = "TestTransaction",
        currency = "USD",
        provider = "TestBank",
        amount = 1000,
        paymentDt = 1234567890L,
        bank = "TestBank",
        deliveryCost = 50,
        goodsTotal = 950,
        customFee = 0,
        status = PaymentStatus.NEW
    )
}
