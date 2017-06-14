package com.fernandocejas.android.sample

import com.mskn73.kache.AndroidTest
import com.mskn73.kache.Kache
import com.mskn73.kache.Kacheable
import com.mskn73.kache.annotations.KacheLife
import org.amshove.kluent.Verify
import org.amshove.kluent.called
import org.amshove.kluent.mock
import org.amshove.kluent.on
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.that
import org.amshove.kluent.was
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.test.assertFails
import kotlin.test.assertTrue

/**
 * Created by francisco.hernandez on 22/5/17.
 */

class KacheTest : AndroidTest() {

  val kache = Kache(context(), cacheDir().path)

  @Before
  fun setUp() {

  }

  @After
  fun tearDown() {
    val dir = cacheDir()
    if (dir.isDirectory()) {
      val children = dir.list()
      for (i in children.indices) {
        File(dir, children[i]).delete()
      }
    }
  }

  @Test
  fun shouldBeCached() {
    val key = "key1"
    val dummy = DummyObject(key, "content")

    kache.put(dummy)
    val expectedContent = kache.isCached(key)

    expectedContent shouldEqualTo true
  }

  @Test
  fun shouldNotBeCached() {
    val key1 = "key1"
    val key2 = "key2"
    val dummy = DummyObject(key1, "content")

    kache.put(dummy)
    val expectedContent = kache.isCached(key2)

    expectedContent shouldEqualTo false
  }

  @Test
  fun shouldGetData() {
    val key = "key1"
    val data = "content"
    val dummy = DummyObject(key, data)

    kache.put(dummy)
    val expectContent = kache.get(DummyObject::class.java, key)

    if (expectContent is DummyObject) {
      expectContent.data shouldEqualTo data
    } else {
      assertTrue(expectContent is DummyObject)
    }
  }

  @Test
  fun shouldNotBeExpired() {
    val key = "key1"
    val dummy = DummyObject(key, "content")

    kache.put(dummy)
    Thread.sleep(1000)
    val expectedContent = kache.isExpired(key, dummy.javaClass)

    expectedContent shouldEqualTo false
  }

  @Test
  fun shouldBeExpired() {
    val key = "key1"
    val dummy = DummyObject(key, "content")

    kache.put(dummy)
    Thread.sleep(1000)
    val expectedContent = kache.isExpired(key, dummy.javaClass)

    assertFails({ Verify on kache that kache.evict(key) was called })
    expectedContent shouldEqualTo false
  }

  @Test
  fun shouldBeEvicted() {
    val key = "key1"
    val dummy = DummyObject(key, "content")

    kache.put(dummy)
    kache.evict(key)
    val expectedContent = kache.isCached(key)

    expectedContent shouldEqualTo false
  }

  @Test
  fun shouldBeEvictAll() {
    val key1 = "key1"
    val dummy1 = DummyObject(key1, "content")
    val key2 = "key2"
    val dummy2 = DummyObject(key2, "content")

    kache.put(dummy1)
    kache.put(dummy2)
    kache.evictAll()
    val expectedContent = kache.isCached(key1) || kache.isCached(key2)

    expectedContent shouldEqualTo false
  }

  class DummyObject(override val key: String, val data: String) : Kacheable

  @KacheLife(expiresTime = 1)
  class DummyObject1(override val key: String, val data: String) : Kacheable
}