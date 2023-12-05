package ru.alexander.deom.service.password

import ru.alexander.deom.model.User

interface PasswordService {
    fun getUser(plainPassword: String, username: String): User?
    fun cacheUser(plainPassword: String, username: String)
}