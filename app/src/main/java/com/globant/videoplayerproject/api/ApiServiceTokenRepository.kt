package com.globant.videoplayerproject.api

import com.globant.videoplayerproject.di.TypeEnum
import com.globant.videoplayerproject.utils.CLIENT_ID
import com.globant.videoplayerproject.utils.CLIENT_SECRET
import com.globant.videoplayerproject.utils.GRANT_TYPE
import javax.inject.Inject

class ApiServiceTokenRepository @Inject constructor(@com.globant.videoplayerproject.di.ApiServiceToken(
    TypeEnum.APISERVICE) private val apiServiceToken: ApiServiceToken) {
    fun getTokens() = apiServiceToken.getAccessTokenAsync(CLIENT_ID, CLIENT_SECRET, GRANT_TYPE)
}