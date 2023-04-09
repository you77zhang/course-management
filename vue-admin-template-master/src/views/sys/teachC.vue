<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="24">
          <el-input v-model="searchModal.tno" placeholder="工号" clearable></el-input>
          <el-input v-model="searchModal.tname" placeholder="姓名" clearable></el-input>
          <el-button @click="getTeaList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
      </el-row>
    </el-card>
    <!-- 结果列表 -->
    <el-card id="list">
      <el-table :data="teaList" stripe style="width: 100%">
        <el-table-column prop="date" align="center" label="#" width="80">
          <!-- (pageNo-1)*pageSize+index+1 -->
          <template slot-scope="scope">
            {{ (searchModal.pageNo - 1) * searchModal.pageSize + scope.$index + 1 }}
          </template>
        
        </el-table-column>
        <el-table-column prop="tno" label="工号" align="center" width="180">
        </el-table-column>
        <el-table-column prop="tname" label="姓名" align="center" width="180">
        </el-table-column>
        <el-table-column prop="cno" label="课程号" align="center" width="180">
        </el-table-column>
        <el-table-column prop="cname" label="课程名" align="center">
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModal.pageNo" :page-sizes="[5, 10, 50, 100]" :page-size="searchModal.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>

  </div>
</template>

<script>
import teaApi from '@/api/teacher'
// import { getList } from '@/api/table';

export default {
  data() {
    return {
      total: 0,
      searchModal: {
        pageNo: 1,
        pageSize: 10
      },
      tcModal: {
        tno:"",
        tname:"",
        cno:"",
        cname:""
      },
      teaList: [],
      dialogAddVisible: false,
      formLabelWidth: "130px",
      formLabelWidth2: "300px",
      title: ""
    }
  },
  methods: {
    handleSizeChange(pageSize) {
      this.searchModal.pageSize = pageSize;
      this.getTeaList();
    },
    handleCurrentChange(pageNo) {
      this.searchModal.pageNo = pageNo;
      this.getTeaList();
    },
    getTeaList() {
      teaApi.getTeachCList(this.searchModal).then(response => {
        this.teaList = response.data.rows;
        this.total = response.data.total;
      });
    }
  },
  // 钩子函数，加载的时候就执行
  created() {
    this.getTeaList();
  }
};
</script>

<style>
#search .el-input {
  width: 200px;
  margin-right: 10px;
}

.el-dialog .el-input {
  width: 100%;
}

/* .el-input {
  width: 200px;
  margin-right: 10px;
} */
</style>