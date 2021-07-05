package com.globant.videoplayerproject.di

import java.lang.annotation.Documented
import javax.inject.Qualifier

@Qualifier
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiServiceStream(val value: TypeEnum)