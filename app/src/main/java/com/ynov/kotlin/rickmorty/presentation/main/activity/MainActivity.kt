package com.ynov.kotlin.rickmorty.presentation.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.main.fragments.CharactersListFragment
import com.ynov.kotlin.rickmorty.presentation.main.fragments.EpisodesListFragment

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_characters -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_activity_fragment_container, CharactersListFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_episodes -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_activity_fragment_container, EpisodesListFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_fragment_container, CharactersListFragment())
                .commit()
        }

        setUpBottomNavigationView()
    }

    private fun setUpBottomNavigationView() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        // TODO pourquoi ne pas directement utiliser nav_view au lieu de faire un findView ?
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
