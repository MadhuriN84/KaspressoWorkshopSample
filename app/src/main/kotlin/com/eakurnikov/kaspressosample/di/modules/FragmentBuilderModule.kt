package com.eakurnikov.kaspressosample.di.modules

import dagger.Module
import dagger.android.AndroidInjectionModule

/**
 * Created by eakurnikov on 2019-10-07
 */
@Module(includes = [AndroidInjectionModule::class])
interface FragmentBuilderModule {

//    @FragmentScope
//    @ContributesAndroidInjector
//    fun bindSomeFragment(): SomeFragment
}