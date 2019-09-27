package org.telekinesis.app;
import android.content.Context;
import android.net.nsd.NsdServiceInfo;
import android.net.nsd.NsdManager;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class TelekinesisDiscovery {
    public static final String TAG = "TelekinesisDiscovery";
    public static final String SERVICE_TYPE = "_http._tcp.";
    public static final String SERVICE_NAME = "TelekinesisServer";

    public interface Listener {
        void onServiceFound(TelekinesisServiceInfo serviceInfo);
    }

    private NsdManager nsdManager;
    private NsdManager.ResolveListener resolveListener;
    private NsdManager.DiscoveryListener discoveryListener;
    private Listener listener;

    public TelekinesisDiscovery(Context context, Listener listener) {
        nsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
        this.listener = listener;
    }

    public void discoverServices() {
        stopDiscovery();  // Cancel any existing discovery request
        initializeResolveListener();
        initializeDiscoveryListener();
        nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
    }

    public void stopDiscovery() {
        if (discoveryListener != null) {
            nsdManager.stopServiceDiscovery(discoveryListener);
        }
    }

    private void initializeDiscoveryListener() {
        discoveryListener = new NsdManager.DiscoveryListener() {
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "Service discovery started");
            }
            @Override
            public void onServiceFound(NsdServiceInfo service) {
                Log.d(TAG, "Service discovery success: " + service);
                if (!service.getServiceType().equals(SERVICE_TYPE)) {
                    Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
                } else if (service.getServiceName().equals(SERVICE_NAME)) {
                    nsdManager.resolveService(service, resolveListener);
                }
            }
            @Override
            public void onServiceLost(NsdServiceInfo service) {
                Log.e(TAG, "service lost" + service);
            }
            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }
            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery start failed: Error code: " + errorCode);
            }
            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery stop failed: Error code: " + errorCode);
            }
        };
    }

    private void initializeResolveListener() {
        resolveListener = new NsdManager.ResolveListener() {
            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Log.e(TAG, "Resolve failed: " + errorCode);
            }
            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                try {
                    URL url = new URL("http", serviceInfo.getHost().getHostAddress(), serviceInfo.getPort(), "/");
                    listener.onServiceFound(new TelekinesisServiceInfo(url));
                } catch (MalformedURLException e) {
                    Log.w(TAG, e);
                }
                Log.d(TAG, "Resolve Succeeded: " + serviceInfo);
                Log.d(TAG, "Host: " + serviceInfo.getHost());
                Log.d(TAG, "Port: " + serviceInfo.getPort());
                for (Map.Entry<String, byte[]> entry : serviceInfo.getAttributes().entrySet()) {
                    String value = new String(entry.getValue());
                    Log.d(TAG, "Attribute " + entry.getKey() + ": " + value);
                }
            }
        };
    }
}