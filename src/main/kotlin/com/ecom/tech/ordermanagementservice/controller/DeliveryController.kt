package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.DeliveryDTO
import com.ecom.tech.ordermanagementservice.service.DeliveryService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/delivery")
class DeliveryController(private val deliveryService: DeliveryService) {
    @GetMapping
    fun getAllDeliveries(): ResponseEntity<List<DeliveryDTO>> = ResponseEntity.ok(deliveryService.getAllDeliveries())

    @GetMapping("/{id}")
    fun getDeliveryById(@PathVariable id: Long): ResponseEntity<DeliveryDTO> =
        ResponseEntity.ok(deliveryService.getDeliveryById(id))

    @PostMapping
    fun createDelivery(@RequestBody deliveryDto: DeliveryDTO): ResponseEntity<DeliveryDTO> =
        ResponseEntity.ok(deliveryService.createDelivery(deliveryDto))

    @PutMapping("/{id}")
    fun updateDelivery(@PathVariable id: Long, @RequestBody deliveryDto: DeliveryDTO): ResponseEntity<DeliveryDTO> =
        ResponseEntity.ok(deliveryService.updateDelivery(id, deliveryDto))
}