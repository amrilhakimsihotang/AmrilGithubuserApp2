package com.amrilhs.amrilgithubuserapp2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrilhs.amrilgithubuserapp2.DetailActivity
import com.amrilhs.amrilgithubuserapp2.R
import com.amrilhs.amrilgithubuserapp2.model.UsersData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_user.view.*

class UsersDataAdapter(private val listusersgit: ArrayList<UsersData>):RecyclerView.Adapter<UsersDataAdapter.UsersViewHolder>() {

    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(userdata: UsersData){
            with(itemView){
            Glide.with(itemView.context)
                .load(userdata.avatar)
                .apply(RequestOptions().override(80,80))
                .into(listavatar)
                listusername.text= itemView.context.getString(R.string.det_username, userdata.username)
                listfullName.text=userdata.name
                listrepository.text=itemView.context.getString(R.string.repository,userdata.repository)
                listfollowers.text=itemView.context.getString(R.string.followers,userdata.followers)
                listfollowing.text=itemView.context.getString(R.string.followings,userdata.following)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_user,parent,false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
       holder.bind(listusersgit[position])
        val usersdata=listusersgit[position]
        holder.itemView.setOnClickListener {
            val usersdataIntent = UsersData(
                usersdata.avatar,
                usersdata.username,
                usersdata.name,
                usersdata.company,
                usersdata.location,
                usersdata.repository,
                usersdata.followers,
                usersdata.following
            )

            val moveIntent = Intent(it.context, DetailActivity::class.java )
            moveIntent.putExtra(DetailActivity.EXTRA_DETAIL,usersdataIntent)
            it.context.startActivity(moveIntent)
        }
    }

    override fun getItemCount(): Int {
        return listusersgit.size
    }

    fun setData(items: ArrayList<UsersData>) {
        listusersgit.clear()
        listusersgit.addAll(items)
        notifyDataSetChanged()
    }
}