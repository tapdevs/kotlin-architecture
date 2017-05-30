package com.tapdevs.kotlin.injections.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Scope

/**
 * Created by  Jan Shair on 29/05/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class RuntimeScope
