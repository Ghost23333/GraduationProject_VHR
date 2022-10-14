<template>
	<div>
		<div style="display: flex;justify-content: space-between">
			<div>
				<el-input
						v-model="keywords"
						placeholder="请输入员工名进行搜索..."
						prefix-icon="el-icon-search"
						@keydown.enter.native="initEmps"
						@input="initEmps"
						style="width: 300px;margin-right: 8px"></el-input>
				<el-button type="primary" icon="el-icon-search" @click="initEmps">搜索</el-button>
				<el-button type="primary">
					<i class="fa fa-angle-double-down" aria-hidden="true"></i>
					高级搜索
				</el-button>
			</div>
			<div>
				<el-button type="success">
					<i class="fa fa-level-up" aria-hidden="true"></i>
					导入数据
				</el-button>
				<el-button type="success">
					<i class="fa fa-level-down" aria-hidden="true"></i>
					导出数据
				</el-button>
				<el-button type="primary" icon="el-icon-plus" @click="showAddEmpView">
					添加用户
				</el-button>
			</div>
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
						width="85">
				</el-table-column>
				<el-table-column
						prop="birthday"
						label="出生日期"
						align="left"
						width="100">
				</el-table-column>
				<el-table-column
						prop="idCard"
						label="身份证号码"
						align="left"
						width="170">
				</el-table-column>
				<el-table-column
						prop="wedlock"
						label="婚姻状况"
						width="50">
				</el-table-column>
				<el-table-column
						prop="nation.name"
						label="民族"
						width="50">
				</el-table-column>
				<el-table-column
						prop="nativePlace"
						label="籍贯"
						width="80">
				</el-table-column>
				<el-table-column
						prop="politicsstatus.name"
						label="政治面貌">
				</el-table-column>
				<el-table-column
						prop="email"
						label="电子邮件"
						align="left"
						width="180">
				</el-table-column>
				<el-table-column
						prop="phone"
						label="电话号码"
						align="left"
						width="120">
				</el-table-column>
				<el-table-column
						prop="address"
						label="联系地址"
						align="left"
						width="220">
				</el-table-column>
				<el-table-column
						prop="department.name"
						label="所属部门"
						width="100">
				</el-table-column>
				<el-table-column
						prop="position.name"
						label="职位"
						align="left"
						width="100">
				</el-table-column>
				<el-table-column
						prop="jobLevel.name"
						label="职称"
						align="left"
						width="120">
				</el-table-column>
				<el-table-column
						prop="engageForm"
						label="聘用形式"
						align="left"
						width="100">
				</el-table-column>
				<el-table-column
						prop="beginDate"
						label="入职日期"
						align="left"
						width="100">
				</el-table-column>
				<el-table-column
						prop="conversionTime"
						label="转正日期"
						align="left"
						width="100">
				</el-table-column>
				<el-table-column
						prop="beginContract"
						label="合同起始日期"
						align="left"
						width="100">
				</el-table-column>
				<el-table-column
						prop="endContract"
						label="合同终止日期"
						align="left"
						width="100">
				</el-table-column>
				<el-table-column
						prop="contractTerm"
						label="合同期限"
						align="left"
						width="70">
				</el-table-column>
				<el-table-column
						prop="tiptopDegree"
						label="最高学历">
				</el-table-column>
				<el-table-column label="操作" fixed="right" width="200">
					<template slot-scope="scope">
						<el-button @click="showEditEmpView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
						<el-button @click="showAdvEmpView(scope.row)" style="padding: 3px" size="mini" type="primary">
							查看高级资料
						</el-button>
						<el-button @click="deleteEmp(scope.row)" style="padding: 3px" size="mini" type="danger">删除
						</el-button>
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
				:title="dialogTitle"
				:visible.sync="dialogVisible"
				width="80%">
			<div>
				<el-form :model="emp" :rules="rules" ref="empForm">
					<el-row>
						<el-col :span="6">
							<el-form-item label="姓名:" prop="name">
								<el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="emp.name"
								          placeholder="请输入员工姓名"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="性别:" prop="gender">
								<el-radio-group v-model="emp.gender">
									<el-radio label="男">男</el-radio>
									<el-radio label="女">女</el-radio>
								</el-radio-group>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="出生日期:" prop="birthday">
								<el-date-picker
										v-model="emp.birthday"
										type="date"
										value-format="yyyy-MM-dd"
										style="width: 150px;"
										placeholder="出生日期">
								</el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="政治面貌:" prop="politicId">
								<el-select v-model="emp.politicId" placeholder="政治面貌" style="width: 200px;">
									<el-option
											v-for="item in politicsstatus"
											:key="item.id"
											:label="item.name"
											:value="item.id">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="6">
							<el-form-item label="民族:" prop="nationId">
								<el-select v-model="emp.nationId" placeholder="民族" style="width: 150px;">
									<el-option
											v-for="item in nations"
											:key="item.id"
											:label="item.name"
											:value="item.id">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="籍贯:" prop="nativePlace">
								<el-input style="width: 120px" prefix-icon="el-icon-edit"
								          v-model="emp.nativePlace" placeholder="请输入籍贯"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="电子邮箱:" prop="email">
								<el-input style="width: 150px" prefix-icon="el-icon-message"
								          v-model="emp.email" placeholder="请输入电子邮箱"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="联系地址:" prop="address">
								<el-input style="width: 200px" prefix-icon="el-icon-edit"
								          v-model="emp.address" placeholder="请输入联系地址"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="6">
							<el-form-item label="职位:" prop="posId">
								<el-select v-model="emp.posId" placeholder="职位" style="width: 150px;">
									<el-option
											v-for="item in positions"
											:key="item.id"
											:label="item.name"
											:value="item.id">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="职称:" prop="jobLevelId">
								<el-select v-model="emp.jobLevelId" placeholder="职称" style="width: 150px;">
									<el-option
											v-for="item in joblevels"
											:key="item.id"
											:label="item.name"
											:value="item.id">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="所属部门:" prop="departmentId">
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
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="电话号码:" prop="phone">
								<el-input style="width: 200px" prefix-icon="el-icon-phone"
								          v-model="emp.phone" placeholder="电话号码"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="6">
							<el-form-item label="工号:" prop="workID">
								<el-input style="width: 150px" prefix-icon="el-icon-edit"
								          v-model="emp.workID" disabled></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="学历:" prop="tiptopDegree">
								<el-select v-model="emp.tiptopDegree" placeholder="学历"
								           style="width: 150px;">
									<el-option
											v-for="item in tiptopDegrees"
											:key="item"
											:label="item"
											:value="item">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="毕业院校:" prop="school">
								<el-input style="width: 150px" prefix-icon="el-icon-edit"
								          v-model="emp.school" placeholder="毕业院校名称"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="专业名称:" prop="specialty">
								<el-input style="width: 200px" prefix-icon="el-icon-edit"
								          v-model="emp.specialty" placeholder="请输入专业名称"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="6">
							<el-form-item label="入职日期:" prop="beginDate">
								<el-date-picker
										v-model="emp.beginDate"
										type="date"
										value-format="yyyy-MM-dd"
										style="width: 140px;cursor: pointer"
										placeholder="入职日期">
								</el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="转正日期:" prop="conversionTime">
								<el-date-picker
										v-model="emp.conversionTime"

										type="date"
										value-format="yyyy-MM-dd"
										style="width: 140px;cursor: pointer"
										placeholder="转正日期">
								</el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="合同起始日期:" prop="beginContract">
								<el-date-picker
										v-model="emp.beginContract"

										type="date"
										value-format="yyyy-MM-dd"
										style="width: 140px;cursor: pointer"
										placeholder="合同起始日期">
								</el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="合同终止日期:" prop="endContract" style="cursor: pointer">
								<el-date-picker
										v-model="emp.endContract"

										type="date"
										value-format="yyyy-MM-dd"
										style="width: 140px;cursor: pointer"
										placeholder="合同终止日期">
								</el-date-picker>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="身份证号码:" prop="idCard">
								<el-input style="width: 180px" prefix-icon="el-icon-edit"
								          v-model="emp.idCard" placeholder="请输入身份证号码"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="聘用形式:" prop="engageForm">
								<el-radio-group v-model="emp.engageForm">
									<el-radio label="劳动合同">劳动合同</el-radio>
									<el-radio label="劳务合同">劳务合同</el-radio>
								</el-radio-group>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="婚姻状况:" prop="wedlock">
								<el-radio-group v-model="emp.wedlock">
									<el-radio label="已婚">已婚</el-radio>
									<el-radio label="未婚">未婚</el-radio>
									<el-radio label="离异">离异</el-radio>
								</el-radio-group>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</div>
			<span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAddEmp">确 定</el-button>
  </span>
		</el-dialog>
		<el-dialog
				title="查看员工高级资料"
				:visible.sync="dialogVisibleForAdv"
				width="70%">
			<div>
				<span style="padding-right: 30px;">员工工资套账</span>
				<el-tooltip placement="right" v-if="salarySob">
					<div slot="content">
						<table>
							<tr>
								<td>基本工资</td>
								<td>{{ salarySob.basicSalary }}</td>
							</tr>
							<tr>
								<td>交通补助</td>
								<td>{{ salarySob.trafficSalary }}</td>
							</tr>
							<tr>
								<td>午餐补助</td>
								<td>{{ salarySob.lunchSalary }}</td>
							</tr>
							<tr>
								<td>奖金</td>
								<td>{{ salarySob.bonus }}</td>
							</tr>
							<tr>
								<td>养老金比率</td>
								<td>{{ salarySob.pensionPer }}</td>
							</tr>
							<tr>
								<td>养老金基数</td>
								<td>{{ salarySob.pensionBase }}</td>
							</tr>
							<tr>
								<td>医疗保险比率</td>
								<td>{{ salarySob.medicalPer }}</td>
							</tr>
							<tr>
								<td>医疗保险基数</td>
								<td>{{ salarySob.medicalBase }}</td>
							</tr>
							<tr>
								<td>公积金比率</td>
								<td>{{ salarySob.accumulationFundPer }}</td>
							</tr>
							<tr>
								<td>公积金基数</td>
								<td>{{ salarySob.accumulationFundBase }}</td>
							</tr>
							<tr>
								<td>启用时间</td>
								<td>{{ salarySob.createDate }}</td>
							</tr>
						</table>
					</div>
					<el-tag>{{ salarySob.name }}</el-tag>
				</el-tooltip>
				<el-tag v-else>暂未设置</el-tag>
			</div>
			<el-divider></el-divider>
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
			<el-divider></el-divider>
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
                <el-button size="small" @click="dialogVisibleForAdv = false">退出</el-button>
            </span>
		</el-dialog>
	</div>
</template>

<script>
import {deleteRequest, getRequest, postRequest, putRequest} from "@/utils/api";

export default {
	name: "EmpBasic",
	data() {
		return {
			salarySob: {},
			PerEcs: [],
			PerMvs: [],
			dialogTitle: '添加员工',
			allDeps: [],
			defaultProps: {
				children: "children",
				label: "name"
			},
			popVisible: false,
			dialogVisible: false,
			dialogVisibleForAdv: false,
			keywords: '',
			loading: false,
			emps: [],
			total: 0,
			page: 1,
			size: 10,
			inputDepName: '所属部门',
			emp: {
				name: "",
				gender: "",
				birthday: "",
				idCard: "",
				wedlock: "",
				nationId: 1,
				nativePlace: "",
				politicId: 13,
				email: "",
				phone: "",
				address: "",
				departmentId: null,
				jobLevelId: 12,
				posId: 54,
				engageForm: "",
				tiptopDegree: "",
				specialty: "",
				school: "",
				beginDate: "",
				workState: "在职",
				workID: "",
				contractTerm: 2,
				conversionTime: "",
				notWorkDate: null,
				beginContract: "",
				endContract: "",
				workAge: null

			},
			nations: [],
			politicsstatus: [],
			joblevels: [],
			positions: [],
			tiptopDegrees: ['本科', '大专', '硕士', '博士', '高中', '初中', '小学', '其他'],
			rules: {
				name: [{required: true, message: '请输入用户名', trigger: 'blur'}],
				gender: [{required: true, message: '请输入性别', trigger: 'blur'}],
				birthday: [{required: true, message: '请输入出生日期', trigger: 'blur'}],
				idCard: [{required: true, message: '请输入身份证号码', trigger: 'blur'}, {
					pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
					message: '身份证号码格式不正确',
					trigger: 'blur'
				}],
				wedlock: [{required: true, message: '请输入婚姻状况', trigger: 'blur'}],
				nationId: [{required: true, message: '请输入您组', trigger: 'blur'}],
				nativePlace: [{required: true, message: '请输入籍贯', trigger: 'blur'}],
				politicId: [{required: true, message: '请输入政治面貌', trigger: 'blur'}],
				email: [{required: true, message: '请输入邮箱地址', trigger: 'blur'}, {
					type: 'email',
					message: '邮箱格式不正确',
					trigger: 'blur'
				}],
				phone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
				address: [{required: true, message: '请输入员工地址', trigger: 'blur'}],
				departmentId: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
				jobLevelId: [{required: true, message: '请输入职称', trigger: 'blur'}],
				posId: [{required: true, message: '请输入职位', trigger: 'blur'}],
				engageForm: [{required: true, message: '请输入聘用形式', trigger: 'blur'}],
				tiptopDegree: [{required: true, message: '请输入学历', trigger: 'blur'}],
				specialty: [{required: true, message: '请输入专业', trigger: 'blur'}],
				school: [{required: true, message: '请输入毕业院校', trigger: 'blur'}],
				beginDate: [{required: true, message: '请输入入职日期', trigger: 'blur'}],
				workState: [{required: true, message: '请输入工作状态', trigger: 'blur'}],
				workID: [{required: true, message: '请输入工号', trigger: 'blur'}],
				contractTerm: [{required: true, message: '请输入合同期限', trigger: 'blur'}],
				conversionTime: [{required: true, message: '请输入转正日期', trigger: 'blur'}],
				notworkDate: [{required: true, message: '请输入离职日期', trigger: 'blur'}],
				beginContract: [{required: true, message: '请输入合同起始日期', trigger: 'blur'}],
				endContract: [{required: true, message: '请输入合同结束日期', trigger: 'blur'}],
				workAge: [{required: true, message: '请输入工龄', trigger: 'blur'}],
			},

		}
	},
	mounted() {
		this.initEmps();
		this.initData();
	},
	methods: {
		showAdvEmpView(data) {
			getRequest("/personnel/ec/" + data.id).then(resp => {
				if (resp) {
					this.PerEcs = resp;
				}
			})
			getRequest("/personnel/remove/" + data.id).then(resp => {
				if (resp) {
					this.PerMvs = resp;
				}
			})
			getRequest("/salary/sob/" + data.id).then(resp => {
				if (resp) {
					this.salarySob = resp;
				}
			})
			this.dialogVisibleForAdv = true;
		},
		emptyEmp() {
			this.emp = {
				name: "",
				gender: "",
				birthday: "",
				idCard: "",
				wedlock: "",
				nationId: 1,
				nativePlace: "",
				politicId: 13,
				email: "",
				phone: "",
				address: "",
				departmentId: null,
				jobLevelId: 12,
				posId: 54,
				engageForm: "",
				tiptopDegree: "",
				specialty: "",
				school: "",
				beginDate: "",
				workState: "在职",
				workID: "",
				contractTerm: 2,
				conversionTime: "",
				notWorkDate: null,
				beginContract: "",
				endContract: "",
				workAge: null
			}
			this.inputDepName = '所属部门';
		},
		showEditEmpView(data) {
			this.dialogTitle = '编辑员工信息';
			this.emp = data;
			this.inputDepName = data.department.name;
			this.initPositions();
			this.dialogVisible = true;

		},
		doAddEmp()  {
			if (this.emp.id) {
				this.$refs.empForm.validate(valid => {
					if (valid) {
						putRequest("/employee/basic/", this.emp).then(resp => {
							if (resp) {
								this.dialogVisible = false;
								this.emptyEmp();
								this.initEmps();

							}
						})
					}
				})
			} else {
				this.$refs.empForm.validate(valid => {
					if (valid) {
						postRequest("/employee/basic/", this.emp).then(resp => {
							if (resp) {
								this.dialogVisible = false;
								this.initEmps();
							}
						})
					}
				})
			}
		},
		handleNodeClick(data) {
			this.popVisible = !this.popVisible;
			this.inputDepName = data.name;
			this.emp.departmentId = data.id;


		},
		showDepView() {
			this.popVisible = !this.popVisible;

		},
		getMaxWorkID() {
			getRequest("/employee/basic/maxWorkID").then(resp => {
				if (resp) {
					this.emp.workID = resp;
				}
			})
		},
		initPositions() {
			getRequest("/employee/basic/positions").then(resp => {
				if (resp) {
					this.positions = resp;
				}
			})
		},
		initData() {
			if (!window.sessionStorage.getItem("joblevels")) {
				getRequest("/employee/basic/joblevels").then(resp => {
					if (resp) {
						this.joblevels = resp;
						window.sessionStorage.setItem("joblevels", JSON.stringify(resp))
					}
				})
			} else {
				this.joblevels = JSON.parse(window.sessionStorage.getItem("joblevels"))
			}
			if (!window.sessionStorage.getItem("politicsstatus")) {
				getRequest("/employee/basic/politicsstatus").then(resp => {
					if (resp) {
						this.politicsstatus = resp;
						window.sessionStorage.setItem("politicsstatus", JSON.stringify(resp))
					}
				})
			} else {
				this.politicsstatus = JSON.parse(window.sessionStorage.getItem("politicsstatus"))
			}
			if (!window.sessionStorage.getItem("nations")) {
				getRequest("/employee/basic/nations").then(resp => {
					if (resp) {
						this.nations = resp;
						window.sessionStorage.setItem("nations", JSON.stringify(resp))
					}
				})
			} else {
				this.nations = JSON.parse(window.sessionStorage.getItem("nations"))
			}
			if (!window.sessionStorage.getItem("deps")) {
				getRequest("/employee/basic/deps").then(resp => {
					if (resp) {
						this.allDeps = resp;
						window.sessionStorage.setItem("deps", JSON.stringify(resp))
					}
				})
			} else {
				this.allDeps = JSON.parse(window.sessionStorage.getItem("deps"))
			}

		},
		sizeChange(size) {
			this.size = size;
			this.initEmps();
		},
		currentChange(currentPage) {
			this.page = currentPage;
			this.initEmps();
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
		showAddEmpView() {
			this.emptyEmp();
			this.dialogTitle='添加员工'
			this.dialogVisible = true
			this.initPositions();
			this.getMaxWorkID();
		},
		deleteEmp(data) {
			this.$confirm('此操作将永久删除[' + data.name + ']员工, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				deleteRequest("/employee/basic/" + data.id).then(resp => {
					if (resp) {
						this.initEmps()
					}
				})
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消删除'
				});
			});
		}
	}
}
</script>

<style scoped>

</style>