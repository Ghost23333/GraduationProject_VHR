<template>
	<div>
		<div style="font-size: 25px;margin-bottom: 10px"><span>个人中心</span></div>
		<div style="display: flex;">
			<el-card style="margin-right: 30px">
				<div>
					<img :src="hr.userface" class="image" style="width: 200px">
				</div>
				<div>
					<el-button style="margin-left: 45px;margin-top: 10px" type="primary">修改头像</el-button>
				</div>

			</el-card>
			<el-card style="width: 500px">
				<div class="text-container">
					<div> 姓名: <span>{{ hr.name }}</span></div>
					<div>用户名: <span>{{ hr.username }}</span></div>
					<div>手机号码: <span>{{ hr.phone }}</span></div>
					<div>电话号码: <span>{{ hr.telephone }}</span></div>
					<div>地址: <span>{{ hr.address }}</span></div>
					<div>备注: <span>{{ hr.remark }}</span></div>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
export default {
	name: "userInfo",
	data() {
		return {
			hr: {},
		}
	},
	mounted() {
		this.hr = JSON.parse(window.sessionStorage.getItem("user"))
	}
}
</script>

<style>
.text-container div{
	margin-bottom: 10px;
	font-size: 20px;
}
.text-container span{
	color: #409eff;
}
</style>