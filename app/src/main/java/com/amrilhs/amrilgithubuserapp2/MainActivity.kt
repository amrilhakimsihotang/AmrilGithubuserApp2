package com.amrilhs.amrilgithubuserapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrilhs.amrilgithubuserapp2.model.UsersData
import com.amrilhs.amrilgithubuserapp2.adapter.UsersDataAdapter
import com.amrilhs.amrilgithubuserapp2.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private var listUsersData: ArrayList<UsersData> = ArrayList()
    private lateinit var usersDataAdapter: UsersDataAdapter
    private lateinit var userviewmodel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersDataAdapter = UsersDataAdapter(listUsersData)
        userviewmodel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        searchData()
        recyclerviewUser()
        showGetDataGit()
        showUserViewModel(usersDataAdapter)
    }

    private fun showProgressbar(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun recyclerviewUser() {
        recylerview_user.layoutManager= LinearLayoutManager(this)
        recylerview_user.setHasFixedSize(true)
        usersDataAdapter.notifyDataSetChanged()
        recylerview_user.adapter=usersDataAdapter
    }

    private fun showGetDataGit() {
        userviewmodel.getGitDataUser(applicationContext)
        showProgressbar(true)

    }

    private fun showUserViewModel(usersDataAdapter: UsersDataAdapter) {
        userviewmodel.getAllUsers().observe(this, Observer { listUsers ->
            if (listUsers != null) {
                usersDataAdapter.setData(listUsers)
                showProgressbar(false)
            }
        })
    }

    private fun searchData() {
        search_user.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    listUsersData.clear()
                    recyclerviewUser()
                    userviewmodel.getUserSearch(query, applicationContext)
                    showProgressbar(true)
                    showUserViewModel(usersDataAdapter)
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            R.id.change_language -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
            R.id.exit_btn -> {
                finish()
            }
        }
    }
}