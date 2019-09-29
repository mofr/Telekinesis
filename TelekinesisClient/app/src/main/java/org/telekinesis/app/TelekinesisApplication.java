package org.telekinesis.app;

import android.app.Application;

import org.telekinesis.client.TelekinesisService;
import org.telekinesis.retrofit.EnumRetrofitConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelekinesisApplication extends Application implements TelekinesisDiscovery.Listener {
    public static TelekinesisDiscovery discovery;
    public static TelekinesisService service;

    @Override
    public void onCreate() {
        super.onCreate();
        discovery = new TelekinesisDiscovery(getApplicationContext(), this);
        discovery.discoverServices();
    }

    @Override
    public void onServiceFound(TelekinesisServiceInfo serviceInfo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serviceInfo.getURL())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new EnumRetrofitConverterFactory())
                .build();

        service = retrofit.create(TelekinesisService.class);
    }
}
