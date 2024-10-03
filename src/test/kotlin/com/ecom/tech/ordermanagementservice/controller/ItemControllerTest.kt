package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.ItemDTO
import com.ecom.tech.ordermanagementservice.model.dto.OrderDTO
import com.ecom.tech.ordermanagementservice.service.ItemService
import com.ecom.tech.ordermanagementservice.service.OrderService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ItemController::class)
class ItemControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var itemService: ItemService

    @MockBean
    private lateinit var itemController: ItemController

    @MockBean
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var sampleItemDto: ItemDTO
    private lateinit var sampleOrderDto: OrderDTO

    @BeforeEach
    fun setUp() {
        sampleItemDto = ItemDTO(
            id = 1L,
            chrtId = 123,
            trackNumber = "TRK12345678",
            price = 100,
            rid = "RID12345",
            name = "Test Item",
            sale = 10,
            size = "M",
            totalPrice = 90,
            nmId = 456,
            brand = "Test Brand",
            status = "NEW",
            orderId = 1L
        )

        itemController.createItem(sampleItemDto)
    }

    @Test
    fun `getAllItems should return 200 OK and list of ItemDTOs`() {
        val itemList = listOf(sampleItemDto)
        `when`(itemService.getAllItems()).thenReturn(itemList)

        mockMvc.perform(get("/items"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Sample Item"))
            .andExpect(jsonPath("$[0].price").value(200))
    }

    @Test
    fun `getItemById should return 200 OK and ItemDTO`() {
        `when`(itemService.getItemById(1L)).thenReturn(sampleItemDto)

        mockMvc.perform(get("/items/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Sample Item"))
            .andExpect(jsonPath("$.price").value(200))
    }

    @Test
    fun `createItem should return 200 OK and created ItemDTO`() {
        `when`(orderService.getOrderById("sample-order-uid")).thenReturn(sampleOrderDto)
        `when`(itemService.createItem(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(sampleItemDto)

        mockMvc.perform(
            post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleItemDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Sample Item"))
    }

    @Test
    fun `updateItem should return 200 OK and updated ItemDTO`() {
        val updatedItemDto = sampleItemDto.copy(name = "Updated Item")
        `when`(itemService.updateItem(1L, ArgumentMatchers.any())).thenReturn(updatedItemDto)

        mockMvc.perform(
            put("/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleItemDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Updated Item"))
    }

    @Test
    fun `deleteItem should return 204 No Content`() {
        doNothing().`when`(itemService).deleteItem(1L)

        mockMvc.perform(delete("/items/1"))
            .andExpect(status().isNoContent)
    }
}