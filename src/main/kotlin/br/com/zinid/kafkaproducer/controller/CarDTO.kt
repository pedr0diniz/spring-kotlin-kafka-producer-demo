package br.com.zinid.kafkaproducer.controller

import br.com.zinid.kafkaproducer.Car
import java.util.*

data class CarDTO(
    val model: String,
    val color: String
) {

    fun toEntity() : Car {
        return Car(
            id = UUID.randomUUID().toString(),
            model = model,
            color = color
        )
    }

}