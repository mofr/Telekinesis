package org.telekinesis.retrofit;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class EnumUtils {
    @Nullable
    static public <E extends Enum<E>> String GetSerializedNameValue(E e) {
        String value = null;
        try {
            value = e.getClass().getField(e.name()).getAnnotation(SerializedName.class).value();
        } catch (NoSuchFieldException exception) {
            exception.printStackTrace();
        }
        return value;
    }
}