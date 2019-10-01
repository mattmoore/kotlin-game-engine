package io.mattmoore.gameengine

import kotlinx.cinterop.*
import libsdl.*

import io.mattmoore.gameengine.engine.*
import io.mattmoore.gameengine.audio.*

fun main() {
    val engine = createEngine("Game Engine", 0, 0, 640, 480)
    gameLoop {
//        SDL_SetRenderDrawColor(engine.graphics.renderer, 0, 0, 0, 0)
//        SDL_RenderClear(engine.graphics.renderer)
//        SDL_SetRenderDrawColor(engine.graphics.renderer, 255, 255, 255, 0)
//
//        memScoped {
//            val rect = alloc<SDL_Rect>()
//            rect.w = 200
//            rect.h = 200
//            rect.x = 0
//            rect.y = 0
//            SDL_RenderDrawRect(engine.graphics.renderer, rect.ptr.reinterpret())
//        }
//
//        SDL_RenderPresent(engine.graphics.renderer)
    }
}
