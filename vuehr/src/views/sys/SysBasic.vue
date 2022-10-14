<template>
	<div>
		<el-tabs v-model="activeName" type="card">
			<el-tab-pane label="部门管理" name="depmana"><DepMana /></el-tab-pane>
			<el-tab-pane label="职位管理" name="posmana"><PosMana /></el-tab-pane>
			<el-tab-pane label="职称管理" name="jobtitlemana"><JobTitleMana /></el-tab-pane>
			<el-tab-pane label="权限组" name="permmana"><PermMana /></el-tab-pane>
		</el-tabs>
	</div>

</template>

<script>
import DepMana from "@/components/sys/basic/DepMana";
import JobTitleMana from "@/components/sys/basic/JobTitleMana";
import PermMana from "@/components/sys/basic/PermMana";
import PosMana from "@/components/sys/basic/PosMana";
export default {
	name: "SysBasic",
	data(){
		return{
			activeName:"jobtitlemana",

		}
	},
	components:{
		DepMana,
		JobTitleMana,
		PosMana,
		PermMana,
	}
}
</script>

<style scoped>

</style>