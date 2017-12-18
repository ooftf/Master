package com.master.kit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by 99474 on 2017/12/7 0007.
 */

public class MyTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return new Adapter<T>();
    }

    public static class Adapter<T> extends TypeAdapter<T> {


        @Override
        public void write(JsonWriter out, T value) throws IOException {

        }

        @Override
        public T read(JsonReader in) throws IOException {

            return null;
        }
    }
}
