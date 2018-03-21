package com.citi.myclientsnews.app

import com.citi.myclientsnews.MainActivity
import com.citi.myclientsnews.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by yb34982 on 21/03/2018.
 */
@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        NetworkModule::class
//        ,RoomModule::class
    )
)
public interface AppComponent {

    abstract fun inject(app: App)
    fun inject(mainActivity: MainActivity)

}