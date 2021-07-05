package com.globant.videoplayerproject.di

import com.globant.videoplayerproject.api.ApiService
import com.globant.videoplayerproject.api.ApiServiceToken
import com.globant.videoplayerproject.api.ApiStreamUrlVideo
import com.globant.videoplayerproject.utils.BASE_URL
import com.globant.videoplayerproject.utils.BASE_URL_STREAM_VIDEOS
import com.globant.videoplayerproject.utils.BASE_URL_TOKEN
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Modules {

    //Retrofit
    @Singleton
    @Provides @ApiServiceStream(TypeEnum.RETROFIT)
    fun provideApiServiceStreamRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL_STREAM_VIDEOS)
            .client(client)
            .build()
    }

    @Singleton
    @Provides @com.globant.videoplayerproject.di.ApiServiceToken(TypeEnum.RETROFIT)
    fun provideApiServiceTokenRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL_TOKEN)
            .client(client)
            .build()
    }

    @Singleton
    @Provides @com.globant.videoplayerproject.di.ApiService(TypeEnum.RETROFIT)
    fun provideApiServiceRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    //OkHttp
    @Provides
    fun provideHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
    }

    //Interceptor
    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    //Gson
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    //Repositories
    @Provides @com.globant.videoplayerproject.di.ApiService(TypeEnum.APISERVICE)
    fun createApiService(@com.globant.videoplayerproject.di.ApiService(TypeEnum.RETROFIT) retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides @com.globant.videoplayerproject.di.ApiServiceToken(TypeEnum.APISERVICE)
    fun createApiServiceToken(@com.globant.videoplayerproject.di.ApiServiceToken(TypeEnum.RETROFIT) retrofitToken: Retrofit): ApiServiceToken {
        return retrofitToken.create(ApiServiceToken::class.java)
    }

    @Provides @ApiServiceStream(TypeEnum.APISERVICE)
    fun createApiServiceStreams(@ApiServiceStream(TypeEnum.RETROFIT) retrofitStream: Retrofit): ApiStreamUrlVideo {
        return retrofitStream.create(ApiStreamUrlVideo::class.java)
    }
}