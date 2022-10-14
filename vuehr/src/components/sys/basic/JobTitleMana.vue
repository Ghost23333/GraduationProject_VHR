<template>
	<div>
		<div>
			<el-input v-model="jobLevel.name"
			          style="width: 300px"
			          prefix-icon="el-icon-plus"
			          placeholder="添加职称...">
			</el-input>
			<el-select v-model="jobLevel.titleLevel" placeholder="职称等级" style="margin-left: 5px;margin-right: 5px">
				<el-option
						v-for="item in titleLevels"
						:key="item"
						:label="item"
						:value="item">
				</el-option>
			</el-select>
			<el-button icon="el-icon-plus" type="primary" @click="addJobLevel">添加</el-button>

		</div>
		<div style="margin-top: 10px;">
			<el-table
					v-loading="loading"
					:data="jobLevels"
					border
					stripe
					style="width: 100%">
				<el-table-column
						prop="id"
						label="编号"
						width="55">
				</el-table-column>
				<el-table-column
						prop="name"
						label="职称名称"
						width="150">
				</el-table-column>
				<el-table-column
						prop="titleLevel"
						label="职称级别"
						width="150">
				</el-table-column>
				<el-table-column
						prop="createDate"
						label="创建时间"
						width="220">
				</el-table-column>
				<el-table-column
						label="是否启用"
						width="140">
					<template slot-scope="scope">
						<el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
						<el-tag type="danger" v-else>未启用</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button
								size="mini"
								@click="showEditView(scope.row)">编辑
						</el-button>
						<el-button
								size="mini"
								type="danger"
								@click="handleDelete(scope.row)">删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
		<el-dialog
				title="修改职称"
				:visible.sync="dialogVisible"
				width="30%">
			<div>
				<table>
					<tr>
						<td>
							<el-tag>职称名</el-tag>
						</td>
						<td>
							<el-input size="small" v-model="updateJobLevel.name"></el-input>
						</td>
					</tr>
					<tr>
						<td>
							<el-tag>职称级别</el-tag>
						</td>
						<td>
							<el-select size="small" v-model="updateJobLevel.titleLevel" placeholder="职称等级" style="margin-left: 5px;margin-right: 5px">
								<el-option
										v-for="item in titleLevels"
										:key="item"
										:label="item"
										:value="item">
								</el-option>
							</el-select>
						</td>
					</tr>
					<tr>
						<td>
							<el-tag>是否启用</el-tag>
						</td>
						<td>
							<el-switch
									v-model="updateJobLevel.enabled"
									active-text="启用"
									inactive-text="禁用">
							</el-switch>
						</td>
					</tr>
				</table>
			</div>
			<span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
            </span>
		</el-dialog>
	</div>
</template>

<script>
import {deleteRequest, getRequest, postRequest, putRequest} from "@/utils/api";

export default {
	name: "JobTitleMana",
	data() {
		return {
			loading:false,
			dialogVisible: false,
			jobLevel: {
				name: '',
				titleLevel: ''
			},
			updateJobLevel: {
				name: '',
				titleLevel: '',
				enabled: false
			},
			jobLevels: [],
			titleLevels: [
				'正高级',
				'副高级',
				'中级',
				'初级',
				'员级'
			],
		}
	},
	mounted() {
		this.initJobLevels();
	},
	methods: {
		doUpdate(){
			putRequest("/system/basic/jobTitle/",this.updateJobLevel).then(resp=>{
				if(resp){
					this.initJobLevels();
					this.dialogVisible = false;
				}
			});

		},
		showEditView(data){
			Object.assign(this.updateJobLevel,data)
			this.dialogVisible = true;
		},
		handleDelete(data) {
			this.$confirm('此操作将永久删除[' + data.name + ']职称, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				deleteRequest("/system/basic/jobTitle/" + data.id).then(resp => {
					if (resp) {
						this.initJobLevels();
					}
				})
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消删除'
				});
			});

		},
		addJobLevel() {
			if (this.jobLevel.name && this.jobLevel.titleLevel) {
				postRequest("/system/basic/jobTitle/", this.jobLevel).then(resp => {
					if (resp) {
						this.initJobLevels();
						this.jobLevel.name = '';
						this.jobLevel.titleLevel = '';
					}
				})
			} else {
				this.$message.error('职称名称和级别不可以为空!');
			}
		},
		initJobLevels() {
			this.loading=true
			getRequest("/system/basic/jobTitle/").then(resp => {
				this.loading=false;
				if (resp) {
					this.jobLevels = resp;
				}
			})
		}
	}
}
</script>

<style scoped>

</style>