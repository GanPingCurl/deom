package ru.alexander.deom.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.alexander.deom.dto.AuthDto
import ru.alexander.deom.service.password.PasswordService

@RestController
class AuthController(
    private val passwordService: PasswordService
) {
    @PostMapping("/auth")
    fun auth(@RequestBody authDto: AuthDto): ResponseEntity<Void> {
        passwordService.cacheUser(authDto.password, authDto.username)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/exists")
    fun exists(@RequestBody authDto: AuthDto): ResponseEntity<Boolean> {
        val user = passwordService.getUser(authDto.password, authDto.username) ?: return ResponseEntity.ok().body(false)
        return ResponseEntity.ok().body(true)
    }
}