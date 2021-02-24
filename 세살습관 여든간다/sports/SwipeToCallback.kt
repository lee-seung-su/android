package org.techtown.sports

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeToCallback : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        //val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val dragFlag = 0
        val swipeFlag = 0
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

}
