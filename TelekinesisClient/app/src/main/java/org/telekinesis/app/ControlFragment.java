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

import org.telekinesis.client.KeyboardKey;
import org.telekinesis.telekinesis.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControlFragment extends Fragment implements Callback<ResponseBody> {
    private static String TAG = "ControlFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_control, container, false);
        Button volumeUpButton = root.findViewById(R.id.buttonVolumeUp);
        Button volumeDownButton = root.findViewById(R.id.buttonVolumeDown);
        Button muteButton = root.findViewById(R.id.buttonMute);
        Button spaceButton = root.findViewById(R.id.buttonSpace);
        final Map<Button, List<KeyboardKey>> sequences = new HashMap<>();
        sequences.put(volumeUpButton, Arrays.asList(KeyboardKey.VOLUME_UP));
        sequences.put(volumeDownButton, Arrays.asList(KeyboardKey.VOLUME_DOWN));
        sequences.put(muteButton, Arrays.asList(KeyboardKey.VOLUME_MUTE));
        sequences.put(spaceButton, Arrays.asList(KeyboardKey.SPACE));
        for (Map.Entry<Button, List<KeyboardKey>> kv: sequences.entrySet()){
            final List<KeyboardKey> keys = kv.getValue();
            kv.getKey().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TelekinesisApplication.service.pressKeyboard(keys).enqueue(ControlFragment.this);
                }
            });
        }
        return root;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.w(TAG, "Failed to send control command", t);
    }
}
