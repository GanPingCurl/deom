package ru.alexander.deom.service.password

import ru.alexander.deom.model.User
import ru.alexander.deom.service.cache.PasswordCacheService

class PasswordServiceImpl(
    private val passwordCacheService: PasswordCacheService
) : PasswordService {
    override fun getUser(plainPassword: String, username: String): User? {
        return passwordCacheService.getUser(plainPassword, username)
    }

    override fun cacheUser(plainPassword: String, username: String) {
        passwordCacheService.cacheUser(plainPassword, username)
    }
}