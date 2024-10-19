package main.kotlin.repository

import com.ecom.tech.ordermanagementservice.model.entity.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, Long>