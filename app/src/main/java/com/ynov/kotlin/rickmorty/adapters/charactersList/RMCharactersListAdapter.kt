package com.ynov.kotlin.rickmorty.adapters.charactersList

import android.view.View
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.adapters.charactersList.viewHolder.RMCharacterViewHolder
import com.ynov.kotlin.rickmorty.adapters.common.RMListAdapter
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter

class RMCharactersListAdapter: RMListAdapter<RMCharacter, String, RMCharacterViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.character_cell_layout
    }

    override fun getViewHolder(view: View): RMCharacterViewHolder = RMCharacterViewHolder(view)
}