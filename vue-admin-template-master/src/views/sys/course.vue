
<!-- TODO 改stu为course-->
<!-- TODO 5改Stu为Course -->
<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="22">
          <!-- TODO 6 改这里的 -->
          <el-input v-model="searchModal.cno" placeholder="课程号" clearable></el-input>
          <el-input v-model="searchModal.cname" placeholder="课程名" clearable></el-input>
          <el-button @click="getCourseList" type="primary" icon="el-icon-search" round>查询</el-button>
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
      <el-table :data="courseList" stripe style="width: 100%">
        <el-table-column prop="date" align="center" label="#" width="80">
          <!-- (pageNo-1)*pageSize+index+1 -->
          <template slot-scope="scope">
            {{ (searchModal.pageNo - 1) * searchModal.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="cno" label="课程号" align="center" width="180">
        </el-table-column>
        <el-table-column prop="cname" label="课程名" align="center" width="180">
        </el-table-column>
        <el-table-column prop="credit" label="学分" align="center" width="180">
        </el-table-column>
        <el-table-column prop="cTno" label="授课老师工号" align="center">
        </el-table-column>
        <el-table-column prop="cterm" label="学期" align="center">
        </el-table-column>
        <el-table-column fixed="right" prop="operator" label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="primary" @click="editCourse(scope.row,scope.$index)" plain>编辑</el-button>
            <el-button type="danger" @click="deleteCourse(scope.row)" plain>删除</el-button>
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
      <el-form :model="courseModal" ref='courseFromRef' :rules="rules">
        <el-form-item label="课程号" prop="cno" :label-width="formLabelWidth">
          <el-input v-if="title == '新增用户'" v-model="courseModal.cno" autocomplete="off"></el-input>
          <el-input v-if="title == '修改用户'" v-model="courseModal.cno" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="名字" prop="cname" :label-width="formLabelWidth">
          <el-input v-model="courseModal.cname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学分" :label-width="formLabelWidth">
          <el-input v-model="courseModal.credit" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="授课老师工号" prop="cTno" :label-width="formLabelWidth">
          <el-input v-model="courseModal.cTno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学期" :label-width="formLabelWidth">
          <el-input v-model="courseModal.cterm" autocomplete="off"></el-input>
        </el-form-item>

        <!-- <el-form-item label="活动时间" :label-width="formLabelWidth">
          <el-col :span="11">
            <el-form-item prop="date1">
              <el-date-picker type="date" placeholder="选择日期" v-model="courseModal.cterm"
                style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="1">-</el-col>
          <el-col :span="12">
            <el-form-item prop="date2">
              <el-date-picker placeholder="选择时间" v-model="courseModal.cterm" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item> -->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取 消</el-button>
        <el-button v-if="this.title == '新增用户'" type="primary" @click="saveCourse('0')">确 定</el-button>
        <el-button v-if="this.title == '修改用户'" type="primary" @click="saveCourse('1')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import courseApi from '@/api/course'
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
      courseModal: {
        cno: "",
        cTno: "",
        credit: "",
        cname: "",
        cterm: ""
      },
      courseList: [],
      dialogAddVisible: false,
      formLabelWidth: "130px",
      formLabelWidth2: "300px",
      title: "",
      rules: {
        // TODO 改限制
        cno: [
          { required: true, message: '请输入课程号', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        cTno: [
          { required: true, message: '请输入教师工号', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        cname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    editCourse(course,index) {
      this.courseModal = course;
      this.courseList[index] = JSON.parse(JSON.stringify(course))
      this.openForm('修改用户');
    },
    deleteCourse(course) {
      this.$confirm('此操作将永久删除 ' + course.cname + ' , 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        courseApi.deleteCourse(course.cno).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          });
          this.getCourseList();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveCourse(tempid) {
      this.$refs.courseFromRef.validate((valid) => {
        if (valid) {
          //提交验证请
          courseApi.saveCourse(this.courseModal, tempid).then(response => {
            //成功提示  
            if (response.code == 20000) {
              this.$message({
                message: response.message,
                type: 'success'
              });
              //关闭对话框
              this.dialogAddVisible = false;
              //刷新表格
              this.getCourseList();
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
      this.getCourseList();
    },
    handleCurrentChange(pageNo) {
      this.searchModal.pageNo = pageNo;
      this.getCourseList();
    },
    getCourseList() {
      courseApi.getCourseList(this.searchModal).then(response => {
        this.courseList = response.data.rows;
        this.total = response.data.total;
      });
    },
    openForm(title) {
      this.dialogAddVisible = true;
      this.title = title;
    },
    clearForm() {
      this.courseModal = {};
      this.$refs.courseFromRef.clearValidate();
    }
  },
  // 钩子函数，加载的时候就执行
  created() {
    this.getCourseList();
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