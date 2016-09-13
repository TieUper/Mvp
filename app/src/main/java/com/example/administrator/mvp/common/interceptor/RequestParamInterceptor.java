package com.example.administrator.mvp.common.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求参数 拦截器
 * Created by wutong on 2016/3/8 0008.
 */
public class RequestParamInterceptor implements Interceptor {
    private static final String TAG = "RequestParamInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d("RequestParamInterceptor", "RequestParamInterceptor_" + System.currentTimeMillis());
//        Request oldRequest = chain.request();
//
//
//
//        // 添加新的参数
//        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
//                .newBuilder()
//                .scheme(oldRequest.url().scheme())
//                .host(oldRequest.url().host());
                //添加UUID
                //.addQueryParameter(Constant.UUID, UUIDUtil.getUniqueID(ClientApp.getInstance()));

//        String token = SharePreferencesUtil.getString(
//                ClientApp.getInstance(), Constant.USER_TOKEN, Constant.USER_TOKEN, "");
//        if (!TextUtils.isEmpty(token)) {
//            //添加TOKEN
//            authorizedUrlBuilder.addQueryParameter(Constant.USER_TOKEN, token);
//        }

        Request original = chain.request();

        //请求定制：添加请求头
        Request.Builder requestBuilder = original.newBuilder();

        //请求体定制：统一添加token参数
        if(original.body() instanceof FormBody){
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oidFormBody = (FormBody) original.body();
            for (int i = 0;i<oidFormBody.size();i++){
                newFormBody.addEncoded(oidFormBody.encodedName(i),oidFormBody.encodedValue(i));
            }
            newFormBody.add("token","123456");
            requestBuilder.method(original.method(),newFormBody.build());
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);


//        //多语言
//        String language = SharePreferencesUtil.getString(
//                ClientApp.getInstance(), Constant.ACCEPT_LANGUAGE, Constant.ACCEPT_LANGUAGE, Constant.ACCEPT_LANGUAGE_JIANTI);

//        FormBody formBody = (FormBody) oldRequest.body();
//        FormBody formBody2 = new FormBody.Builder().add(Constant.USER_TOKEN, token).build();

//        /**/FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
//        formEncodingBuilder.add(Constant.USER_TOKEN,token)
//                .add(Constant.UUID,UUIDUtil.getUniqueID(ClientApp.getInstance()))
//                .add("Accept-Language",language).build();
//        FormBody build = new FormBody.Builder().add(Constant.USER_TOKEN, token)
//                .add(Constant.UUID, UUIDUtil.getUniqueID(ClientApp.getInstance()))
//                .add("Accept-Language", language).build();

        // 新的请求
//        Request newRequest = oldRequest.newBuilder()
//
//                .method(oldRequest.method(), oldRequest.body())
//                .url(authorizedUrlBuilder.build())
//               // .post(build)
//                //简繁体
////                .header("Accept-Language", language)
//                .build();
//
//       // RFLog.d(TAG, newRequest.toString());
//        //响应信息拦截
//        Response response = chain.proceed(newRequest);
////        Log.d("response=", "response==" + response.body().string());
//        return response;

    }
}
