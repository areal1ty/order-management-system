package com.ecom.tech.ordermanagementservice.model.mapper

import com.ecom.tech.ordermanagementservice.model.dto.OrderDTO
import com.ecom.tech.ordermanagementservice.model.entity.Item
import com.ecom.tech.ordermanagementservice.model.entity.Order

object OrderDtoMapper {
    fun toEntity(orderDto: OrderDTO): Order {
        return Order(
            orderUid = orderDto.orderUid,
            trackNumber = orderDto.trackNumber,
            entry = orderDto.entry,
            delivery = DeliveryDtoMapper.toEntity(orderDto.delivery),
            payment = PaymentDtoMapper.toEntity(orderDto.payment),
            items = orderDto.items.map { itemDto ->
                Item(
                    id = itemDto.id,
                    chrtId = itemDto.chrtId,
                    trackNumber = itemDto.trackNumber,
                    price = itemDto.price,
                    rid = itemDto.rid,
                    name = itemDto.name,
                    sale = itemDto.sale,
                    size = itemDto.size,
                    totalPrice = itemDto.totalPrice,
                    nmId = itemDto.nmId,
                    brand = itemDto.brand,
                    order = null
                )
            },
            deliveryService = orderDto.deliveryService,
            dateCreated = orderDto.dateCreated,
            status = orderDto.status
        )
    }

    fun toDto(order: Order): OrderDTO {
        return OrderDTO(
            orderUid = order.orderUid,
            trackNumber = order.trackNumber,
            entry = order.entry,
            delivery = DeliveryDtoMapper.toDto(order.delivery),
            payment = PaymentDtoMapper.toDto(order.payment),
            items = order.items.map { ItemDtoMapper.toDto(it) },
            deliveryService = order.deliveryService,
            dateCreated = order.dateCreated,
            status = order.status
        )
    }
}