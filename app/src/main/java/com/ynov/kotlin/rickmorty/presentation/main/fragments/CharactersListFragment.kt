package com.ynov.kotlin.rickmorty.presentation.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.adapters.charactersList.RMCharactersListAdapter
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.characterDetail.activity.CharacterDetailActivity
import com.ynov.kotlin.rickmorty.presentation.main.viewModels.CharactersListViewModel
import kotlinx.android.synthetic.main.fragment_characters_list.*

const val CHARACTER_ID = "characterId"

class CharactersListFragment : Fragment() {

    private lateinit var viewModel: CharactersListViewModel
    private val charactersListAdapter = RMCharactersListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setSwipeRefreshListener()
        characters_recycle_view.apply { bindViewModel(this) }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(CharactersListViewModel::class.java)
    }

    private fun setSwipeRefreshListener() {
        characters_refresh_layout.setOnRefreshListener {
            viewModel.loadCharactersList()
        }
    }

    private fun update(charactersList: List<RMCharacter>) {
        characters_refresh_layout.isRefreshing = false
        charactersListAdapter.updateList(charactersList)
    }

    private fun bindViewModel(recycleView: RecyclerView) {
        viewModel.charactersList.observe(this, Observer { charactersList ->
            characters_recycle_view.layoutManager = LinearLayoutManager(context)
            update(charactersList)
            charactersListAdapter.onClickListener = { id ->
                this.startCharacterDetailActivity(id)
            }

            recycleView.adapter = charactersListAdapter
        })

        viewModel.errorLiveData.observe(this, Observer { e ->
            if (view != null) {
                this.showError(e.localizedMessage)
            }
        })
    }

    private fun startCharacterDetailActivity(id: String) {
        val intent = Intent(context, CharacterDetailActivity::class.java)
        intent.putExtra(CHARACTER_ID, id)
        startActivity(intent)
    }

    private fun showError(message: String) {
        view?.let { it ->
            Snackbar
                .make(it, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }

}
