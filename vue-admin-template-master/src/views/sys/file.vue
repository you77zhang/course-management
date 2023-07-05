
<!-- TODO 改stu为course-->
<!-- TODO 5改Stu为Course -->
<template>
  <div>
    <el-upload class="upload-demo" drag action :auto-upload="false" :on-change="fileUpload" multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件,且不超过20Mb</div>
    </el-upload>
    <el-button type="primary" @click="test()">主要按钮</el-button>
    <router-link target="_blank" :to="{ path: '/manage/course', query: { id: '1' } }">新标签页打开</router-link>
    
    <el-link type="success" @click="test">主要链接</el-link>

    <el-dialog title="提示" :visible.sync="dialogVisible" width="80%" height="100%" :before-close="handleClose">
      <iframe src="https://www.csdn.net/" frameborder="0" width="100%" height="600px"></iframe>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import fileapi from '@/api/file'

export default {
  data() {
    return {
      fileList: [],
      name: "indexJK", 
      dialogVisible: false
    }
  },
  methods: {
    test() {
      this.dialogVisible = true;
    },
    beforeUpload(file) {
      console.log(file);
    },
    fileUpload(file) {
      let fileData = new FormData()
      fileData.append('file', file.raw)
      // file1.append("fileType", 'IMAGE');
      fileapi.upload(fileData).then(response => {
        if (response.code == 20000) {
          this.$message({
            message: response.message,
            type: 'success'
          })
        }
      })
    },
    skip() {
      this.$router.push('/manage/course')
      console.log("跳转成功")
    }
    // upload(file){
    //   const file1 = new FormData()
    //   file1.append("file",file)
    //   fileapi.upload(file1).then(response => {
    //     if(response.code == 20000){
    //       this.$message({
    //         message: response.message,
    //         type:'success'
    //       })
    //     }
    //   })
    // }
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