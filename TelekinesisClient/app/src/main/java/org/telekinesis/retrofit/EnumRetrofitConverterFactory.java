package org.telekinesis.retrofit;

import androidx.annotation.Nullable;

import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class EnumRetrofitConverterFactory extends Converter.Factory {
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Converter<?, String> converter = null;
        if (type instanceof Class && ((Class<?>)type).isEnum()) {
            converter = new Converter<Object, String>() {
                @Nullable
                @Override
                public String convert(Object value) throws IOException {
                    return EnumUtils.GetSerializedNameValue((Enum) value);
                }
            };
        }
        return converter;
    }
}