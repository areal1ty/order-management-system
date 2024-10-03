package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.ItemDTO
import com.ecom.tech.ordermanagementservice.model.mapper.OrderDtoMapper
import com.ecom.tech.ordermanagementservice.service.ItemService
import com.ecom.tech.ordermanagementservice.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/payment/item")
class ItemController(private val itemService: ItemService,
                     private val orderService: OrderService) {
    @GetMapping
    fun getAllItems(): ResponseEntity<List<ItemDTO>> = ResponseEntity.ok(itemService.getAllItems())

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Long): ResponseEntity<ItemDTO> =
        ResponseEntity.ok(itemService.getItemById(id))

    @PostMapping
    fun createItem(@RequestBody itemDto: ItemDTO): ResponseEntity<ItemDTO> {
        val orderDto = orderService.getOrderById(itemDto.orderId!!)
        return ResponseEntity.ok(itemService.createItem(itemDto, OrderDtoMapper.toEntity(orderDto)))
    }

    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Long, @RequestBody itemDto: ItemDTO): ResponseEntity<ItemDTO> =
        ResponseEntity.ok(itemService.updateItem(id, itemDto))

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long): ResponseEntity<Void> {
        itemService.deleteItem(id)
        return ResponseEntity.noContent().build()
    }
}