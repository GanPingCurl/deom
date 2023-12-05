package ru.alexander.deom

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.alexander.deom.service.password.PasswordService

@SpringBootTest
class DeomApplicationTests {

	@Autowired
	private lateinit var passwordService: PasswordService

	@Test
	fun testPasswordCacheService() {
		// Arrange, Act
		passwordService.cacheUser(PLAIN_PASSWORD, USERNAME)
		val user = passwordService.getUser(PLAIN_PASSWORD, USERNAME)

		// Assert
		assertEquals(USERNAME, user!!.username)
	}

	companion object {
		const val PLAIN_PASSWORD = "PLAIN_PASSWORD"
		const val USERNAME = "USERNAME"
	}
}
