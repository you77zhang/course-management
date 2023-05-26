
<!-- TODO 改stu为divide-->
<!-- TODO 5改Stu为Divide -->
<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="22">
          <!-- TODO 6 改这里的 -->
          <el-input v-model="searchModal.gno" placeholder="流水号" clearable></el-input>
          <el-input v-model="searchModal.sgroup" placeholder="组号" clearable></el-input>
          <el-button @click="getDivideList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
        <!-- 新增按钮 -->
        <el-col :span="2" align="right">
          <el-button @click="openForm('新增分组')" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>
    <!-- 结果列表 -->
    <!-- TODO 4 改表格 -->
    <el-card id="list">
      <el-table :data="divideList" stripe style="width: 100%">
        <el-table-column prop="date" align="center" label="#" width="80">
          <!-- (pageNo-1)*pageSize+index+1 -->
          <template slot-scope="scope">
            {{ (searchModal.pageNo - 1) * searchModal.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <!-- <el-table-column prop="gno" label="课程号" align="center" width="180">
        </el-table-column> -->
        <el-table-column prop="dCno" label="课程号" align="center" width="180">
        </el-table-column>
        <el-table-column prop="dSno" label="学生号" align="center" width="180">
        </el-table-column>
        <el-table-column prop="gscore" label="学分" align="center" width="180" >
        </el-table-column>
        <el-table-column prop="sgroup" label="组号" align="center" width="180">
        </el-table-column>
        <el-table-column prop="sclass" label="班级" align="center" >
        </el-table-column>
        <el-table-column fixed="right" prop="operator" label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="primary" @click="editDivide(scope.row,scope.$index)" plain>编辑</el-button>
            <el-button type="danger" @click="deleteDivide(scope.row)" plain>删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </el-card>
    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModal.pageNo" :page-sizes="[5, 10, 50, 100]" :page-size="searchModal.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>

    <!-- 新增的对话框 -->
    <!-- 新增表单 -->
    <!-- TODO 3 该表单 -->
    <el-dialog  @close='clearForm' id="add" :title="title" :visible.sync="dialogAddVisible">
      <el-form :model="divideModal" ref='divideFromRef' :rules="rules">
        <!-- <el-form-item label="课程号" prop="gno" :label-width="formLabelWidth">
          <el-input v-if="title == '新增用户'" v-model="divideModal.gno" autocomplete="off"></el-input>
          <el-input v-if="title == '修改用户'" v-model="divideModal.gno" autocomplete="off" disabled></el-input>
        </el-form-item> -->
        <el-form-item label="课程号" prop="dCno" :label-width="formLabelWidth">
          <el-input v-model="divideModal.dCno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学生号" prop="dSno" :label-width="formLabelWidth">
          <el-input v-model="divideModal.dSno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学分" :label-width="formLabelWidth">
          <el-input v-model="divideModal.gscore" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-input v-model="divideModal.sclass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="组号" :label-width="formLabelWidth">
          <el-input v-model="divideModal.sgroup" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取 消</el-button>
        <el-button v-if="this.title == '新增分组'" type="primary" @click="saveDivide('0')">确 定</el-button>
        <el-button v-if="this.title == '修改分组'" type="primary" @click="saveDivide('1')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import divideApi from '@/api/divide'
// import { getList } from '@/api/table';

export default {
  data() {
    return {
      total: 0,
      searchModal: {
        pageNo: 1,
        pageSize: 10
      },
      // TODO2 改类
      divideModal: {
        gno:null,
        dCno:"",
        dSno:"",
        gscore:null,
        sclass:"",
        sgroup:null
      },
      divideList: [],
      dialogAddVisible: false,
      formLabelWidth: "130px",
      formLabelWidth2: "300px",
      title: "",
      rules: {
        // TODO 7 改限制
        // gno: [
        //   { required: true, message: '请输入课程号', trigger: 'blur' },
        //   { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        // ],
        dCno: [
          { required: true, message: '请输入课程号', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        dSno: [
          { required: true, message: '请输入学生号', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        // cname: [
        //   { required: true, message: '请输入姓名', trigger: 'blur' },
        //   { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        // ],
      }
    }
  },
  methods: {
    editDivide(divide,index){
      this.divideModal=divide;
      this.divideList[index] = JSON.parse(JSON.stringify(divide))
      this.openForm('修改分组');
    },
    deleteDivide(divide) {
      this.$confirm('此操作将永久删除此分组, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        divideApi.deleteDivide(divide.gno).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          });
          this.getDivideList();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveDivide(tempid) {
      this.$refs.divideFromRef.validate((valid) => {
        if (valid) {
          //提交验证请
          divideApi.saveDivide(this.divideModal,tempid).then(response => {
            //成功提示  
            if (response.code == 20000) {
              this.$message({
                message: response.message,
                type: 'success'
              });
              //关闭对话框
              this.dialogAddVisible = false;
              //刷新表格
              this.getDivideList();
            }
            else {
              this.$message.error(
                response.message
              );
            }
          });
        }
        else {
          console.log("error submit");
          return false;
        }
      });
    },
    handleSizeChange(pageSize) {
      this.searchModal.pageSize = pageSize;
      this.getDivideList();
    },
    handleCurrentChange(pageNo) {
      this.searchModal.pageNo = pageNo;
      this.getDivideList();
    },
    getDivideList() {
      divideApi.getDivideList(this.searchModal).then(response => {
        this.divideList = response.data.rows;
        this.total = response.data.total;
      });
    },
    openForm(title) {
      this.dialogAddVisible = true;
      this.title = title;
    },
    clearForm() {
      this.divideModal = {};
      this.$refs.divideFromRef.clearValidate();
    }
  },
  // 钩子函数，加载的时候就执行
  created() {
    this.getDivideList();
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