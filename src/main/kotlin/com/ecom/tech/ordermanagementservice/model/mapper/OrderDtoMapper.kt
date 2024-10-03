package com.ecom.tech.ordermanagementservice.model.mapper

import com.ecom.tech.ordermanagementservice.model.dto.OrderDTO
import com.ecom.tech.ordermanagementservice.model.entity.Order
import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus
import java.time.LocalDateTime

object OrderDtoMapper {
    fun toEntity(orderDto: OrderDTO): Order {
        return Order(
            orderId = orderDto.orderId,
            trackNumber = orderDto.trackNumber,
            entry = orderDto.entry,
            delivery = DeliveryDtoMapper.toEntity(orderDto.delivery),
            payment = PaymentDtoMapper.toEntity(orderDto.payment),
            items = orderDto.items.map { ItemDtoMapper.toEntity(it) },
            deliveryService = orderDto.deliveryService,
            dateCreated = LocalDateTime.parse(orderDto.dateCreated),
            status = OrderStatus.valueOf(orderDto.status)
        )
    }

    fun toDto(order: Order): OrderDTO {
        return OrderDTO(
            orderId = order.orderId,
            trackNumber = order.trackNumber,
            entry = order.entry,
            delivery = DeliveryDtoMapper.toDto(order.delivery),
            payment = PaymentDtoMapper.toDto(order.payment),
            items = order.items.map { ItemDtoMapper.toDto(it) },
            deliveryService = order.deliveryService,
            dateCreated = order.dateCreated.toString(),
            status = order.status.name
        )
    }
}