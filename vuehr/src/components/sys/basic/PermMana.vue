<template>
	<div>
		<div class="permManaTool">
			<el-input placeholder="请输入角色英文名" v-model="role.name">
				<template slot="prepend">ROLE_</template>
			</el-input>
			<el-input placeholder="请输入角色中文名" v-model="role.nameZh"></el-input>
			<el-button type="primary" icon="el-icon-plus">添加角色</el-button>
		</div>
		<div style="margin-top: 10px;width: 700px">
			<el-collapse accordion @change="change" v-model="activeName" >
				<el-collapse-item v-for="(role,index) in this.roles" :title="role.nameZh" :name="role.id" :key="index">
					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span>可访问的资源</span>
							<el-button style="float: right; padding: 3px 0;color: red" type="text"
							           icon="el-icon-delete"></el-button>
						</div>
						<div>
							<el-tree :data="allMenus" :props="defaultProps"
							         ref="tree"
							         show-checkbox
							         node-key="id"
									:default-checked-keys="selectedMenus">
							</el-tree>
							<div style="display: flex;justify-content: flex-end">
								<el-button size="small" @click="cancelUpdate">取消修改</el-button>
								<el-button type="primary" size="small" @click="doUpdate(role.id,index)">确认修改</el-button>
							</div>
						</div>
					</el-card>
				</el-collapse-item>
			</el-collapse>
		</div>
	</div>
</template>

<script>
import {getRequest, putRequest} from "@/utils/api";

export default {
	name: "PermMana",
	data() {
		return {
			role: {
				name: '',
				nameZh: ''
			},
			roles: [],
			allMenus: [],
			defaultProps: {
				children: 'children',
				label: 'name'
			},
			selectedMenus:[],
			activeName:-1,
		}
	},
	mounted() {
		this.initRoles();
	},
	methods: {
		cancelUpdate(){
			this.activeName = -1;
		},
		doUpdate(rid,index){
			let tree = this.$refs.tree[index];
			let selectedKeys = tree.getCheckedKeys(true)
			let url = '/system/basic/perm/?rid=' + rid;
			selectedKeys.forEach(key=>{
				url += '&mids=' + key;
			})
			putRequest(url).then(resp=>{
				if(resp){
					this.initRoles();
					this.activeName = -1;
				}
			})
		},
		initSelectMenus(rid){
			getRequest("/system/basic/perm/mids/" + rid).then(resp=>{
				if(resp){
					this.selectedMenus  = resp;
				}
			})
		},
		initRoles() {
			getRequest("/system/basic/perm/").then(resp => {
				if (resp) {
					this.roles = resp
				}
			})
		},
		change(rid) {
			if (rid) {
				this.initAllMenus();
				this.initSelectMenus(rid);
			}
		},
		initAllMenus() {
			getRequest("/system/basic/perm/menus").then(resp => {
				if (resp) {
					this.allMenus = resp;
				}
			})
		}

	}
}
</script>

<style>
.permManaTool {
	display: flex;
}

.permManaTool .el-input {
	width: 300px;
	margin-right: 8px;
}
</style>