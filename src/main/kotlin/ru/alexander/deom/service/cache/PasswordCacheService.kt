package ru.alexander.deom.service.cache

import ru.alexander.deom.model.User

interface PasswordCacheService {
    fun getUser(plainPassword: String, username: String): User?
    fun cacheUser(plainPassword: String, username: String): User
}