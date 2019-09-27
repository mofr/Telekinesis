package org.telekinesis.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.telekinesis.client.TelekinesisService;
import org.telekinesis.client.Window;
import org.telekinesis.retrofit.EnumRetrofitConverterFactory;
import org.telekinesis.telekinesis.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TelekinesisDiscovery.Listener {
    private static String TAG = "MainActivity";

    TelekinesisDiscovery telekinesisDiscovery;
    Retrofit retrofit;
    TelekinesisService telekinesisService;

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        telekinesisDiscovery = new TelekinesisDiscovery(getApplicationContext(), this);
        telekinesisDiscovery.discoverServices();
    }

    @Override
    public void onServiceFound(TelekinesisServiceInfo serviceInfo) {
        retrofit = new Retrofit.Builder()
                .baseUrl(serviceInfo.getURL())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new EnumRetrofitConverterFactory())
                .build();

        telekinesisService = retrofit.create(TelekinesisService.class);
        telekinesisService.getAllWindows().enqueue(new Callback<List<Window>>() {
            @Override
            public void onResponse(Call<List<Window>> call, Response<List<Window>> response) {
                String text = "";
                for (Window window : response.body()) {
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
