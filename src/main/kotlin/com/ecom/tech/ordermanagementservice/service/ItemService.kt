package com.ecom.tech.ordermanagementservice.service

import com.ecom.tech.ordermanagementservice.exception.item.ItemNotFoundException
import com.ecom.tech.ordermanagementservice.model.dto.ItemDTO
import com.ecom.tech.ordermanagementservice.model.entity.Order
import com.ecom.tech.ordermanagementservice.model.mapper.ItemDtoMapper
import com.ecom.tech.ordermanagementservice.repository.ItemRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(private val itemRepo: ItemRepository, private val orderService: OrderService) {

    private val logger = KotlinLogging.logger {}
    fun getAllItems(): List<ItemDTO> {
        logger.info { "Getting all items..." }
        return itemRepo.findAll().map { ItemDtoMapper.toDto(it) }
    }

    fun getItemById(id: Long): ItemDTO {
        logger.info { "Looking for Item with ID $id" }
        val item = itemRepo.findById(id)
            .orElseThrow { ItemNotFoundException("Item with ID $id not found") }
        logger.info { "Got an Item with ID $id" }
        return ItemDtoMapper.toDto(item)
    }

    fun createItem(itemDto: ItemDTO, order: Order): ItemDTO {
        val itemEntity = ItemDtoMapper.toEntity(itemDto, order)
        logger.info { "Saving new Item..." }
        return ItemDtoMapper.toDto(itemRepo.save(itemEntity))
    }

    fun updateItem(id: Long, itemDto: ItemDTO): ItemDTO {
        logger.info { "Looking for Item with ID $id to update" }
        val existingItem = itemRepo.findById(id)
            .orElseThrow { ItemNotFoundException("Item with ID $id not found") }

        existingItem.name = itemDto.name
        existingItem.price = itemDto.price
        existingItem.brand = itemDto.brand

        logger.info { "Item with ID $id is being updated..." }
        return ItemDtoMapper.toDto(itemRepo.save(existingItem))
    }

    @Transactional
    fun deleteItem(id: Long) {
        logger.info { "Looking for  Item with ID $id to delete" }
        val item = itemRepo.findById(id)
            .orElseThrow { IllegalArgumentException("Item with ID $id not found") }

        val order = item.order
        logger.info { "Validating if Item can be deleted from Order..." }
        orderService.validateOrderForItemDeletion(order!!.orderUid)

        logger.info { "Order validated, deleting Item from Order..." }
        itemRepo.deleteById(id)
    }
}