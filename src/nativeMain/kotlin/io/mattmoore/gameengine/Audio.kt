package io.mattmoore.gameengine.audio

import libsdl.*

class Audio()

fun createAudio(): Audio {
    if (Mix_OpenAudio(44100, MIX_DEFAULT_FORMAT, 2, 2048) < 0) {
        println("Error initializing Open Audio.")
        SDL_Quit()
    }
    return Audio()
}

fun playSound(path: String) {
    val sound = Mix_LoadMUS(path)
    if (sound == null) {
        println("Error loading sound file.")
    }
    Mix_PlayMusic(sound, 1)
    while (Mix_PlayingMusic() == 1) {
        SDL_Delay(100)
    }
}
