package dev.kartikboreda.multiauthwebapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
class MultiAuthWebAppApplication

fun main(args: Array<String>) {
    runApplication<MultiAuthWebAppApplication>(*args)
}
