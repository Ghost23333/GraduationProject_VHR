<template>
	<div style="width: 600px">
		<el-input
				placeholder="请输入部门名称进行搜索..."
				prefix-icon="el-ic"
				v-model="filterText">
		</el-input>

		<el-tree
				default-expand-all
				:data="deps"
				:props="defaultProps"
				:expand-on-click-node="false"
				:filter-node-method="filterNode"
				ref="tree">
			 <span class="custom-tree-node" style="display: flex;justify-content: space-between;width: 100%" slot-scope="{ node, data }">
		        <span>{{ node.label }}</span>
		        <span>
		          <el-button
				          type="primary"
				          style="padding: 2px"
				          size="mini"
				          @click="() => addDep(data)">
		            添加部门
		          </el-button>
		          <el-button
				          type="danger"
				          style="padding: 2px"
				          size="mini"
				          @click="() => deleteDep(node, data)">
		            删除部门
		          </el-button>
		        </span>
			 </span>
		</el-tree>
	</div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
	name: "DepMana",
	data() {
		return {
			filterText: '',
			deps: [],
			defaultProps: {
				children: 'children',
				label: 'name'
			}

		}
	},
	mounted() {
		this.initDeps();
	},
	methods: {
		addDep(data){

		},
		deleteDep(){

		},
		initDeps() {
			getRequest("/system/basic/department/").then(resp => {
				if (resp) {
					this.deps = resp;
				}
			})
		},
		filterNode(value, data) {
			if (!value) return true;
			return data.name.indexOf(value) !== -1;
		},

	},
	watch: {
		filterText(val) {
			this.$refs.tree.filter(val);
		}
	},
}
</script>

<style scoped>

</style>