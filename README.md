# SL-Kaiwa

Kaiwa is a simple server-side example application that is meant to create a 
simple interface through which 2 clients may interact. 

This application is not standalone, meaning it is not designed to
be used as-is with any other application. Rather, it's meant to be a 
quick template I can reach to in case a project I am working on sometime 
down the line is in need of such functionality.

I also took the opportunity to test some structure ideas. Data classes in Kotlin make POJOs really easy to make, so I experimented with writing
one file for the model, service, and controller.

_As a side note, the name "kaiwa" is from the Japanese "会話", meaning "conversation"._

## v0.0.1

The API includes the following operations:
- get all messages (`GET /message`)
- get messages from a specific entity (`GET /message/{id}`)
- save a message (`POST /message`, takes a `Message` JSON in `RequestBody`)

As the API doesn't make full use of the included service, I'll include what that can do too.
- write a message to the `core` (`Message` -> `Unit`)
- get read-only access to messages from an entity ((`String`, `List<Message>` -> `Unit`) -> `Unit`)
- get an immutable list of messages that satisfy some predicate ((`Message` -> `Boolean`) -> `List<Message>`)

The "core" is an internal mechanism that handles writing to a persistent
data source. For the purposes of this template, it is currently writing to a text file.