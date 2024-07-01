package com.lolipop.kaiwa

import com.lolipop.kaiwa.core.Core
import com.lolipop.kaiwa.core.Message
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KaiwaApplication

fun main(args: Array<String>) {
	//runApplication<KaiwaApplication>(*args)

	val core = Core("kaiwa/src/main/kotlin/com/lolipop/kaiwa/core/frozen.txt")
	core.writeToFrozen(listOf(
		Message("entity1", "AYY WHATS UP"),
		Message("entity2", "A test for a test, huh...")
	))
	core.readFromFrozen { println(it) }
}
