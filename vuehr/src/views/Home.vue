<template>
	<div>
		<el-container>
			<el-header class="homeHeader">
				<div class="title">VHR</div>
				<el-dropdown class="userInfo" @command="commandHandler">
                    <span class="el-dropdown-link" >
                        {{ user.name }}<i><img :src="user.userface" alt=""></i>
                    </span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
						<el-dropdown-item command="setting">设置</el-dropdown-item>
						<el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-header>
			<el-container>
				<div class="left-menu">
					<el-aside width="210px" >
						<el-menu router unique-opened >
							<el-submenu :index="index.toString()" v-for="(item,index) in routers" v-if="!item.hidden">
								<template slot="title">
									<i :class="item.iconCls" style="color: #2c3e50;margin-right: 10px" ></i>
									<span>{{item.name}}</span>
								</template>
								<el-menu-item :index="child.path" v-for="(child,indexj) in item.children">{{child.name}}</el-menu-item>
							</el-submenu>

						</el-menu>
					</el-aside>
				</div>

				<el-main>
					<el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
						<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
						<el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
					</el-breadcrumb>
					<welcome v-if="this.$router.currentRoute.path=='/home'"></welcome>
					<router-view class="homeRouterView" />
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
import Welcome from "@/components/Welcome";
export default {
	name: "Home",
	data() {
		return {
			user: JSON.parse(window.sessionStorage.getItem("user")),

		}
	},
	computed:{
		routers(){
			return this.$store.state.routers;
		}
	},
	methods:{
		commandHandler(cmd){
			if(cmd == 'logout'){
				this.$confirm('此操作将注销登录, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.getRequest('/logout')
					//清空数据
					window.sessionStorage.removeItem("user")
					this.$store.commit('initRoutes',[])
					//跳转到登录页面
					this.$router.replace('/')
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消'
					});
				});
			}
			else if(cmd == 'userInfo'){
				this.$router.replace('/userInfo')
			}

		}
	},
	components:{
		Welcome
	}
}
</script>

<style>
/*Vue elementui中侧边栏滚动条隐藏*/
.el-aside::-webkit-scrollbar {
	display: none;
}
.left-menu{
	/*解决element-ui侧边菜单栏高度占满屏幕问题*/
	height: 100vh;
	border-right:1px solid #DCDFE6;
}
.homeHeader {
	background-color: black;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0px 15px;
	box-sizing: border-box;
}

.homeHeader .title {
	font-size: 30px;
	font-family: 华文行楷;
	color: white;
}

.homeHeader .userInfo{
	cursor: pointer;
}
.el-dropdown-link{
	display: flex;
	align-items: center;
	color: white;
}
.el-dropdown-link img{
	width: 48px;
	height: 48px;
	margin-left: 8px;
	border-radius: 24px;
}

.el-menu {
	border-right:0!important;
}


.homeRouterView{
	padding-top: 20px;
}
</style>