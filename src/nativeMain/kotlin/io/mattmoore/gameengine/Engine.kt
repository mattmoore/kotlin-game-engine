package io.mattmoore.gameengine.engine

import kotlinx.cinterop.*
import libsdl.*

import io.mattmoore.gameengine.graphics.*
import io.mattmoore.gameengine.audio.*

data class Engine(var graphics: Graphics, var audio: Audio)

fun createEngine(title: String, x: Int, y: Int, width: Int, height: Int): Engine {
    initSDL()
    val graphics = createGraphics(title, x, y, width, height)
    val audio = createAudio()
    return Engine(graphics, audio)
}

fun stopEngine() {
    SDL_Quit()
}

fun initSDL() {
    if (SDL_Init(SDL_INIT_EVERYTHING) < 0) {
        println("Error initialising SDL.")
        SDL_Quit()
    }
}

fun gameLoop(lambda: () -> Unit) {
    memScoped {
        var isQuit = false
        var event = alloc<SDL_Event>()
        while (!isQuit) {
            SDL_PollEvent(event.ptr)
            if (event.type == SDL_QUIT) {
                isQuit = true
            }
            lambda()
        }
    }
}
