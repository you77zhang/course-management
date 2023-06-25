
<!-- TODO 改stu为course-->
<!-- TODO 5改Stu为Course -->
<template>
  <div>
    <el-upload class="upload-demo"
    drag 
    action
    :auto-upload="false"
    :on-change="fileUpload"
    multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件,且不超过20Mb</div>
    </el-upload>


    <!-- <el-upload action="" multiple  :http-request="fileUpload">
      <el-button type="primary">Click to upload</el-button>
      <template #tip>
        <div class="el-upload__tip">
          jpg/png files with a size less than 500KB.
        </div>
      </template>
    </el-upload> -->


    <!-- <el-upload action :show-file-list="false" :auto-upload="false" :before-upload="beforeUpload" :on-change="fileUpload"
      style="display: inline-block;">
      <Button icon="ios-download" type="primary">导入</Button>
    </el-upload> -->
  </div>
</template>

<script>
import fileapi from '@/api/file'

export default {
  data() {
    return {
      fileList: []
    }
  },
  methods: {
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