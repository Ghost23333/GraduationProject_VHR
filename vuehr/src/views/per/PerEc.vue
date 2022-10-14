<template>
	<div>
		<div>
			<el-input
					v-model="keywords"
					placeholder="请输入员工名进行搜索..."
					prefix-icon="el-icon-search"
					@keydown.enter.native="initEmps"
					@input="initEmps"
					style="width: 300px;margin-right: 8px"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="initEmps">搜索</el-button>
		</div>
		<div style="margin-top: 10px">
			<el-table
					v-loading="loading"
					:data="emps"
					border
					stripe
					style="width: 100%">
				<el-table-column
						type="selection"
						width="55">
				</el-table-column>
				<el-table-column
						prop="name"
						label="姓名"
						fixed
						align="left"
						width="120">
				</el-table-column>
				<el-table-column
						prop="workID"
						label="工号"
						align="left"
						width="150">
				</el-table-column>
				<el-table-column
						prop="department.name"
						label="所属部门"
						width="150">
				</el-table-column>
				<el-table-column
						prop="position.name"
						label="职位"
						align="left"
						width="150">
				</el-table-column>
				<el-table-column
						prop="jobLevel.name"
						label="职称"
						align="left"
						width="180">
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button @click="checkPerEc(scope.row)" size="mini" type="primary">查看奖惩记录</el-button>
						<el-button @click="addPerEc(scope.row)" size="mini" type="primary">添加奖惩记录</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div style="display: flex;justify-content: flex-end">
				<el-pagination
						@current-change="currentChange"
						@size-change="sizeChange"
						background
						layout="sizes, prev, pager, next, jumper, ->, total, slot"
						:total="total">
				</el-pagination>
			</div>
		</div>
		<el-dialog
				title="添加奖惩记录"
				:visible.sync="dialogVisible"
				width="30%">
			<div>
				<table>
					<tr>
						<td>
							<el-tag>奖惩类型</el-tag>
						</td>
						<td style="padding-left: 20px">
							<el-radio-group v-model="PerEc.ecType">
								<el-radio label= 1>惩罚</el-radio>
								<el-radio label= 0>奖励</el-radio>
							</el-radio-group>
						</td>
					</tr>
					<tr>
						<td>
							<el-tag>奖罚分数</el-tag>
						</td>
						<td style="padding-left: 20px">
							<el-rate v-model="PerEc.ecPoint"></el-rate>
						</td>
					</tr>
					<tr>
						<td>
							<el-tag>奖罚原因</el-tag>
						</td>
						<td style="padding-left: 20px">
							<el-input size="small" v-model="PerEc.ecReason"></el-input>
						</td>
					</tr>
					<tr>
						<td>
							<el-tag>备注</el-tag>
						</td>
						<td style="padding-left: 20px">
							<el-input size="small" v-model="PerEc.remark"></el-input>
						</td>
					</tr>
				</table>
			</div>
			<span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doAddPerEc">确 定</el-button>
            </span>
		</el-dialog>
<el-dialog
				title="查看奖惩记录"
				:visible.sync="dialogVisibleForCheck"
				width="50%">
			<div>
				<el-table :data="PerEcs" border stripe>
					<el-table-column prop="ecType" label="奖惩类型">
						<template slot-scope="scope">
							<el-tag type="danger" v-if="scope.row.ecType">惩罚</el-tag>
							<el-tag type="success" v-else>奖励</el-tag>
						</template>
					</el-table-column>
					<el-table-column prop="ecPoint" label="奖惩分数">
						<template slot-scope="scope">
							<el-rate
									:value="scope.row.ecPoint"
									disabled
									show-score
									text-color="#ff9900"
									score-template="{value}">
							</el-rate>
						</template>
					</el-table-column>
					<el-table-column prop="ecDate" label="奖惩时间"></el-table-column>
					<el-table-column prop="ecReason" label="奖惩原因"></el-table-column>
					<el-table-column prop="remark" label="备注"></el-table-column>
				</el-table>
			</div>
			<span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisibleForCheck = false">退出</el-button>
            </span>
		</el-dialog>
	</div>
</template>

<script>
import {getRequest, postRequest} from "@/utils/api";

export default {
	name: "PerEc",
	data() {
		return {
			keywords: '',
			emps: [],
			total: 0,
			page: 1,
			size: 10,
			loading: false,
			dialogVisible:false,
			dialogVisibleForCheck:false,
			PerEc:{
				eid:0,
				ecReason:'',
				ecPoint:0,
				ecType:1,
				remark:''
			},
			PerEcs:[],
		}
	},
	mounted() {
		this.initEmps();
	},
	methods: {
		initEmps() {
			this.loading = true;
			getRequest("/employee/basic/?page=" + this.page + "&size=" + this.size + "&keywords=" + this.keywords).then(resp => {
				this.loading = false;
				if (resp) {
					this.emps = resp.data;
					this.total = resp.total;
				}
			})
		},
		addPerEc(data) {
			this.PerEc = {
				eid:0,
				ecReason:'',
				ecPoint:0,
				ecType:0,
				remark:''
			};
			this.PerEc.eid = data.id;
			this.dialogVisible = true;
		},
		doAddPerEc(){
			postRequest("/personnel/ec/",this.PerEc).then(resp=>{
				if(resp){
					this.dialogVisible = false;
				}
			})
		},
		checkPerEc(data){
			getRequest("/personnel/ec/"+data.id).then(resp=>{
				if(resp){
					this.PerEcs = resp;
					this.dialogVisibleForCheck = true;
				}
			})
		},
		sizeChange(size) {
			this.size = size;
			this.initEmps();
		},
		currentChange(currentPage) {
			this.page = currentPage;
			this.initEmps();
		},

	}
}
</script>

<style scoped>

</style>