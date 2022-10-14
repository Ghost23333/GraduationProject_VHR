<template>
	<div>
		<div style="display: flex;">
			<el-image class="welcomeLogo" :src="welcomeLogoUrl"></el-image>
			<div style="font-size: 30px;text-align: left;margin-left: 20px;margin-top: 20px">早安, HR专员, 祝你开心每一天!</div>
		</div>
		<el-divider></el-divider>
		<div style="display: flex">
			<div style="width: 700px">
				<span style="font-size: 30px;">工作日历</span>
				<el-calendar style="margin-top: 30px" v-model="value"></el-calendar>
			</div>
			<div style="font-size: 20px;margin-left: 100px">快速访问</div>
		</div>

	</div>



</template>

<script>
export default {
	name: "Welcome",
	data() {
		return {
			welcomeLogoUrl: require('../assets/welcomeLogo.png'),
			value: new Date()
		}
	}
}
</script>

<style scoped>
.welcomeLogo {
	width: 100px;
	height: 100px;
	border-radius: 50px;
	/*display: inline;*/
	/*float: left;*/
}
</style>