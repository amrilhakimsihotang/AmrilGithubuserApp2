package com.amrilhs.amrilgithubuserapp2.utilities

import com.loopj.android.http.AsyncHttpClient
/*Display 10 users for page 1*/
const val BASE_URL = "https://api.github.com/users?per_page=10&page=1"

/*Token, auth agent,auth user*/
const val TOKEN = "token ea5a69bb8d8ea6e10056f84c000c3ec43604a0cf"

const val AUTH_AGENT = "Authorization"
const val AUTH_USER = "User-Agent"
const val REQ_ACCESS = "request"
val httpClient = AsyncHttpClient()

