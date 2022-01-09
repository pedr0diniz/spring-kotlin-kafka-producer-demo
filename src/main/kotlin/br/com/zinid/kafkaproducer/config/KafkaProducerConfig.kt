package br.com.zinid.kafkaproducer.config

import br.com.zinid.kafkaproducer.Car
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapAddress: String

    @Value("\${topic.name}")
    lateinit var topic: String

    @Bean
    fun createTopic(): NewTopic {
        return NewTopic(topic, 3, 1)
    }

    @Bean
    fun carProducerFactory(): ProducerFactory<String, Car> {
        val configProps = mutableMapOf<String, Any>()
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun carKafkaTemplate(): KafkaTemplate<String, Car> {
        return KafkaTemplate(carProducerFactory())
    }

}