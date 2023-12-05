package ru.alexander.deom.service.hash

import org.slf4j.LoggerFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Sha256TokenHashService(
    private val tokenSalt: String
) : TokenHashService {
    private val logger = LoggerFactory.getLogger(Sha256TokenHashService::class.java)
    override fun hash(source: String): String =
    try {
        val bytes = source.toByteArray()
        val md = MessageDigest.getInstance(SHA256)
        md.update(tokenSalt.toByteArray())
        val digest: ByteArray = md.digest(bytes)
        digest.fold("") { str, it -> str + "%02x".format(it) }
    } catch (e: NoSuchAlgorithmException) {
        logger.error("Не найдено необходимого алгоритма для хэширования [$SHA256]", e)
        throw RuntimeException(e)
    }

    companion object {
        private const val SHA256 = "SHA-256"
    }
}