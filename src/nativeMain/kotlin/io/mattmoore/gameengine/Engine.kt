package io.mattmoore.gameengine

import kotlin.system.exitProcess
import kotlinx.cinterop.*
import libsdl.*

fun main() {
    initializeSDL()
    val window = createWindow()
    val renderer = createRenderer(window)
    processEvents(renderer)
    SDL_Quit()
}

fun initializeSDL() {
    if (SDL_Init(SDL_INIT_EVERYTHING) < 0) {
        println("Error initialising SDL exiting.")
        SDL_Quit()
    }
}

fun createWindow(): CPointer<SDL_Window>? {
    val window = SDL_CreateWindow("Hello World!", 0, 0, 640, 480, SDL_WINDOW_SHOWN)
    println(window)
    if (window == null) {
        println("Error setting SDL Video Mode.")
        SDL_Quit()
    }
    return window
}

fun createRenderer(window: CPointer<SDL_Window>?): CPointer<SDL_Renderer>? {
    val renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED)
    if (renderer == null) {
        println("Error initialising renderer.")
        SDL_DestroyWindow(window)
        SDL_Quit()
    }
    return renderer
}

fun render(renderer: CPointer<SDL_Renderer>?) {
    SDL_SetRenderDrawColor(renderer, 0, 0, 0, 0)
    SDL_RenderClear(renderer)
    SDL_SetRenderDrawColor(renderer, 255, 255, 255, 0)

    memScoped {
        val rect = alloc<SDL_Rect>()
        rect.w = 200
        rect.h = 200
        rect.x = 0
        rect.y = 0
        SDL_RenderDrawRect(renderer, rect.ptr.reinterpret())
    }

    SDL_RenderPresent(renderer)
}

fun processEvents(renderer: CPointer<SDL_Renderer>?) {
    memScoped {
        var isQuit = false
        var event = alloc<SDL_Event>()
        while (!isQuit) {
            SDL_PollEvent(event.ptr)
            if (event.type == SDL_QUIT) {
                isQuit = true
            }
            render(renderer)
        }
    }
}
