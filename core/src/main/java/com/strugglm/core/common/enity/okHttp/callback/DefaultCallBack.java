package com.strugglm.core.common.enity.okHttp.callback;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @description: 默认okHttp的CallBack方法
 * @author: hejiale
 * @create: 2019/11/01 00:54
 */
@Slf4j
public class DefaultCallBack implements Callback {

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        log.info("okHttp默认回调，请求失败");
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        log.info("okHttp默认回调，请求成功");
    }
}
