package ru.alexander.deom.service.hash

interface TokenHashService {
    fun hash(source: String): String
}