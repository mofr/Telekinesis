package org.telekinesis.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface TelekinesisService {
    @GET("/api/windows")
    Call<List<Window>> getAllWindows();

    @POST("/api/windows/{id}/activate")
    Call<ResponseBody> activateWindow(@Path("id") String id);

    @POST("/api/windows/{id}/close")
    Call<ResponseBody> closeWindow(@Path("id") String id);

    @POST("/api/keyboard/press")
    Call<ResponseBody> pressKeyboard(@Query("key") List<KeyboardKey> keys);

    @POST("/api/system/lock-screen")
    Call<ResponseBody> lockScreen();

    @POST("/api/system/reboot")
    Call<ResponseBody> reboot();

    @POST("/api/system/shutdown")
    Call<ResponseBody> shutdown();

    @POST("/api/system/abort-shutdown")
    Call<ResponseBody> abortShutdown();
}
