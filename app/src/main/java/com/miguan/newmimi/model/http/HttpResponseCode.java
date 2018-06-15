package com.miguan.newmimi.model.http;

/**
 * 作者: liyu on  2018/1/30 0030 19:39
 * 功能描述:请求返回码
 * 备注:
 */
public interface HttpResponseCode {

     int SUCCESS = 1;//请求成功
     int ERROR_UNKNOWN_HOST = -1001; // 网络不可用
     int ERROR_SOCKET_TIMEOUT = -1002;// 请求网络超时
     int ERROR_INTERNAL_SERVER = 500; // 服务器发生错误
     int ERROR_NOT_EXIST = 404; // 请求地址不存在
     int ERROR_REJECTED = 403; // 请求被服务器拒绝
     int ERROR_REDIRECTED = 307; // 请求被重定向到其他页面
     int ERROR_PARSE_FAIL = -1006;// 数据解析错误

     int ERROR_PARAM_ERROR = -1003;//网络不可用
     int ERROR_EXIST = -1004;//已关注过
     int ERROR_USER_NOT_EXIST = -1005;//用户不存在
     int ERROR_MOBILE_EXIT = -1007; // 手机号码已注册
}
