package com.lolipop.kaiwa.core

import com.lolipop.kaiwa.data.Message
import java.io.File

fun String.toMessage(): Message {
    val tokens = this.split("<##DELIMIT##>")
    return Message(tokens[0], tokens[1])
}

class Core(private val path: String, private val file: File = File(path)) {
    fun readFromFrozen(howMany: Int = -1, func: (List<Message>) -> Unit) {
        val entries = file.useLines { it.toList() }
        val messages =  if(howMany < 0) entries.map { it.toMessage() }
                        else (0..< if (entries.size < howMany) entries.size else howMany)
                            .map { entries[it].toMessage() }
        func(messages)
    }

    fun writeToFrozen(entry: List<Message>) {
        readFromFrozen { cached ->
            val curated = mutableListOf<Message>()
            curated.addAll(entry)
            curated.addAll(cached)
            file.writeText(
                curated.map { it.toString() }
                       .reduce { acc, bit -> "$acc\n$bit" }
            )
        }
    }
}