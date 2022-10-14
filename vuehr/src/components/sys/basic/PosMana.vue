<template>
	<div>
		<div>
			<el-input
					style="width: 300px;"
					placeholder="添加职位..."
					prefix-icon="el-icon-plus"
					v-model="position.name"
					@keydown.enter.native="addPosition">
			</el-input>
			<el-button icon="el-icon-plus" type="primary" @click="addPosition">添加</el-button>
		</div>
		<div class="posManTable">
			<el-table
					v-loading="loading"
					:data="positions"
					border
					stripe
					@selection-change="handleSelectionChange"
					style="width: 100%">
				<el-table-column
						type="selection"
						width="55">
				</el-table-column>
				<el-table-column
						prop="id"
						label="编号"
						width="55">
				</el-table-column>
				<el-table-column
						prop="name"
						label="职位名称"
						width="180">
				</el-table-column>
				<el-table-column
						prop="createDate"
						label="创建时间"
						width="280">
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
								@click="showEditView(scope.$index, scope.row)">编辑
						</el-button>
						<el-button
								size="mini"
								type="danger"
								@click="handleDelete(scope.$index, scope.row)">删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-button @click="deleteMultiplePos" type="danger" style="margin-top: 8px"
			           :disabled="this.multipleSelection.length==0">批量删除
			</el-button>
		</div>
		<el-dialog
				title="修改职位"
				:visible.sync="dialogVisible"
				width="30%">
			<div>
				<div>
					<el-tag>职位名称</el-tag>
					<el-input class="updatePosInput" size="small" v-model="updatePosition.name"></el-input>
				</div>
				<div>
					<el-tag>是否启用</el-tag>
					<el-switch
							v-model="updatePosition.enabled"
							active-text="启用"
							inactive-text="禁用">
					</el-switch>
				</div>

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
	name: "PosMana",
	data() {
		return {
			loading:false,
			position: {
				name: '',
			},
			positions: [],
			dialogVisible: false,
			updatePosition: {
				name: '',
				enabled:false
			},
			multipleSelection: []
		}
	},
	mounted() {
		this.initPositions();
	},
	methods: {
		deleteMultiplePos() {
			this.$confirm('此操作将永久删除[' + this.multipleSelection.length + ']条记录, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				let ids = '?';
				this.multipleSelection.forEach(item => {
					ids += 'ids=' + item.id + '&';
				})

				deleteRequest("/system/basic/pos/" + ids).then(resp => {
					if (resp) {
						this.initPositions();
					}
				})
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消删除'
				});
			});
		},
		handleSelectionChange(val) {
			this.multipleSelection = val;
		},
		addPosition() {
			if (this.position.name) {
				postRequest("/system/basic/pos/", this.position).then(resp => {
					if (resp) {
						this.initPositions();
						this.position.name = '';
					}
				})
			} else {
				this.$message.error('职位名称不可以为空');
			}
		},
		showEditView(index, data) {
			Object.assign(this.updatePosition, data);
			this.dialogVisible = true;
		},
		doUpdate() {
			putRequest("/system/basic/pos/", this.updatePosition).then(resp => {
				if (resp) {
					this.initPositions();
					this.updatePosition.name = '';
					this.dialogVisible = false;
				}
			})
		},
		handleDelete(index, data) {
			this.$confirm('此操作将永久删除[' + data.name + ']职位, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				deleteRequest("/system/basic/pos/" + data.id).then(resp => {
					if (resp) {
						this.initPositions();
					}
				})
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消删除'
				});
			});
		},
		initPositions() {
			this.loading=true;
			getRequest("/system/basic/pos/").then(resp => {
				this.loading=false;
				if (resp) {
					this.positions = resp;
				}
			})
		}
	}
}
</script>

<style>
.posManTable {
	margin-top: 10px;
}

.updatePosInput {
	width: 200px;
	margin-left: 8px;
}
</style>