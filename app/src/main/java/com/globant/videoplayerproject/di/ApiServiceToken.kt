package com.globant.videoplayerproject.di

import java.lang.annotation.Documented
import javax.inject.Qualifier

@Qualifier
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiServiceToken(val value: TypeEnum)