<template>
	<div>
		<el-form v-loading="loading" :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
			<h3 class="loginTitle">系统登录</h3>
			<el-form-item prop="username">
				<el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
			</el-form-item>
			<el-form-item prop="password">
				<el-input show-password type="text" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码" @keydown.enter.native="submitLogin"></el-input>
			</el-form-item>
			<el-checkbox size="normal" class="loginRemember" v-model="checked">remember</el-checkbox>
			<el-button type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
		</el-form>
	</div>
</template>

<script>

export default {
	name: "Login",
	data() {
		return {
			loading:false,
			loginForm: {
				username: 'admin',
				password: '123'
			},
			checked: true,
			rules: {
				username: [{required: true, message: "请输入用户名", trigger: 'blur'}],
				password: [{required: true, message: "请输密码", trigger: 'blur'}],

			}
		}
	},
	methods: {
		submitLogin() {
			this.$refs.loginForm.validate((valid) => {
				if (valid) {
					this.loading = true;
					this.postKeyValueRequest('/doLogin', this.loginForm).then(resp => {
						this.loading = false;
						if (resp) {
							window.sessionStorage.setItem("user",JSON.stringify(resp.obj))
							this.$router.replace("/home")
						}
					})
				} else {
					this.$message.error('请输入所有字段');
					return false;
				}
			});
		}
	}
}
</script>

<style>
.loginContainer {
	border-radius: 15px;
	background-clip: padding-box;
	margin: 180px auto;
	width: 350px;
	padding: 15px 35px 15px 35px;
	background: #fff;
	border: 1px solid #eaeaea;
	box-shadow: 0 0 25px #cac6c6;
}

.loginTitle {
	margin: 15px auto 20px auto;
	text-align: center;
	color: #505458;
}

.loginRemember {
	text-align: left;
	margin: 0px 0px 15px 0px;
}
</style>