package com.ynov.kotlin.rickmorty.adapters.charactersList.viewHolder

import android.view.View
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.adapters.common.viewHolders.RMListAdapterViewHolder
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import kotlinx.android.synthetic.main.character_cell_layout.view.*

class RMCharacterViewHolder(itemView: View): RMListAdapterViewHolder<RMCharacter, String>(itemView) {

    override fun bind(entity: RMCharacter, onClick: (String) -> Unit) {
        Picasso
            .get()
            .load(entity.image)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(itemView.main_activity_character_image)

        itemView.main_activity_character_gender_text.text = entity.gender
        itemView.main_activity_character_name_text.text = entity.name
        itemView.setOnClickListener { onClick(entity.id.toString()) }
    }
}