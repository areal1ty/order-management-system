package com.ecom.tech.ordermanagementservice.service

import com.ecom.tech.ordermanagementservice.exception.order.OrderModificationException
import com.ecom.tech.ordermanagementservice.model.dto.OrderDTO
import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus
import com.ecom.tech.ordermanagementservice.model.mapper.OrderDtoMapper
import com.ecom.tech.ordermanagementservice.repository.OrderRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(private val orderRepo: OrderRepository) {
    private val logger = KotlinLogging.logger {}

    fun getAllOrders(): List<OrderDTO> {
        logger.info { "Getting all Orders..." }
        return orderRepo.findAll().map { OrderDtoMapper.toDto(it) }
    }

    fun getOrderById(orderUid: String): OrderDTO {
        logger.info { "Getting Order with ID $orderUid" }
        val order = orderRepo.findById(orderUid)
            .orElseThrow { IllegalArgumentException("Order with ID $orderUid not found") }
        logger.info { "Got order with ID $orderUid, returning... " }
        return OrderDtoMapper.toDto(order)
    }

    fun createOrder(orderDto: OrderDTO): OrderDTO {
        logger.info { "Creating new order..." }
        val orderEntity = OrderDtoMapper.toEntity(orderDto)
        return OrderDtoMapper.toDto(orderRepo.save(orderEntity))
    }

    fun updateOrder(orderUid: String, orderDto: OrderDTO): OrderDTO {
        logger.info { "Looking for Order with ID $orderUid to update..."}
        val existingOrder = orderRepo.findById(orderUid)
            .orElseThrow { IllegalArgumentException("Order with ID $orderUid not found") }

        val updatedOrder = OrderDtoMapper.toEntity(orderDto)
        updatedOrder.orderUid = existingOrder.orderUid
        logger.info { "Updating Order with ID $orderUid..." }
        return OrderDtoMapper.toDto(orderRepo.save(updatedOrder))
    }

    fun deleteOrder(orderUid: String) {
        logger.info { "Looking for Order with ID $orderUid to delete..." }
        val existingOrder = orderRepo.findById(orderUid)
            .orElseThrow { IllegalArgumentException("Order with ID $orderUid not found") }

        logger.info { "Deleting Order with ID $orderUid..." }
        orderRepo.delete(existingOrder)
    }

    @Transactional(readOnly = true)
    fun canDeleteItemFromOrder(orderId: String): Boolean {
        val order = orderRepo.findById(orderId)
            .orElseThrow { IllegalArgumentException("Order with ID $orderId not found") }

        logger.info { "Checking status of Order with ID $orderId..." }
        return when (order.status) {
            OrderStatus.NEW, OrderStatus.PENDING -> true
            else -> false
        }
    }

    @Transactional
    fun validateOrderForItemDeletion(orderId: String) {
        if (!canDeleteItemFromOrder(orderId)) {
            throw OrderModificationException("Cannot delete item from Order with ID $orderId because the order is not in an active state.")
        }
    }
}