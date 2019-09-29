package org.telekinesis.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.telekinesis.client.Window;
import org.telekinesis.telekinesis.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WindowsFragment extends Fragment {
    private static String TAG = "WindowsFragment";

    private RecyclerView recyclerView;
    private WindowsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_windows, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WindowsAdapter(new ArrayList<Window>());
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TelekinesisApplication.service.getAllWindows().enqueue(new Callback<List<Window>>() {
            @Override
            public void onResponse(Call<List<Window>> call, Response<List<Window>> response) {
                adapter.setWindows(response.body());
            }

            @Override
            public void onFailure(Call<List<Window>> call, Throwable throwable) {
                Log.w(TAG, "getAllWindows call failed ", throwable);
            }
        });
    }
}
