package br.com.zinid.kafkaproducer.producer

import br.com.zinid.kafkaproducer.Car
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class CarProducer(
    @Value("\${topic.name}") val topic: String,
    val kafkaTemplate: KafkaTemplate<String, Car>
) {

    val logger = LoggerFactory.getLogger(this.javaClass)

    fun send(car: Car) {
        kafkaTemplate.send(topic, car).addCallback(
            {
                logger.info("Message sent") // success case
            },
            {
                logger.info("Message failed") // failure case
            }
        )
    }
}
