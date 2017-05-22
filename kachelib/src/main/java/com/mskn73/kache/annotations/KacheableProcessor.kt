package com.mskn73.kache.annotations

/**
 * Created by moskis on 05/05/2017.
 */

class KacheableProcessor {
    fun getExpiresTime(kacheableObject: Class<*>): Long {
        var expiresTime = Long.MAX_VALUE
        for (annotation in kacheableObject.declaredAnnotations) {
            if (annotation is KacheLife) {
                expiresTime = annotation.expiresTime
            }
        }
        return expiresTime
    }
}
