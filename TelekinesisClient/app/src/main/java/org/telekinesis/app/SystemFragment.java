package org.telekinesis.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.telekinesis.client.Window;
import org.telekinesis.telekinesis.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SystemFragment extends Fragment implements Callback<ResponseBody> {
    private static String TAG = "SystemFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_system, container, false);
        Button shutdownButton = root.findViewById(R.id.buttonShutdown);
        Button restartButton = root.findViewById(R.id.buttonRestart);
        Button lockScreenButton = root.findViewById(R.id.buttonLockScreen);
        Button abortShutdownButton = root.findViewById(R.id.buttonAbortShutdown);
        shutdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelekinesisApplication.service.shutdown().enqueue(SystemFragment.this);
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelekinesisApplication.service.reboot().enqueue(SystemFragment.this);
            }
        });
        lockScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelekinesisApplication.service.lockScreen().enqueue(SystemFragment.this);
            }
        });
        abortShutdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelekinesisApplication.service.abortShutdown().enqueue(SystemFragment.this);
            }
        });
        return root;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.w(TAG, "Failed to send system command", t);
    }
}
