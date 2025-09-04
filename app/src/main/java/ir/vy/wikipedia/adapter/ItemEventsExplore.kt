package ir.vy.wikipedia.adapter

import ir.vy.wikipedia.data.ItemPost

interface ItemEventsExplore {

    fun onItemClicked(itemPost: ItemPost)
}