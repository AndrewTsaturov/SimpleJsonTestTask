package com.andrewtsaturov.simplejsontesttask.presentation.navigation

import ru.terrakok.cicerone.Cicerone

class LocalCiceroneHolder {
    val rootCicerone = Cicerone.create()
    val rootHolder = rootCicerone.navigatorHolder
    val rootRouter = rootCicerone.router

    val albumsCicerone = Cicerone.create()
    val albumsHolder = albumsCicerone.navigatorHolder
    val albumsRouter = albumsCicerone.router

    val postsCicerone = Cicerone.create()
    val postsHolder = postsCicerone.navigatorHolder
    val postsRouter = postsCicerone.router
}