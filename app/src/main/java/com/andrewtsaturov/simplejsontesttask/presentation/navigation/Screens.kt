package com.andrewtsaturov.simplejsontesttask.presentation.navigation
import androidx.fragment.app.Fragment
import com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.BottomNavigationFragment
import com.andrewtsaturov.simplejsontesttask.presentation.screen.comments.CommentsFragment
import com.andrewtsaturov.simplejsontesttask.presentation.screen.posts.PostsFragment
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class StartScreen(): SupportAppScreen(){
        override fun getFragment(): Fragment = BottomNavigationFragment()
    }

    class PostScreen(): SupportAppScreen(){
        override fun getFragment() = PostsFragment()
    }

    class CommentsScreen(private val id: Long) : SupportAppScreen() {
        override fun getFragment() = CommentsFragment.newInstanse(id)
    }
}