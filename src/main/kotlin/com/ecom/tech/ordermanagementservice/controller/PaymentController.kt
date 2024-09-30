package com.ecom.tech.ordermanagementservice.controller

import com.ecom.tech.ordermanagementservice.model.dto.PaymentDTO
import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import com.ecom.tech.ordermanagementservice.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/public/payment")
class PaymentController(private val paymentService: PaymentService) {
    @PostMapping
    fun createPayment(@RequestBody paymentDto: PaymentDTO): ResponseEntity<PaymentDTO> =
        ResponseEntity.ok(paymentService.createPayment(paymentDto))

    @GetMapping("/{id}")
    fun getPaymentById(@PathVariable id: Long): ResponseEntity<PaymentDTO> =
        ResponseEntity.ok(paymentService.getPaymentById(id))

    @PutMapping("/{id}")
    fun updatePayment(@PathVariable id: Long, @RequestBody paymentDto: PaymentDTO): ResponseEntity<PaymentDTO> =
        ResponseEntity.ok(paymentService.updatePayment(id, paymentDto))

    @PutMapping("/{id}/status")
    fun updatePaymentStatus(@PathVariable id: Long, @RequestParam status: String): ResponseEntity<Void> {
        val paymentStatus = PaymentStatus.fromValue(status)
        paymentService.updatePaymentStatus(id, paymentStatus)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun deletePayment(@PathVariable id: Long): ResponseEntity<Void> {
        paymentService.deletePayment(id)
        return ResponseEntity.noContent().build()
    }
}