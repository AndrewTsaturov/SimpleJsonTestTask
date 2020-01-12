package com.andrewtsaturov.simplejsontesttask.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnBackPressed
import com.andrewtsaturov.simplejsontesttask.presentation.navigation.LocalCiceroneHolder
import com.andrewtsaturov.simplejsontesttask.presentation.navigation.Screens
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val navigatorHolder = get<LocalCiceroneHolder>().rootHolder
    private val navigator by lazy { SupportAppNavigator(this, R.id.fragment_container) }
    private val router = get<LocalCiceroneHolder>().rootRouter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.popBackStack()
            router.newRootScreen(Screens.StartScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as? OnBackPressed)?.onBackPressed()
            ?: back()
    }

    private fun back(){
        moveTaskToBack(true)
    }
}
