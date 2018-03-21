package com.citi.myclientsnews.app;

import android.app.Application;

import com.citi.myclientsnews.network.NetworkModule;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by yb34982 on 21/03/2018.
 */

public class App extends Application {
    private AppComponent component;

    @Inject
    Timber.Tree timberTree;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
        component.inject(this);

        initLog();
    }

    private void initLog() {
        Timber.plant(timberTree);
    }

    public AppComponent getComponent() {
        return component;
    }
}