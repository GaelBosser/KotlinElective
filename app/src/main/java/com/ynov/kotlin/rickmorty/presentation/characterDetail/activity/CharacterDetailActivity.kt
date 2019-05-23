package com.ynov.kotlin.rickmorty.presentation.characterDetail.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.characterDetail.fragment.CharacterDetailFragment
import com.ynov.kotlin.rickmorty.presentation.main.fragments.CHARACTER_ID

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val bundle = Bundle()
        bundle.putString(CHARACTER_ID, intent.getStringExtra(CHARACTER_ID))
        val characterDetailFragment = CharacterDetailFragment()
        characterDetailFragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.characters_detail_fragment_container, characterDetailFragment)
            .commit()
    }
}
