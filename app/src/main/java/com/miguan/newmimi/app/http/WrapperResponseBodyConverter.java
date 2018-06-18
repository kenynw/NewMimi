package com.miguan.newmimi.app.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class WrapperResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final String TAG = WrapperResponseBodyConverter.class.getSimpleName();

    private final Type mType;

    WrapperResponseBodyConverter(Type type) {
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();

        try {
            if (TextUtils.isEmpty(result)) {
                return new Gson().fromJson(result, mType);
            }

            Object object = new JSONTokener(result).nextValue();
            if (object instanceof JSONObject) {
                JSONObject data = (JSONObject) object;
//                LogUtil.json(data.toString());

                if (data.has("code")) {
                    int code = data.getInt("code");
                    if (code != HttpResponseCode.SUCCESS) {
                        if (data.has("message")) {
                            throw new ApiException(code, data.getString("message"));
                        }  else {
                            throw new ApiException(code, "网络错误");
                        }
                    }

                    if (data.has("data") && !data.isNull("data")) {
                        result = data.opt("data").toString();
                    }
                }
            }
            return new Gson().fromJson(result, mType);
        } catch (JSONException | JsonSyntaxException e) {
            throw new ApiException(HttpResponseCode.ERROR_PARSE_FAIL, "数据解析错误");
        }
    }

}
