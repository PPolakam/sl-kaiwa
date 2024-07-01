package com.lolipop.kaiwa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KaiwaApplication

fun main(args: Array<String>) {
	runApplication<KaiwaApplication>(*args)
}
