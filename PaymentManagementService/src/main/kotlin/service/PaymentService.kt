package com.ecom.tech.ordermanagementservice.service

import com.ecom.tech.ordermanagementservice.exception.payment.CannotDeletePaymentException
import com.ecom.tech.ordermanagementservice.exception.payment.PaymentNotFoundException
import com.ecom.tech.ordermanagementservice.model.dto.PaymentDTO
import com.ecom.tech.ordermanagementservice.model.entity.Payment
import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import com.ecom.tech.ordermanagementservice.model.mapper.PaymentDtoMapper
import com.ecom.tech.ordermanagementservice.repository.PaymentRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class PaymentService(private val paymentRepo: PaymentRepository) {
    private val logger = KotlinLogging.logger {}

    fun createPayment(paymentDto: PaymentDTO): PaymentDTO {
        val paymentEntity = PaymentDtoMapper.toEntity(paymentDto)
        logger.info { "Creating new Payment..." }
        updatePaymentStatus(paymentEntity.id, PaymentStatus.NEW)
        logger.info { "Payment status has been updated to NEW" }
        return PaymentDtoMapper.toDto(paymentRepo.save(paymentEntity))
    }

    fun getPaymentById(id: Long): PaymentDTO {
        logger.info { "Looking for Payment with ID $id..." }
        val payment = paymentRepo.findById(id)
            .orElseThrow { PaymentNotFoundException("Payment with ID $id not found") }
        return PaymentDtoMapper.toDto(payment)
    }

    fun updatePayment(id: Long, paymentDto: PaymentDTO): PaymentDTO {
        logger.info { "Looking for Payment with ID $id to update..." }
        val existingPayment = paymentRepo.findById(id)
            .orElseThrow { PaymentNotFoundException("Payment with ID $id not found") }

        existingPayment.amount = paymentDto.amount
        existingPayment.currency = paymentDto.currency
        existingPayment.bank = paymentDto.bank
        existingPayment.provider = paymentDto.provider

        logger.info { "Payment with ID $id is being updated..." }
        return PaymentDtoMapper.toDto(paymentRepo.save(existingPayment))
    }

    fun deletePayment(id: Long) {
        logger.info { "Looking for Payment with ID $id to delete..." }
        val payment = paymentRepo.findById(id)
            .orElseThrow { PaymentNotFoundException("Payment with ID $id not found") }

        logger.info { "Checking if Payment with ID $id can be deleted..." }
        if (payment.status !in listOf(PaymentStatus.NEW, PaymentStatus.CANCELLED)) {
            throw CannotDeletePaymentException("Payment with ID $id cannot be deleted because it is not in a deletable state.")
        }

        logger.info { "Payment with ID $id is being deleted..." }
        paymentRepo.deleteById(id)
    }

    fun updatePaymentStatus(paymentId: Long, newStatus: PaymentStatus): Payment {
        val payment = paymentRepo.findById(paymentId)
            .orElseThrow { PaymentNotFoundException("Payment with ID $paymentId not found") }


        payment.status = when (newStatus) {
            PaymentStatus.COMPLETED -> {
                PaymentStatus.COMPLETED
            }

            PaymentStatus.FAILED -> {
                PaymentStatus.FAILED
            }

            PaymentStatus.CANCELLED -> {
                PaymentStatus.CANCELLED
            }

            else -> newStatus
        }
        return paymentRepo.save(payment)
    }

    private fun isValidStatusTransition(currentStatus: PaymentStatus, newStatus: PaymentStatus): Boolean {
        return when (currentStatus) {
            PaymentStatus.NEW -> newStatus in listOf(PaymentStatus.PENDING, PaymentStatus.CANCELLED)
            PaymentStatus.PENDING -> newStatus in listOf(
                PaymentStatus.COMPLETED,
                PaymentStatus.FAILED,
                PaymentStatus.CANCELLED
            )

            PaymentStatus.COMPLETED, PaymentStatus.FAILED -> false
            PaymentStatus.CANCELLED -> false
        }
    }
}