package com.amrilhs.amrilgithubuserapp2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrilhs.amrilgithubuserapp2.R
import com.amrilhs.amrilgithubuserapp2.model.UsersFollowing
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_following.view.*


class FollowingAdapter(private val listFollowing: ArrayList<UsersFollowing>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(usersFollowing: UsersFollowing) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(usersFollowing.avatar)
                    .apply(RequestOptions().override(90,90))
                    .into(followingavatar)
                followingfullName.text=usersFollowing.name
                followingusername.text= itemView.context.getString(R.string.det_username, usersFollowing.username)
                followingrepository.text=itemView.context.getString(R.string.repository,usersFollowing.repository)
                followingfollowers.text=itemView.context.getString(R.string.followers,usersFollowing.followers)
                followingfollowing.text=itemView.context.getString(R.string.followings,usersFollowing.following)

            }
        }
    }

    fun setFollowing(items: ArrayList<UsersFollowing>) {
        listFollowing.clear()
        listFollowing.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        return FollowingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_user,parent,false)
        )
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(listFollowing[position])
    }

    override fun getItemCount(): Int {
        return listFollowing.size
    }
}