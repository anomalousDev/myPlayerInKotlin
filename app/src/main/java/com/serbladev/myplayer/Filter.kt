package com.serbladev.myplayer

sealed class Filter {
    object None: Filter()
    class ByType (val type: MediaItem.Type) : Filter()
}