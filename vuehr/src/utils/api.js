import axios from "axios";
import {Message} from 'element-ui';
import message from "element-ui/packages/message";
import router from "@/router";

/*响应拦截器*/
axios.interceptors.response.use(success => {
    //业务错误
    if (success.status && success.status == 200 && success.data.status == 500) {
        Message.error({message: success.data.msg})
        return;
    }
    if(success.data.msg){
        Message.success({message:success.data.msg})
    }
    return success.data
}, error => {
    var status = error.response.status
    if (status == 504 || status == 404) {
        Message.error({message: "找不到服务器!"})
    } else if (status == 403) {
        Message.error({message: "权限不足,请联系管理员"})
    } else if (status == 401) {
        Message.error({message: "尚未登录,请登录!"})
        router.replace("/")
    } else {
        if (error.response.data.msg) {
            Message.error({message: error.response.data.msg})
        } else {
            Message.error({message: "未知错误!"})
        }
    }
    return;
})

let base = '';

export const postKeyValueRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        transformRequest: [function (data) {
            let ret = '';
            for (let i in data) {
                ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
}
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    })
}
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params
    })
}
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        params: params
    })
}
export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        params: params
    })
}