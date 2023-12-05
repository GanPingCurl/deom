package ru.alexander.deom.service.cache

import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import ru.alexander.deom.config.ApplicationConfig.Companion.PASSWORD_CACHE
import ru.alexander.deom.config.ApplicationConfig.Companion.PASSWORD_KEY_GENERATOR
import ru.alexander.deom.model.User

open class PasswordCacheServiceImpl : PasswordCacheService {
    @Cacheable(PASSWORD_CACHE, keyGenerator = PASSWORD_KEY_GENERATOR)
    override fun getUser(plainPassword: String, username: String): User? = null

    @CachePut(value = [PASSWORD_CACHE], keyGenerator = PASSWORD_KEY_GENERATOR)
    override fun cacheUser(plainPassword: String, username: String): User {
        return User(username)
    }
}