package org.telekinesis.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.telekinesis.client.Window;
import org.telekinesis.telekinesis.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WindowsFragment extends Fragment {
    private static String TAG = "WindowsFragment";

    TextView textView;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_windows, container, false);
        textView = root.findViewById(R.id.textView);
        imageView = root.findViewById(R.id.imageView);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TelekinesisApplication.service.getAllWindows().enqueue(new Callback<List<Window>>() {
            @Override
            public void onResponse(Call<List<Window>> call, Response<List<Window>> response) {
                String text = "";
                for (Window window : response.body()) {
                    Log.d(TAG, window.title);
                    Picasso.get().load(window.iconLink).into(imageView);
                    text += window.title + "\n";
                }
                textView.setText(text);
            }

            @Override
            public void onFailure(Call<List<Window>> call, Throwable throwable) {
                Log.w(TAG, "getAllWindows call failed ", throwable);
            }
        });
    }
}
