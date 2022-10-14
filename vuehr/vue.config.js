//代理对象
let proxyObj = {};
proxyObj['/']={
  ws:false,
  target:'http://localhost:8080',
  changeOrigin:true,
  PathRewrite:{
    '^/':''
  }
}
module.exports = {
  transpileDependencies: true,
  devServer:{
    host:'localhost',
    port:8081,
    proxy:proxyObj

  }
}