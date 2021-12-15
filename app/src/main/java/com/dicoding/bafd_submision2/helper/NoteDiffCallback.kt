package com.dicoding.bafd_submision2.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.bafd_submision2.model.DataUser

class NoteDiffCallback(private val mDataUser: List<DataUser>, private val mNewNoteList: List<DataUser>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mDataUser.size
    }
    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mDataUser[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = mDataUser[oldItemPosition]
        val newUser = mNewNoteList[newItemPosition]
        return oldUser.login== newUser.login
    }
}