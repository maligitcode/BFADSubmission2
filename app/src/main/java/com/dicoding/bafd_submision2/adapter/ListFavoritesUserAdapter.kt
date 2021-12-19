package com.dicoding.bafd_submision2.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.bafd_submision2.R
import com.dicoding.bafd_submision2.databinding.ItemUserBinding
import com.dicoding.bafd_submision2.helper.NoteDiffCallback
import com.dicoding.bafd_submision2.model.DataUser
import com.dicoding.bafd_submision2.view.UserDetailActivity
import com.kylix.submissionbfaa3.viewmodels.FavoriteViewModel
import java.util.*

class ListFavoritesUserAdapter(application: Application) : RecyclerView.Adapter<ListFavoritesUserAdapter.ListFavoritesUserHolder>() {
    private val listFaforites = ArrayList<DataUser>()
    private lateinit var favoriteViewModel: FavoriteViewModel


    var application: Application = application
        // Custom Getter
        get() {
            return field
        }

    fun setListFaforites(listFaforites: List<DataUser>) {
        val diffCallback = NoteDiffCallback(this.listFaforites, listFaforites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFaforites.clear()
        this.listFaforites.addAll(listFaforites)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFavoritesUserHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListFavoritesUserHolder(binding)
    }

    override fun onBindViewHolder(holder: ListFavoritesUserHolder, position: Int) {
        holder.bind(listFaforites[position])
        val data = listFaforites[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent = DataUser(
                1,
                data.login,
                data.avatar_url,
                data.name,
                data.location,
                data.company,
                data.public_repos,
                data.followers,
                data.following,
            )
            favoriteViewModel = FavoriteViewModel(
                application,
            )

            val builder =
                AlertDialog.Builder(holder.itemView.getRootView().getContext(), R.style.MyAlertDialogStyle)
            builder.setMessage("Delete this data?")
            builder.setPositiveButton(
                "Detail User"
            ) { dialog, arg1 ->val mIntent = Intent(it.context, UserDetailActivity::class.java)
                mIntent.putExtra(UserDetailActivity.EXTRA_DETAIL, dataUserIntent)
                it.context.startActivity(mIntent) }

            builder.setNegativeButton(
                "Delete User"
            ) { dialog, arg1 ->  favoriteViewModel.delete(dataUserIntent) }

            val alert = builder.create()
            alert.show()


        }

    }

    override fun getItemCount(): Int {
        return listFaforites.size
    }

    inner class ListFavoritesUserHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val avatar: ImageView = itemView.findViewById(R.id.avatar) as ImageView
        val fullName: TextView = itemView.findViewById(R.id.fullName) as TextView
        val username: TextView = itemView.findViewById(R.id.fullName) as TextView
        val count_repository: TextView = itemView.findViewById(R.id.count_repository) as TextView
        val count_followers: TextView = itemView.findViewById(R.id.count_followers) as TextView
        val count_following: TextView = itemView.findViewById(R.id.count_followers) as TextView

        @SuppressLint("StringFormatInvalid")
        fun bind(dataUsers: DataUser) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(dataUsers.avatar_url)
                    .apply(RequestOptions().override(100, 100))
                    .into(avatar)

                fullName.text = dataUsers.name
                username.text = itemView.context.getString(R.string.login, dataUsers.login)
                count_repository.text =
                    itemView.context.getString(R.string._100_repository, dataUsers.public_repos)
                count_followers.text =
                    itemView.context.getString(R.string.followers, dataUsers.followers)
                count_following.text =
                    itemView.context.getString(R.string.following, dataUsers.following)
            }
        }
    }
}