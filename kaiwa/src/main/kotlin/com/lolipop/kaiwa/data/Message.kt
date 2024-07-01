package com.lolipop.kaiwa.data

import com.lolipop.kaiwa.core
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Message(val id: String, val content: String) {
    override fun toString() = "$id<##DELIMIT##>$content"
}

@Service
class MessageService {
    fun write(message: Message) {
        core.writeToFrozen(listOf(message))
    }

    fun from(entity: String, func: (List<Message>) -> Unit) {
        core.readFromFrozen(100) {
            func(it.filter { msg -> msg.id == entity })
        }
    }

    fun getAllWhich(predicate: (Message) -> Boolean = { true }): List<Message> {
        val matches = mutableListOf<Message>()
        core.readFromFrozen {
            matches.addAll(it.filter(predicate))
        }
        return matches.toList()
    }
}

@RestController
@RequestMapping("message")
class MessageController {
    @Autowired
    lateinit var messageService: MessageService

    @GetMapping
    fun getAllMessages() = messageService.getAllWhich()

    @GetMapping("{id}")
    fun getMessagesFrom(@PathVariable id: String) =
        messageService.getAllWhich {
            it.id == id
        }

    @PostMapping
    fun saveMessage(@RequestBody message: Message) = messageService.write(message)
}