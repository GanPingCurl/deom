package ru.alexander.deom.service.cache

import org.springframework.cache.interceptor.KeyGenerator
import ru.alexander.deom.service.hash.TokenHashService
import java.lang.reflect.Method

class UserPasswordKeyGenerator(
    private val tokenHashService: TokenHashService
) : KeyGenerator {
    override fun generate(target: Any, method: Method, vararg params: Any?): Any {
        val password = params[0]
        val username = params[1]
        return tokenHashService.hash("$password-$username")
    }
}