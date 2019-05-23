package com.ynov.kotlin.rickmorty.adapters.common.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RMListAdapterViewHolder<EntityType, V>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(entity: EntityType, onClick: (V) -> Unit)
}