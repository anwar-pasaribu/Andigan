package com.unwur.andiganapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform