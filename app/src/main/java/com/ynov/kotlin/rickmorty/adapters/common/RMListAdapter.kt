package com.ynov.kotlin.rickmorty.adapters.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.adapters.common.viewHolders.RMListAdapterViewHolder

abstract class RMListAdapter<EntityType, V, ViewHolderType: RMListAdapterViewHolder<EntityType, V>>: RecyclerView.Adapter<ViewHolderType>() {

    @LayoutRes
    abstract fun getLayout(): Int
    abstract fun getViewHolder(view: View): ViewHolderType

    var onClickListener: (V) -> Unit = {}
    private var list: MutableList<EntityType> = mutableListOf()

    fun updateList(list: List<EntityType>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderType {
        val v = LayoutInflater.from(parent.context)
            .inflate(getLayout(), parent, false)
        return getViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        holder.bind(list[position], onClickListener)
    }
}