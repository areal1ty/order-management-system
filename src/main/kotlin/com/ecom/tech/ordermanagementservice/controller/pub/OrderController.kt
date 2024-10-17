package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.OrderDTO
import com.ecom.tech.ordermanagementservice.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/public/order")
class OrderController(private val orderService: OrderService) {
    @GetMapping
    fun getAllOrders(): ResponseEntity<List<OrderDTO>> = ResponseEntity.ok(orderService.getAllOrders())

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: String): ResponseEntity<OrderDTO> =
        ResponseEntity.ok(orderService.getOrderById(id))

    @PostMapping
    fun createOrder(@RequestBody orderDTO: OrderDTO): ResponseEntity<OrderDTO> =
        ResponseEntity.ok(orderService.createOrder(orderDTO))

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: String, @RequestBody orderDTO: OrderDTO): ResponseEntity<OrderDTO> =
        ResponseEntity.ok(orderService.updateOrder(id, orderDTO))

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: String): ResponseEntity<Void> {
        orderService.deleteOrder(id)
        return ResponseEntity.noContent().build()
    }
}