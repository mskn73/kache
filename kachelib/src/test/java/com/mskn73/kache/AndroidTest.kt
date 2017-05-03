package com.mskn73.kache

import android.app.Application
import android.content.Context
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.File


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,
    application = AndroidTest.ApplicationStub::class,
    sdk = intArrayOf(21))
abstract class AndroidTest {

  fun context(): Context {
    return RuntimeEnvironment.application
  }

  fun cacheDir(): File {
    return context().cacheDir
  }

  internal class ApplicationStub : Application()
}
