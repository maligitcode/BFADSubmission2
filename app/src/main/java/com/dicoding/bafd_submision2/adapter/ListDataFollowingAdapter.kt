package com.dicoding.bafd_submision2.viewModel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.bafd_submision2.R
import com.dicoding.bafd_submision2.model.DataUser
import com.dicoding.bafd_submision2.network.FollowingResponse
import com.dicoding.bafd_submision2.view.UserDetailActivity

//import com.dicoding.bafd_submision2.view.DetailActivity

class ListDataFollowingAdapter(private val listDataUsersGithub: ArrayList<FollowingResponse>) :
    RecyclerView.Adapter<ListDataFollowingAdapter.ListDataHolder>() {

    fun setData(items: ArrayList<FollowingResponse>) {
        listDataUsersGithub.clear()
        listDataUsersGithub.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.avatar) as ImageView
        val fullName: TextView = itemView.findViewById(R.id.fullName) as TextView
        val username: TextView = itemView.findViewById(R.id.fullName) as TextView
        val count_repository: TextView = itemView.findViewById(R.id.count_repository) as TextView
        val count_followers: TextView = itemView.findViewById(R.id.count_followers) as TextView
        val count_following: TextView = itemView.findViewById(R.id.count_followers) as TextView

        fun bind(dataUsers: FollowingResponse) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDataUsersGithub.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listDataUsersGithub[position])

        val data = listDataUsersGithub[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent = DataUser(
                data.avatar_url,
                data.name,
                data.followers,
                data.company,
                data.following,
                data.public_repos,
                data.login,
                data.location,
                data.following,
            )
            val mIntent = Intent(it.context, UserDetailActivity::class.java)
            mIntent.putExtra(UserDetailActivity.EXTRA_DETAIL, dataUserIntent)
            it.context.startActivity(mIntent)
        }
    }


}