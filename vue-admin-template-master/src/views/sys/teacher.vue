
<!-- TODO 改stu为teacher-->
<!-- TODO 5改Stu为Teacher -->
<template>
  <div id="main">
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="22">
          <el-input v-model="searchModal.tno" placeholder="工号" clearable></el-input>
          <el-input v-model="searchModal.tname" placeholder="姓名" clearable></el-input>
          <el-button @click="getTeacherList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
        <!-- 新增按钮 -->
        <el-col :span="2" align="right">
          <el-button @click="openForm('新增用户')" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>
    <!-- 结果列表 -->
    <!-- TODO 4 改表格 -->
    <el-card id="list">
      <el-table :data="teacherList" stripe style="width: 100%">
        <el-table-column prop="date" align="center" label="#" width="80">
          <!-- (pageNo-1)*pageSize+index+1 -->
          <template slot-scope="scope">
            {{ (searchModal.pageNo - 1) * searchModal.pageSize + scope.$index + 1 }}
          </template>

        </el-table-column>
        <el-table-column prop="tno" label="教工号" align="center" width="180">
        </el-table-column>
        <el-table-column prop="tname" label="姓名" align="center" width="180">
        </el-table-column>
        <el-table-column prop="tsex" label="性别" align="center" width="180">
        </el-table-column>
        <el-table-column prop="tdept" label="学院" align="center">
        </el-table-column>
        <el-table-column fixed="right" prop="operator" label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="primary" @click="editTeacher(scope.row,scope.$index)" plain>编辑</el-button>
            <el-button type="danger" @click="deleteTeacher(scope.row)" plain>删除</el-button>
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
    <el-dialog @close='clearForm' id="add" :title="title" :visible.sync="dialogAddVisible">
      <el-form :model="teacherModal" ref='teacherFromRef' :rules="rules">
        <el-form-item label="教师号" prop="tno" :label-width="formLabelWidth">
          <el-input v-if="title == '新增用户'" v-model="teacherModal.tno" autocomplete="off"></el-input>
          <el-input v-if="title == '修改用户'" v-model="teacherModal.tno" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="tname" :label-width="formLabelWidth">
          <el-input v-model="teacherModal.tname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="teacherModal.tsex" placeholder="请选择班级" autocomplete="off">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
            <el-option label="不告诉你" value=""></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="学院" :label-width="formLabelWidth">
          <el-select v-model="teacherModal.tdept" placeholder="请选择学院" autocomplete="off">
            <el-option label="计算机学院" value="计算机学院"></el-option>
            <el-option label="底特律学院" value="底特律学院"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取 消</el-button>
        <el-button v-if="this.title == '新增用户'" type="primary" @click="saveTeacher('0')">确 定</el-button>
        <el-button v-if="this.title == '修改用户'" type="primary" @click="saveTeacher('1')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import teacherApi from '@/api/teacher'
import teachCVue from './teachC.vue';
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
      teacherModal: {
        tno: "",
        tname: "",
        tsex: "",
        tdept: ""
      },
      teacherList: [],
      dialogAddVisible: false,
      formLabelWidth: "130px",
      formLabelWidth2: "300px",
      title: "",
      rules: {
        // TODO 改限制
        tno: [
          { required: true, message: '请输入工号', trigger: 'blur' },
          { min: 3, max: 11, message: '长度在 3 到 11 个字符', trigger: 'blur' }
        ],
        tname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    editTeacher(teacher,index) {
      this.teacherModal = teacher;
      this.teacherList[index] = JSON.parse(JSON.stringify(teacher))
      this.openForm('修改用户');
    },
    deleteTeacher(teacher) {
      this.$confirm('此操作将永久删除 ' + teacher.tname + ' , 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        teacherApi.deleteTeacher(teacher.tno).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          });
          this.getTeacherList();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveTeacher(tempid) {
      this.$refs.teacherFromRef.validate((valid) => {
        if (valid) {
          //提交验证请
          teacherApi.saveTeacher(this.teacherModal, tempid).then(response => {
            //成功提示  
            if (response.code == 20000) {
              this.$message({
                message: response.message,
                type: 'success'
              });
              //关闭对话框
              this.dialogAddVisible = false;
              //刷新表格
              this.getTeacherList();
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
      this.getTeacherList();
    },
    handleCurrentChange(pageNo) {
      this.searchModal.pageNo = pageNo;
      this.getTeacherList();
    },
    getTeacherList() {
      teacherApi.getTeacherList(this.searchModal).then(response => {
        this.teacherList = response.data.rows;
        this.total = response.data.total;
      });
    },
    openForm(title) {
      this.dialogAddVisible = true;
      this.title = title;
    },
    clearForm() {
      this.teacherModal = {};
      this.$refs.teacherFromRef.clearValidate();
    }
  },
  // 钩子函数，加载的时候就执行
  created() {
    this.getTeacherList();
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