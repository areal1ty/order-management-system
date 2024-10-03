package com.ecom.tech.ordermanagementservice.model.mapper

import com.ecom.tech.ordermanagementservice.model.dto.DeliveryDTO
import com.ecom.tech.ordermanagementservice.model.entity.Delivery

object DeliveryDtoMapper {
    fun toEntity(deliveryDto: DeliveryDTO): Delivery {
        return Delivery(
            name = deliveryDto.name,
            phone = deliveryDto.phone,
            zip = deliveryDto.zip,
            city = deliveryDto.city,
            address = deliveryDto.address,
            region = deliveryDto.region,
            email = deliveryDto.email,
            order = deliveryDto.order
        )
    }

    fun toDto(delivery: Delivery): DeliveryDTO {
        return DeliveryDTO(
            name = delivery.name,
            phone = delivery.phone,
            zip = delivery.zip,
            city = delivery.city,
            address = delivery.address,
            region = delivery.region,
            email = delivery.email
            order = delivery.order
        )
    }

}