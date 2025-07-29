package com.ipekkochisarli.forinvest_crypto.core.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.ipekkochisarli.forinvest_crypto.BuildConfig
import com.ipekkochisarli.forinvest_crypto.core.data.remote.FlipperHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkFlipperPlugin(): NetworkFlipperPlugin {
        return FlipperHolder.networkFlipperPlugin
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(networkFlipperPlugin: NetworkFlipperPlugin): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitCoinsApiService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_COINS)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}