package com.globant.videoplayerproject.api

import com.globant.videoplayerproject.di.TypeEnum
import com.globant.videoplayerproject.utils.MAX_VALUE_GAMES
import javax.inject.Inject

class ApiServiceRepository @Inject constructor(@com.globant.videoplayerproject.di.ApiService(
    TypeEnum.APISERVICE) private val apiService: ApiService) {
    fun getTopGames(accessToken: String) = apiService.getTopGamesAsync(accessToken, MAX_VALUE_GAMES)
    fun getStreams(accessToken: String, gameId: String) =
        apiService.getTopStreamsAsync(accessToken, gameId)
}