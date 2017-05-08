package com.mskn73.kache.io

import com.mskn73.kache.Kacheable
import com.mskn73.kache.annotations.KacheLife
import com.mskn73.kache.annotations.KacheableProcessor
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class KacheableProcessorTest {

    private var kacheableProcessor = KacheableProcessor()

    @Test
    fun shouldGetExpirationTimeFromAnnotation() {

        val expiresTime = kacheableProcessor.getExpiresTime(TestObjectWithAnnotation::class.java)

        expiresTime shouldEqualTo 123456
    }

    @Test
    fun shouldGetDefaultExpirationTimeWithoutAnnotation() {

        val expiresTime = kacheableProcessor.getExpiresTime(TestObjectWithoutAnnotation::class.java)

        expiresTime shouldEqualTo Long.MAX_VALUE
    }

    @KacheLife(expiresTime = 123456)
    class TestObjectWithAnnotation : Kacheable {
        override val key: String
            get() = "FAKE_KEY"
    }
    
    class TestObjectWithoutAnnotation : Kacheable {
        override val key: String
            get() = "FAKE_KEY"
    }
}

