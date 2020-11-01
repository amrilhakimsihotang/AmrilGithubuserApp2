package com.amrilhs.amrilgithubuserapp2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import com.amrilhs.amrilgithubuserapp2.adapter.ViewPagerAdapter
import com.amrilhs.amrilgithubuserapp2.model.UsersData


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
          viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
        } else {
          viewpager.layoutParams.height = resources.getDimension(R.dimen.height1).toInt()
        }

        if (supportActionBar != null) {
            supportActionBar?.title = "Detail Github user"
        }
        UsersData()
        viewPagerDetail()
    }

    private fun  UsersData() {
        val usersdata = intent.getParcelableExtra<UsersData>(EXTRA_DETAIL)
            Glide.with(this)
                    .load(usersdata!!.avatar)
                    .apply(RequestOptions().override(100,100))
                    .into(det_avatar)
             det_fullName.text =  usersdata.name
            det_username.text = getString(R.string.det_username, usersdata.username)
            det_repository.text = getString(R.string.repository,usersdata.repository)
            det_followers.text = getString(R.string.followers, usersdata.followers)
            det_following.text = getString(R.string.followings, usersdata.following)
            det_company.text= getString(R.string.company,usersdata.company)
            det_location.text=getString(R.string.location,usersdata.location)


    }
    private fun viewPagerDetail() {
    val viewpageradapter= ViewPagerAdapter(this,supportFragmentManager)
        viewpager.adapter= viewpageradapter
        tabs.setupWithViewPager(viewpager)
       supportActionBar?.elevation = 0f
 }
}


