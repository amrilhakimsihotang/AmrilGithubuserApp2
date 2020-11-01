package com.amrilhs.amrilgithubuserapp2.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amrilhs.amrilgithubuserapp2.fragment.FollowingFragment
import com.amrilhs.amrilgithubuserapp2.model.UsersFollowing
import com.amrilhs.amrilgithubuserapp2.utilities.*
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class UserFollowingViewModel : ViewModel() {
    private val listFollowing = ArrayList<UsersFollowing>()
    private val listFollowingMutable = MutableLiveData<ArrayList<UsersFollowing>>()

    fun getAllFollowing(): LiveData<ArrayList<UsersFollowing>> {
        return listFollowingMutable
    }

    fun getDataGit(context: Context, id: String) {
        val FollowingURL = "https://api.github.com/users/$id/following"
        httpClient.addHeader(AUTH_AGENT, TOKEN)
        httpClient.addHeader(AUTH_USER, REQ_ACCESS)
        httpClient.get(FollowingURL, object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                Log.d(FollowingFragment.TAG, result!!)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val usernameLogin = jsonObject.getString("login")
                        getUserDetail(usernameLogin, context)
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getUserDetail(usernameLogin: String, context: Context) {
        val urlGetDetail="https://api.github.com/users/$usernameLogin"
        httpClient.addHeader(AUTH_AGENT, TOKEN)
        httpClient.addHeader(AUTH_USER, REQ_ACCESS)
        httpClient.get(urlGetDetail, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                Log.d(FollowingFragment.TAG, result!!)
                try {
                    val jsonObject = JSONObject(result)
                    val usersFollowing = UsersFollowing()
                    usersFollowing.username = jsonObject.getString("login")
                    usersFollowing.name = jsonObject.getString("name")
                    usersFollowing.avatar = jsonObject.getString("avatar_url")
                    usersFollowing.company = jsonObject.getString("company")
                    usersFollowing.location = jsonObject.getString("location")
                    usersFollowing.repository = jsonObject.getString("public_repos")
                    usersFollowing.followers = jsonObject.getString("followers")
                    usersFollowing.following = jsonObject.getString("following")
                    listFollowing.add(usersFollowing)
                    listFollowingMutable.postValue(listFollowing)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
