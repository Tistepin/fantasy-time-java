<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="章节ID" prop="worksChapterId">
      <el-input v-model="dataForm.worksChapterId" placeholder="章节ID"></el-input>
    </el-form-item>
    <el-form-item label="用户ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户ID"></el-input>
    </el-form-item>
    <el-form-item label="作品id" prop="worksId">
      <el-input v-model="dataForm.worksId" placeholder="作品id"></el-input>
    </el-form-item>
    <el-form-item label="该画画作品的该章节的第几个图片" prop="imageId">
      <el-input v-model="dataForm.imageId" placeholder="该画画作品的该章节的第几个图片"></el-input>
    </el-form-item>
    <el-form-item label="审核状态 0-审核中 1-审核成功 2-审核失败" prop="reviewStatus">
      <el-input v-model="dataForm.reviewStatus" placeholder="审核状态 0-审核中 1-审核成功 2-审核失败"></el-input>
    </el-form-item>
    <el-form-item label="逻辑删除状态 0-删除 1-存在" prop="deleteStatus">
      <el-input v-model="dataForm.deleteStatus" placeholder="逻辑删除状态 0-删除 1-存在"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="修改时间" prop="editTime">
      <el-input v-model="dataForm.editTime" placeholder="修改时间"></el-input>
    </el-form-item>
    <el-form-item label="章节数据存储位置" prop="worksChapterLocation">
      <el-input v-model="dataForm.worksChapterLocation" placeholder="章节数据存储位置"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          worksChapterId: '',
          userId: '',
          worksId: '',
          imageId: '',
          reviewStatus: '',
          deleteStatus: '',
          createTime: '',
          editTime: '',
          worksChapterLocation: ''
        },
        dataRule: {
          worksChapterId: [
            { required: true, message: '章节ID不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          worksId: [
            { required: true, message: '作品id不能为空', trigger: 'blur' }
          ],
          imageId: [
            { required: true, message: '该画画作品的该章节的第几个图片不能为空', trigger: 'blur' }
          ],
          reviewStatus: [
            { required: true, message: '审核状态 0-审核中 1-审核成功 2-审核失败不能为空', trigger: 'blur' }
          ],
          deleteStatus: [
            { required: true, message: '逻辑删除状态 0-删除 1-存在不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          editTime: [
            { required: true, message: '修改时间不能为空', trigger: 'blur' }
          ],
          worksChapterLocation: [
            { required: true, message: '章节数据存储位置不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/search/workschapterdetailedviewingcontent/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.worksChapterId = data.worksChapterDetailedViewingContent.worksChapterId
                this.dataForm.userId = data.worksChapterDetailedViewingContent.userId
                this.dataForm.worksId = data.worksChapterDetailedViewingContent.worksId
                this.dataForm.imageId = data.worksChapterDetailedViewingContent.imageId
                this.dataForm.reviewStatus = data.worksChapterDetailedViewingContent.reviewStatus
                this.dataForm.deleteStatus = data.worksChapterDetailedViewingContent.deleteStatus
                this.dataForm.createTime = data.worksChapterDetailedViewingContent.createTime
                this.dataForm.editTime = data.worksChapterDetailedViewingContent.editTime
                this.dataForm.worksChapterLocation = data.worksChapterDetailedViewingContent.worksChapterLocation
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/search/workschapterdetailedviewingcontent/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'worksChapterId': this.dataForm.worksChapterId,
                'userId': this.dataForm.userId,
                'worksId': this.dataForm.worksId,
                'imageId': this.dataForm.imageId,
                'reviewStatus': this.dataForm.reviewStatus,
                'deleteStatus': this.dataForm.deleteStatus,
                'createTime': this.dataForm.createTime,
                'editTime': this.dataForm.editTime,
                'worksChapterLocation': this.dataForm.worksChapterLocation
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
