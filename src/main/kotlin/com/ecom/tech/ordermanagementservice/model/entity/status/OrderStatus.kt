package com.ecom.tech.ordermanagementservice.model.entity.status

import com.fasterxml.jackson.annotation.JsonValue

enum class OrderStatus(@JsonValue val value: String) {
    NEW("NEW"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");
}