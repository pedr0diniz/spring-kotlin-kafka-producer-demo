package br.com.zinid.kafkaproducer

import br.com.zinid.kafkaproducer.controller.CarDTO

data class Car(
    val id: String,
    val model: String,
    val color: String
) {

    fun toDTO() : CarDTO {
        return CarDTO(
            model = model,
            color = color
        )
    }

}