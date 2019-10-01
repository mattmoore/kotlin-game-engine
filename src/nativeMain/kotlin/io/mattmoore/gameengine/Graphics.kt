package io.mattmoore.gameengine.graphics

import kotlinx.cinterop.*
import libsdl.*

data class Graphics(
        var title: String,
        var x: Int,
        var y: Int,
        var width: Int,
        var height: Int,
        var window: CPointer<SDL_Window>?,
//        var renderer: CPointer<SDL_Renderer>?,
        var glContext: SDL_GLContext?
)

fun createGraphics(title: String, x: Int, y: Int, width: Int, height: Int): Graphics {
    val window = createWindow(title, x, y, width, height)
//    val renderer = createRenderer(window)
    val glContext = createContext(window)
    initGL(window)
    return Graphics(title, x, y, width, height, window, glContext)
}

fun createWindow(title: String, x: Int, y: Int, width: Int, height: Int): CPointer<SDL_Window>? {
    val window = SDL_CreateWindow(title, x, y, width, height, SDL_WINDOW_SHOWN)
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

fun createContext(window: CValuesRef<SDL_Window>?): SDL_GLContext? {
    val context = SDL_GL_CreateContext(window)
    if (context == null) {
        println("Error initializing GL context.")
        SDL_DestroyWindow(window)
        SDL_Quit()
    }
    return context
}

fun initGL(window: CValuesRef<SDL_Window>?) {
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()

    glClearColor(0.39f, 0.58f, 0.93f, 1.0f)
    glViewport(0, 0, 640, 480)

    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()

    glClear((GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT).convert())
    // glBegin(GL_TRIANGLES)
    // glEnd()
    SDL_GL_SwapWindow(window)
}
