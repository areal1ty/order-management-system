package com.ecom.tech.ordermanagementservice.model.mapper

import com.ecom.tech.ordermanagementservice.model.dto.ItemDTO
import com.ecom.tech.ordermanagementservice.model.entity.Item
import com.ecom.tech.ordermanagementservice.model.entity.Order

object ItemDtoMapper {
    fun toEntity(itemDto: ItemDTO, order: Order): Item {
        return Item(
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
            order = order
        )
    }
    fun toDto(item: Item): ItemDTO {
        return ItemDTO(
            id = item.id,
            chrtId = item.chrtId,
            trackNumber = item.trackNumber,
            price = item.price,
            rid = item.rid,
            name = item.name,
            sale = item.sale,
            size = item.size,
            totalPrice = item.totalPrice,
            nmId = item.nmId,
            brand = item.brand,
            orderUid = item.order!!.orderUid
        )
    }
}
