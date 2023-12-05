package ru.alexander.deom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.alexander.deom.service.password.PasswordService

@SpringBootApplication
class DeomApplication

fun main(args: Array<String>) {
	runApplication<DeomApplication>(*args)
}
