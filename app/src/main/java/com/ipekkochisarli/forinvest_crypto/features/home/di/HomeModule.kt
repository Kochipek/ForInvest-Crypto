package com.ipekkochisarli.forinvest_crypto.features.home.di

import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.HomePageApi
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.HomeRepositoryImpl
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {

    @Provides
    fun provideHomeApiService(retrofit: Retrofit): HomePageApi {
        return retrofit.create(HomePageApi::class.java)
    }

    @Provides
    fun provideHomeRepository(
        homePageApi: HomePageApi,
    ): HomeRepository {
        return HomeRepositoryImpl(homePageApi)
    }
}
