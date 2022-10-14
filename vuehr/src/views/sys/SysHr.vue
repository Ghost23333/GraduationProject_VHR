<template>
	<div>
		<div style="margin-top: 10px;display: flex;justify-content: center">
			<el-input prefix-icon="el-icon-search" v-model="keywords"
			          placeholder="通过用户用搜索用户..."
			          @keydown.enter.native="doSearch"
			          style="width: 400px;margin-right: 10px"></el-input>
			<el-button icon="el-icon-search" type="primary" @click="doSearch">搜索</el-button>
		</div>
		<div class="hr-container">
			<el-card class="box-card" v-for="(hr,index) in hrs" :key="index" v-loading="loading">
				<div slot="header" class="clearfix">
					<span>{{ hr.name }}</span>
					<el-button style="float: right; padding: 3px 0;color: red" type="text"
					           icon="el-icon-delete" @click="deleteHr(hr)"></el-button>
				</div>
				<div>
					<div style="width: 100%;display: flex;justify-content: center">
						<img :src="hr.userface" :alt="hr.name" :title="hr.name" class="img-userface">
					</div>
					<div class="userinfo-container">
						<div>用户名: {{ hr.name }}</div>
						<div>手机号码: {{ hr.phone }}</div>
						<div>电话号码: {{ hr.telephone }}</div>
						<div>地址: {{ hr.address }}</div>
						<div>用户状态:
							<el-switch
									v-model="hr.enabled"
									active-color="#13ce66"
									inactive-color="#ff4949"
									active-text="启用"
									inactive-text="禁用"
									@change="enabledChange(hr)">
							</el-switch>
						</div>
						<div>
							用户角色:
							<el-tag style="margin-right: 4px" type="success" v-for="(role,indexj) in hr.roles"
							        :key="indexj">{{ role.nameZh }}
							</el-tag>

							<el-popover
									placement="right"
									title="角色列表"
									width="200"
									trigger="click"
									@hide="hidePop(hr)"
									@show="showPop(hr)"
							>
								<el-select multiple v-model="selectedRoles" placeholder="请选择">
									<el-option
											v-for="(role,indexk) in allRoles"
											:key="indexk"
											:label="role.nameZh"
											:value="role.id">
									</el-option>
								</el-select>
								<el-button slot="reference" type="text" icon="el-icon-more"></el-button>
							</el-popover>

						</div>
						<div>备注: {{ hr.remark }}</div>
					</div>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
import {deleteRequest, getRequest, putRequest} from "@/utils/api";

export default {
	name: "SysHr",
	data() {
		return {
			loading:false,
			keywords: '',
			hrs: [],
			selectedRoles: [],
			allRoles: []
		}
	},
	mounted() {
		this.initHrs();
	},
	methods: {
		deleteHr(hr){
			this.$confirm('此操作将永久删除该HR, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				deleteRequest("/system/hr/"+hr.id).then(resp=>{
					if(resp){
						this.initHrs();
					}
				})
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消删除'
				});
			});

		},
		doSearch() {
			this.initHrs();
			console.log("aaa")
		},
		hidePop(hr) {
			let roles = hr.roles;
			let rolesArr = [];
			roles.forEach(r => {
				rolesArr.push(r.id)
			})
			if (rolesArr.sort().toString() !== this.selectedRoles.sort().toString()) {
				let url = '/system/hr/role' + '?hrid=' + hr.id;
				this.selectedRoles.forEach(rid => {
					url += '&rids=' + rid;
				})
				putRequest(url).then(resp => {
					this.initHrs();
				})
			}


		},
		showPop(hr) {
			this.initAllRoles();
			let roles = hr.roles;
			this.selectedRoles = [];
			roles.forEach(r => {
				this.selectedRoles.push(r.id)
			})
		},
		initAllRoles() {
			getRequest("/system/hr/roles").then(resp => {
				if (resp) {
					this.allRoles = resp
				}
			})
		},
		enabledChange(hr) {
			delete hr.roles
			putRequest("/system/hr/", hr).then(resp => {
				if (resp) {
					this.initHrs();
				}
			})
		},
		initHrs() {
			this.loading = true;
			getRequest("/system/hr/?keywords=" + this.keywords).then(resp => {
				this.loading = false;
				if (resp) {
					this.hrs = resp;
				}
			})
		}
	}
}
</script>

<style>
.userinfo-container div {
	font-size: 12px;
	color: #409EFF;
}

.userinfo-container {
	margin-top: 20px;

}

.hr-container {
	margin-top: 10px;
	display: flex;
	flex-wrap: wrap;
	justify-content: space-around;
}

.box-card {
	width: 350px;
	margin-bottom: 20px;
}

.img-userface {
	width: 72px;
	height: 72px;
	border-radius: 72px;
}
</style>