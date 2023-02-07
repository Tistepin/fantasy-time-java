<template>
  <el-dialog
    :title="!dataForm.worksId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="作品名" prop="worksName">
      <el-input v-model="dataForm.worksName" placeholder="作品名"></el-input>
    </el-form-item>
    <el-form-item label="作品默认展示图片" prop="defaultImage">
      <el-input v-model="dataForm.defaultImage" placeholder="作品默认展示图片"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="creator">
      <el-input v-model="dataForm.creator" placeholder="创建人"></el-input>
    </el-form-item>
    <el-form-item label="作品创作者" prop="worksCreator">
      <el-input v-model="dataForm.worksCreator" placeholder="作品创作者"></el-input>
    </el-form-item>
    <el-form-item label="作品创作时间" prop="worksCreateTime">
      <el-input v-model="dataForm.worksCreateTime" placeholder="作品创作时间"></el-input>
    </el-form-item>
    <el-form-item label="作品创作地区" prop="worksArea">
      <el-input v-model="dataForm.worksArea" placeholder="作品创作地区"></el-input>
    </el-form-item>
    <el-form-item label="作品类型 1-漫画 2-小说" prop="worksType">
      <el-input v-model="dataForm.worksType" placeholder="作品类型 1-漫画 2-小说"></el-input>
    </el-form-item>
    <el-form-item label="作品评分 10分满分" prop="worksScore">
      <el-input v-model="dataForm.worksScore" placeholder="作品评分 10分满分"></el-input>
    </el-form-item>
    <el-form-item label="作品更新至多少" prop="worksRenew">
      <el-input v-model="dataForm.worksRenew" placeholder="作品更新至多少"></el-input>
    </el-form-item>
    <el-form-item label="作品人气 用户阅读10章以上为1" prop="worksPopularity">
      <el-input v-model="dataForm.worksPopularity" placeholder="作品人气 用户阅读10章以上为1"></el-input>
    </el-form-item>
    <el-form-item label="作品描述" prop="worksDescribe">
      <el-input v-model="dataForm.worksDescribe" placeholder="作品描述"></el-input>
    </el-form-item>
    <el-form-item label="作品分类" prop="worksCategory">
      <el-input v-model="dataForm.worksCategory" placeholder="作品分类"></el-input>
    </el-form-item>
    <el-form-item label="作品状态 1-可以看 2-不可以看" prop="status">
      <el-input v-model="dataForm.status" placeholder="作品状态 1-可以看 2-不可以看"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="修改时间" prop="editTime">
      <el-input v-model="dataForm.editTime" placeholder="修改时间"></el-input>
    </el-form-item>
    <el-form-item label="逻辑删除状态 0-已删除 1-未删除" prop="deleteStatus">
      <el-input v-model="dataForm.deleteStatus" placeholder="逻辑删除状态 0-已删除 1-未删除"></el-input>
    </el-form-item>
    <el-form-item label="作品状态 1-更新中 2-完结" prop="worksStatus">
      <el-input v-model="dataForm.worksStatus" placeholder="作品状态 1-更新中 2-完结"></el-input>
    </el-form-item>
    <el-form-item label="review_status审核状态 0-审核中 1-审核成功 2-审核失败" prop="reviewStatus">
      <el-input v-model="dataForm.reviewStatus" placeholder="review_status审核状态 0-审核中 1-审核成功 2-审核失败"></el-input>
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
          worksId: 0,
          worksName: '',
          defaultImage: '',
          creator: '',
          worksCreator: '',
          worksCreateTime: '',
          worksArea: '',
          worksType: '',
          worksScore: '',
          worksRenew: '',
          worksPopularity: '',
          worksDescribe: '',
          worksCategory: '',
          status: '',
          createTime: '',
          editTime: '',
          deleteStatus: '',
          worksStatus: '',
          reviewStatus: ''
        },
        dataRule: {
          worksName: [
            { required: true, message: '作品名不能为空', trigger: 'blur' }
          ],
          defaultImage: [
            { required: true, message: '作品默认展示图片不能为空', trigger: 'blur' }
          ],
          creator: [
            { required: true, message: '创建人不能为空', trigger: 'blur' }
          ],
          worksCreator: [
            { required: true, message: '作品创作者不能为空', trigger: 'blur' }
          ],
          worksCreateTime: [
            { required: true, message: '作品创作时间不能为空', trigger: 'blur' }
          ],
          worksArea: [
            { required: true, message: '作品创作地区不能为空', trigger: 'blur' }
          ],
          worksType: [
            { required: true, message: '作品类型 1-漫画 2-小说不能为空', trigger: 'blur' }
          ],
          worksScore: [
            { required: true, message: '作品评分 10分满分不能为空', trigger: 'blur' }
          ],
          worksRenew: [
            { required: true, message: '作品更新至多少不能为空', trigger: 'blur' }
          ],
          worksPopularity: [
            { required: true, message: '作品人气 用户阅读10章以上为1不能为空', trigger: 'blur' }
          ],
          worksDescribe: [
            { required: true, message: '作品描述不能为空', trigger: 'blur' }
          ],
          worksCategory: [
            { required: true, message: '作品分类不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '作品状态 1-可以看 2-不可以看不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          editTime: [
            { required: true, message: '修改时间不能为空', trigger: 'blur' }
          ],
          deleteStatus: [
            { required: true, message: '逻辑删除状态 0-已删除 1-未删除不能为空', trigger: 'blur' }
          ],
          worksStatus: [
            { required: true, message: '作品状态 1-更新中 2-完结不能为空', trigger: 'blur' }
          ],
          reviewStatus: [
            { required: true, message: 'review_status审核状态 0-审核中 1-审核成功 2-审核失败不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.worksId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.worksId) {
            this.$http({
              url: this.$http.adornUrl(`/works/works/info/${this.dataForm.worksId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.worksName = data.works.worksName
                this.dataForm.defaultImage = data.works.defaultImage
                this.dataForm.creator = data.works.creator
                this.dataForm.worksCreator = data.works.worksCreator
                this.dataForm.worksCreateTime = data.works.worksCreateTime
                this.dataForm.worksArea = data.works.worksArea
                this.dataForm.worksType = data.works.worksType
                this.dataForm.worksScore = data.works.worksScore
                this.dataForm.worksRenew = data.works.worksRenew
                this.dataForm.worksPopularity = data.works.worksPopularity
                this.dataForm.worksDescribe = data.works.worksDescribe
                this.dataForm.worksCategory = data.works.worksCategory
                this.dataForm.status = data.works.status
                this.dataForm.createTime = data.works.createTime
                this.dataForm.editTime = data.works.editTime
                this.dataForm.deleteStatus = data.works.deleteStatus
                this.dataForm.worksStatus = data.works.worksStatus
                this.dataForm.reviewStatus = data.works.reviewStatus
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
              url: this.$http.adornUrl(`/works/works/${!this.dataForm.worksId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'worksId': this.dataForm.worksId || undefined,
                'worksName': this.dataForm.worksName,
                'defaultImage': this.dataForm.defaultImage,
                'creator': this.dataForm.creator,
                'worksCreator': this.dataForm.worksCreator,
                'worksCreateTime': this.dataForm.worksCreateTime,
                'worksArea': this.dataForm.worksArea,
                'worksType': this.dataForm.worksType,
                'worksScore': this.dataForm.worksScore,
                'worksRenew': this.dataForm.worksRenew,
                'worksPopularity': this.dataForm.worksPopularity,
                'worksDescribe': this.dataForm.worksDescribe,
                'worksCategory': this.dataForm.worksCategory,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'editTime': this.dataForm.editTime,
                'deleteStatus': this.dataForm.deleteStatus,
                'worksStatus': this.dataForm.worksStatus,
                'reviewStatus': this.dataForm.reviewStatus
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
