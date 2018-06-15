package com.miguan.newmimi.model.http;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class WrapperConverterFactory extends Converter.Factory {

    private final Gson gson;

    public static WrapperConverterFactory create() {
        return create(new Gson());
    }

    public static WrapperConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new WrapperConverterFactory(gson);
    }

    private WrapperConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        WrapperResponseBodyConverter converter = new WrapperResponseBodyConverter<>(type);
        return converter;
    }

}
