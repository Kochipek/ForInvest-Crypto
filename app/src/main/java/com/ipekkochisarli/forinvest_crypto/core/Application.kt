package com.ipekkochisarli.forinvest_crypto.core

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.ipekkochisarli.forinvest_crypto.BuildConfig
import dagger.hilt.android.HiltAndroidApp

import com.facebook.soloader.SoLoader
import com.ipekkochisarli.forinvest_crypto.core.data.remote.FlipperHolder

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(FlipperHolder.networkFlipperPlugin)
            client.start()
        }
    }
}