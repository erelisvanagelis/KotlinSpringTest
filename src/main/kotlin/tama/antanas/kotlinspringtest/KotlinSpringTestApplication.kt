package tama.antanas.kotlinspringtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class KotlinSpringTestApplication{
	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()
}

fun main(args: Array<String>) {
	runApplication<KotlinSpringTestApplication>(*args)
}
