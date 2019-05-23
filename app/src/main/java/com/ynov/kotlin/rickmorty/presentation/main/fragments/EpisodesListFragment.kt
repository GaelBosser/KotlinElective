package com.ynov.kotlin.rickmorty.presentation.main.fragments


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
import com.ynov.kotlin.rickmorty.adapters.episodesList.RMEpisodesListAdapter
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode
import com.ynov.kotlin.rickmorty.presentation.main.viewModels.EpisodesListViewModel
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodesListFragment : Fragment() {

    private lateinit var viewModel: EpisodesListViewModel
    // TODO de même que dans CharactersListFragment avec by lazy
    private val episodesListAdapter = RMEpisodesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_episodes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSwipeRefreshListener()
        setUpViewModel()
        episodes_recycle_view.apply { bindViewModel(this) }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(EpisodesListViewModel::class.java)
    }

    private fun setSwipeRefreshListener() {
        episodes_refresh_layout.setOnRefreshListener {
            viewModel.loadEpisodesList()
        }
    }

    private fun update(episodesList: List<RMEpisode>) {
        episodes_refresh_layout.isRefreshing = false
        episodesListAdapter.updateList(episodesList)
    }

    private fun bindViewModel(recycleView: RecyclerView) {
        viewModel.episodesList.observe(this, Observer { episodesList ->
            recycleView.layoutManager = LinearLayoutManager(context)
            update(episodesList)
            recycleView.adapter = episodesListAdapter
        })

        viewModel.errorLiveData.observe(this, Observer { e ->
            // TODO utiliser un ?.let ici plutôt
            if (view != null) {
                this.showError(e.localizedMessage)
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
}
