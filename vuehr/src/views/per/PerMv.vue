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
						width="180">
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button @click="checkPerMv(scope.row)" size="mini" type="primary">查看调动记录</el-button>
						<el-button @click="addPerMv(scope.row)" size="mini" type="primary">添加调动记录</el-button>
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
				title="添加调动记录"
				:visible.sync="dialogVisibleForAdd"
				width="30%">
			<div>
				<table>
					<tr>
						<td>
							<el-tag>调动部门</el-tag>
						</td>
						<td style="padding-left: 20px">
							<el-popover
									placement="right"
									title="请选择部门"
									width="300"
									trigger="manual"
									v-model="popVisible">
								<el-tree default-expand-all :data="allDeps" :props="defaultProps"
								         @node-click="handleNodeClick"></el-tree>
								<div slot="reference"
								     style="width: 150px;display: inline-flex;font-size: 13px;border: 1px solid #dedede;height: 40px;border-radius: 5px;cursor: pointer;align-items: center;padding-left: 8px;box-sizing: border-box"
								     @click="showDepView">{{ inputDepName }}
								</div>
							</el-popover>
						</td>
					</tr>
					<tr>
						<td><el-tag>调动职位</el-tag></td>
						<td style="padding-left: 20px">
							<el-select v-model="PerMv.afterPosId" placeholder="职位" style="width: 150px;">
								<el-option
										v-for="item in positions"
										:key="item.id"
										:label="item.name"
										:value="item.id">
								</el-option>
							</el-select>
						</td>
					</tr>
					<tr>
						<td>
							<el-tag>调动原因</el-tag>
						</td>
						<td style="padding-left: 20px">
							<el-input size="small" v-model="PerMv.reason"></el-input>
						</td>
					</tr>
					<tr>
						<td>
							<el-tag>备注</el-tag>
						</td>
						<td style="padding-left: 20px">
							<el-input size="small" v-model="PerMv.remark"></el-input>
						</td>
					</tr>
				</table>
			</div>
			<span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisibleForAdd = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doAddPerMv">确 定</el-button>
            </span>
		</el-dialog>
		<el-dialog
				title="查看调动记录"
				:visible.sync="dialogVisibleForCheck"
				width="70%">
			<div>
				<el-table :data="PerMvs" border stripe>
					<el-table-column prop="beforePosName" label="调动前职位"></el-table-column>
					<el-table-column prop="afterPosName" label="调动后职位"></el-table-column>
					<el-table-column prop="beforeDepName" label="调动前部门"></el-table-column>
					<el-table-column prop="afterDepName" label="调动后部门"></el-table-column>
					<el-table-column prop="removeDate" label="调动时间"></el-table-column>
					<el-table-column prop="reason" label="调动原因"></el-table-column>
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
	name: "PerMv",
	data(){
		return{
			positions:[],
			inputDepName:'选择部门',
			popVisible:false,
			allDeps:[],
			defaultProps:{
				children:"children",
				label:"name"
			},
			keywords: '',
			emps: [],
			total: 0,
			page: 1,
			size: 10,
			loading: false,
			dialogVisibleForAdd:false,
			dialogVisibleForCheck:false,
			PerMv:{
				eid:0,
				beforeDepId:0,
				afterDepId:0,
				beforePosId:0,
				afterPosId:0,
				reason:'',
				remark:''
			},
			PerMvs:[],
		}
	},
	mounted() {
		this.initEmps();
		this.initData();
	},
	methods:{
		showDepView(){
			this.popVisible = !this.popVisible;
		},
		handleNodeClick(data){
			this.popVisible = !this.popVisible;
			this.inputDepName = data.name;
			this.PerMv.afterDepId = data.id;
		},
		checkPerMv(data){
			getRequest("/personnel/remove/"+data.id).then(resp=>{
				if(resp){
					this.PerMvs = resp;

					this.dialogVisibleForCheck = true;
				}
			})
		},
		addPerMv(data){
			this.initPositions();
			this.PerMv = {
				eid:0,
				beforeDepId:0,
				afterDepId:0,
				beforePosId:0,
				afterPosId:0,
				reason:'',
				remark:''
			};
			this.inputDepName='选择部门',
			this.PerMv.afterPosId=data.posId,
			this.PerMv.eid = data.id;
			this.PerMv.beforeDepId = data.departmentId
			this.PerMv.beforePosId=data.posId
			this.dialogVisibleForAdd = true;
		},
		doAddPerMv(){
			postRequest("/personnel/remove/",this.PerMv).then(resp=>{
				this.initEmps();
				this.dialogVisibleForAdd = false
			})
		},
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
		sizeChange(size) {
			this.size = size;
			this.initEmps();
		},
		currentChange(currentPage) {
			this.page = currentPage;
			this.initEmps();
		},
		initData(){
			if (!window.sessionStorage.getItem("deps")) {
				getRequest("/employee/basic/deps").then(resp => {
					if (resp) {
						this.allDeps = resp;
						window.sessionStorage.setItem("deps",JSON.stringify(resp))
					}
				})
			}else {
				this.allDeps = JSON.parse(window.sessionStorage.getItem("deps"))
			}

		},
		initPositions(){
			getRequest("/employee/basic/positions").then(resp => {
				if (resp) {
					this.positions = resp;
				}
			})
		},
		getPosName(PosId){
			for(let index in this.positions){
				console.log(this.positions)
				if(this.positions[index].id  == PosId){
					console.log(this.positions[index].name)
					return this.positions[index].name
				}
			}
		}


	}
}
</script>

<style scoped>

</style>