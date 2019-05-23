package com.ynov.kotlin.rickmorty.presentation.characterDetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.characterDetail.viewModel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("characterId")?.let { characterId ->
            this.bindViewmodel(characterId)
        }
        // très bien ça
    }

    private fun bindViewmodel(characterId: String) {
        viewModel = CharacterDetailViewModel(characterId)
        viewModel.character.observe(this, Observer {
            this.bindWidgetsData(it)
        })

        viewModel.errorLiveData.observe(this, Observer {
            // TODO utiliser un ?.let ici
            if (view != null) {
                this.showError(it.localizedMessage)
            }
        })
    }

    private fun showError(message: String) {
        view?.let { it ->
            Snackbar
                .make(it, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun bindWidgetsData(character: RMCharacter) {
        activity_character_detail_name_textview.text = character.name
        activity_character_detail_status_textview.text = character.status
        activity_character_detail_species_textview.text = character.species
        activity_character_detail_gender_textview.text = character.gender
        activity_character_detail_location_textview.text = character.location
        activity_character_detail_origin_textview.text = character.origin

        Picasso
            .get()
            .load(character.image)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(activity_character_detail_imageview)
    }
}
