package com.ecom.tech.ordermanagementservice.exception

import com.ecom.tech.ordermanagementservice.exception.delivery.DeliveryNotFoundException
import com.ecom.tech.ordermanagementservice.exception.item.ItemNotFoundException
import com.ecom.tech.ordermanagementservice.exception.order.OrderNotFoundException
import com.ecom.tech.ordermanagementservice.exception.payment.CannotDeletePaymentException
import com.ecom.tech.ordermanagementservice.exception.payment.PaymentNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFoundException(ex: OrderNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(PaymentNotFoundException::class)
    fun handlePaymentNotFoundException(ex: PaymentNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(DeliveryNotFoundException::class)
    fun handleDeliveryNotFoundException(ex: DeliveryNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(ItemNotFoundException::class)
    fun handleItemNotFoundException(ex: DeliveryNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(CannotDeletePaymentException::class)
    fun handleCannotDeletePaymentException(ex: CannotDeletePaymentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }
}