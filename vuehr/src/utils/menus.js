import {getRequest} from "@/utils/api";
import router from "@/router";
import store from "@/store";


export const initMenu = (router, store) => {
    if (store.state.routers.length > 0) {
        return;
    }
    getRequest("/system/config/menu").then(data => {
        if (data) {
            let fmtRouters = formatRouters(data);
            router.addRoutes(fmtRouters);
            store.commit('initRoutes',fmtRouters);
        }
    })
}


export const formatRouters = (routers) => {
    let fmtRouters = [];
    routers.forEach(router => {
        let {
            path,
            component,
            name,
            meta,
            iconCls,
            children
        } = router;
        //如果children存在 且不为null
        if (children && children instanceof Array) {
            //递归格式化children
            children = formatRouters(children);
        }
        let fmRouter = {
            path: path,
            name: name,
            iconCls: iconCls,
            meta: meta,
            children: children,
            component(resolve){
                //动态加载组件
                if(component.startsWith("Home")){
                    require(['../views/' + component + '.vue'],resolve);
                }else if(component.startsWith("Emp")) {
                    require(['../views/emp/' + component + '.vue'], resolve);
                } else if(component.startsWith("Per")){
                    require(['../views/per/' + component + '.vue'],resolve);
                }else if(component.startsWith("Sal")){
                    require(['../views/sal/' + component + '.vue'],resolve);
                }else if(component.startsWith("Sta")){
                    require(['../views/sta/' + component + '.vue'],resolve);
                }else if(component.startsWith("Sys")){
                    require(['../views/sys/' + component + '.vue'],resolve);
                }

            }
        }
        fmtRouters.push(fmRouter)
    })
    return fmtRouters;
}