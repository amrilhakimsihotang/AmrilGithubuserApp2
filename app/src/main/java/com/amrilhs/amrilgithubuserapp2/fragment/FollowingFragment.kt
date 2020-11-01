package com.amrilhs.amrilgithubuserapp2.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrilhs.amrilgithubuserapp2.R
import com.amrilhs.amrilgithubuserapp2.adapter.FollowingAdapter
import com.amrilhs.amrilgithubuserapp2.model.UsersData
import com.amrilhs.amrilgithubuserapp2.model.UsersFollowing
import com.amrilhs.amrilgithubuserapp2.viewModel.UserFollowingViewModel
import kotlinx.android.synthetic.main.following_fragment.*

class FollowingFragment : Fragment() {

    companion object {
        val TAG = FollowingFragment::class.java.simpleName
        const val EXTRA_DETAIL = "extra_detail"
    }

    private var listFollowing: ArrayList<UsersFollowing> = ArrayList()
    private lateinit var followingAdapter: FollowingAdapter
    private lateinit var followingviewmodel: UserFollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.following_fragment, container, false)
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFollowing.clear()
        followingAdapter = FollowingAdapter(listFollowing)
        followingviewmodel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(UserFollowingViewModel::class.java)

        val userData = requireActivity().intent.getParcelableExtra<UsersData>(EXTRA_DETAIL) as UsersData
        recyclerviewFollowing()

        followingviewmodel.getDataGit(requireActivity().applicationContext,userData.username.toString())
        showprogressBarFollowing(true)

        followingviewmodel.getAllFollowing().observe(requireActivity(), Observer { listFollowing ->
            if (listFollowing != null) {
                followingAdapter.setFollowing(listFollowing)
                showprogressBarFollowing(false)
            }
        })
    }
    private fun recyclerviewFollowing() {
        recyclerView_Following.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView_Following.setHasFixedSize(true)
        recyclerView_Following.adapter = followingAdapter
    }

    private fun showprogressBarFollowing(state: Boolean) {
        if (state) {
            progressBarFollowing.visibility = View.VISIBLE
        } else {
            progressBarFollowing.visibility = View.INVISIBLE
        }
    }
}