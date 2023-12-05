package ru.alexander.deom.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.alexander.deom.service.cache.PasswordCacheService
import ru.alexander.deom.service.cache.PasswordCacheServiceImpl
import ru.alexander.deom.service.cache.UserPasswordKeyGenerator
import ru.alexander.deom.service.hash.Sha256TokenHashService
import ru.alexander.deom.service.hash.TokenHashService
import ru.alexander.deom.service.password.PasswordService
import ru.alexander.deom.service.password.PasswordServiceImpl
import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class ApplicationConfig {
    @Bean
    fun cacheManager(
        @Value("\${cache.capacity}") capacity: Long,
        @Value("\${cache.duration.seconds}") duration: Long
    ): CacheManager {
        val caffeineCacheManager = CaffeineCacheManager()
        caffeineCacheManager.registerCustomCache(
            PASSWORD_CACHE,
            Caffeine.newBuilder()
                .expireAfterAccess(duration, TimeUnit.SECONDS)
                .maximumSize(capacity)
                .build()
        )
        return caffeineCacheManager
    }

    @Bean
    fun userPasswordKeyGenerator(tokenHashService: TokenHashService): KeyGenerator =
        UserPasswordKeyGenerator(tokenHashService)

    @Bean
    fun passwordCacheService(): PasswordCacheService =
        PasswordCacheServiceImpl()

    @Bean
    fun tokenHashService(@Value("hash.salt") salt: String): TokenHashService =
        Sha256TokenHashService(salt)

    @Bean
    fun passwordService(passwordCacheService: PasswordCacheService): PasswordService =
        PasswordServiceImpl(passwordCacheService)

    companion object {
        const val PASSWORD_CACHE = "password"
        const val PASSWORD_KEY_GENERATOR = "userPasswordKeyGenerator"
    }
}