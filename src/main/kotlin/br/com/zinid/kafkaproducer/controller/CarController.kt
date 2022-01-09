package br.com.zinid.kafkaproducer.controller

import br.com.zinid.kafkaproducer.producer.CarProducer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cars")
class CarController(val carProducer: CarProducer) {

    @PostMapping
    fun create(@RequestBody carRequest: CarDTO): ResponseEntity<Any> {
        val car = carRequest.toEntity()
        carProducer.send(car)
        return ResponseEntity.status(HttpStatus.CREATED).body(car)
    }

}