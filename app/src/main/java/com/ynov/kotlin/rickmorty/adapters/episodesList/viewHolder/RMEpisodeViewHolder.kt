package com.ynov.kotlin.rickmorty.adapters.episodesList.viewHolder

import android.view.View
import com.ynov.kotlin.rickmorty.adapters.common.viewHolders.RMListAdapterViewHolder
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode
import kotlinx.android.synthetic.main.episode_cell_layout.view.*

class RMEpisodeViewHolder(itemView: View): RMListAdapterViewHolder<RMEpisode, String>(itemView) {

    override fun bind(entity: RMEpisode, onClick: (String) -> Unit) {
        itemView.main_activity_episode_episodeNumber_text.text = entity.episode
        itemView.main_activity_episode_name_text.text = entity.name
        itemView.setOnClickListener { onClick(entity.id.toString()) }
    }
}