package com.ecom.tech.ordermanagementservice.service

import com.ecom.tech.ordermanagementservice.exception.delivery.DeliveryNotFoundException
import com.ecom.tech.ordermanagementservice.model.dto.DeliveryDTO
import com.ecom.tech.ordermanagementservice.model.mapper.DeliveryDtoMapper
import com.ecom.tech.ordermanagementservice.repository.DeliveryRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class DeliveryService(private val deliveryRepo: DeliveryRepository) {
    private val logger = KotlinLogging.logger {}
    fun getAllDeliveries(): List<DeliveryDTO> {
        logger.info {"Getting all delivery data"}
        return deliveryRepo.findAll().map { DeliveryDtoMapper.toDto(it) }
    }

    fun getDeliveryById(id: Long): DeliveryDTO {
        val delivery = deliveryRepo.findById(id)
            .orElseThrow { DeliveryNotFoundException("Delivery with ID $id not found") }
        logger.info { "Got a delivery with ID $id, returning..." }
        return DeliveryDtoMapper.toDto(delivery)
    }

    fun createDelivery(deliveryDto: DeliveryDTO): DeliveryDTO {
        val deliveryEntity = DeliveryDtoMapper.toEntity(deliveryDto)
        logger.info { "Saving new delivery..." }
        return DeliveryDtoMapper.toDto(deliveryRepo.save(deliveryEntity))
    }

    fun updateDelivery(id: Long, deliveryDto: DeliveryDTO): DeliveryDTO {
        val existingDelivery = deliveryRepo.findById(id)
            .orElseThrow { DeliveryNotFoundException("Delivery with ID $id not found") }

        logger.info { "Updating delivery data..." }
        existingDelivery.name = deliveryDto.name
        existingDelivery.phone = deliveryDto.phone
        existingDelivery.city = deliveryDto.city
        existingDelivery.address = deliveryDto.address
        existingDelivery.region = deliveryDto.region
        existingDelivery.zip = deliveryDto.zip
        existingDelivery.email = deliveryDto.email

        logger.info { "Delivery data has been updated" }
        return DeliveryDtoMapper.toDto(deliveryRepo.save(existingDelivery))
    }
}