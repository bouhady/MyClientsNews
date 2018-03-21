package com.citi.myclientsnews.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by yb34982 on 21/03/2018.
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Timber.Tree providesTimber() {
        return
                new Timber.DebugTree() ;

    }

}
