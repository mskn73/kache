package com.mskn73.kache.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mskn73.kache.Kache
import com.mskn73.kache.Kacheable
import com.mskn73.kache.annotations.KacheLife

class MainActivityKt : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val k = Kache(applicationContext, cacheDir.toString())
    k.put(MyObject("fake@mail.com"))
    val myKachedObject = k.get(MyObject::class.java, "fake@mail.com") as Kacheable

    Log.v("Obj", myKachedObject.key)
  }

  @KacheLife(expiresTime = (60 * 1000).toLong())
  internal inner class MyObject(override val key: String) : Kacheable
}
