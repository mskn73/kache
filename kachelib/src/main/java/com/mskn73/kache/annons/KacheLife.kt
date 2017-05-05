package com.mskn73.kache.annons

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by moskis on 05/05/2017.
 */

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(RetentionPolicy.RUNTIME)
internal annotation class KacheLife(val expiresTime: Long)
