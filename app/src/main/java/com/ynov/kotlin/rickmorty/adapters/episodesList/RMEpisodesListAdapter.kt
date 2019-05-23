package com.ynov.kotlin.rickmorty.adapters.episodesList

import android.view.View
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.adapters.episodesList.viewHolder.RMEpisodeViewHolder
import com.ynov.kotlin.rickmorty.adapters.common.RMListAdapter
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode

class RMEpisodesListAdapter: RMListAdapter<RMEpisode, String, RMEpisodeViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.episode_cell_layout
    }
    // TODO pour optimiser le code, ne pas hésiter à utiliser :
    //  override fun getLayout(): Int = R.layout.episode_cell_layout
    //  mais vu le reste du code j'imagine que c'est un oubli

    override fun getViewHolder(view: View): RMEpisodeViewHolder =
        RMEpisodeViewHolder(view)
}