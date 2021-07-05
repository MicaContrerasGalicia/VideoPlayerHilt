package com.globant.videoplayerproject.api

import com.globant.videoplayerproject.di.ApiServiceStream
import com.globant.videoplayerproject.di.TypeEnum
import javax.inject.Inject

class ApiStreamUrlVideoRepository @Inject constructor(@ApiServiceStream(TypeEnum.APISERVICE) private val apiStreamUrlVideo: ApiStreamUrlVideo) {
    fun getVideoUrl(url: String) = apiStreamUrlVideo.getVideoUrlAsync(url)
}