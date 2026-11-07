// Lab 5, Step 6: Wire It Up
package com.example

import com.example.repositories.InMemoryBookRepository
import com.example.routes.bookRoutes
import com.example.services.BookService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun Application.module() {
    install(ContentNegotiation) { json() }
    val repository = InMemoryBookRepository()
    val service = BookService(repository)
    routing { bookRoutes(service) }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") { module() }.start(wait = true)
}