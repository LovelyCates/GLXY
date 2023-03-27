<template>
    <div class="app-container">
        添加讲师
        <el-form label-width="120px">
            <el-form-item label="讲师名称">
                <el-input v-model="teacher.name" />
            </el-form-item>
            <el-form-item label="讲师排序">
                <el-input-number v-model="teacher.sort" controls-position="right" :min="0" />
            </el-form-item>
            <el-form-item label="讲师头衔">
                <el-select v-model="teacher.level" clearable placeholder="请选择">
                    <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
               -->
                    <el-option :value="1" label="高级讲师" />
                    <el-option :value="2" label="首席讲师" />
                </el-select>
            </el-form-item>
            <el-form-item label="讲师资历">
                <el-input v-model="teacher.career" />
            </el-form-item>
            <el-form-item label="讲师简介">
                <el-input v-model="teacher.intro" :rows="10" type="textarea" />
            </el-form-item>

            <!-- 讲师头像：TODO -->

            <!-- 讲师头像 -->
            <el-form-item label="讲师头像">
                <!-- 头衔缩略图 -->
                <pan-thumb :image="String(teacher.avatar)" />
                <!-- 文件上传按钮 -->
                <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow = true">更换头像
                </el-button>
                <!--
        v-show：是否显示上传组件
        :key：类似于id，如果一个页面多个图片上传控件，可以做区分
        :url：后台上传的url地址
        @close：关闭上传组件
        @crop-upload-success：上传成功后的回调 -->
                <image-cropper v-show="imagecropperShow" :width="300" :height="300" :key="imagecropperKey"
                    :url="BASE_API + '/eduoss/fileoss/upload'" field="file" @close="close"
                    @crop-upload-success="cropSuccess" />

            </el-form-item>

            <!-- 讲师 - 保存 -->
            <el-form-item>
                <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>
<script>
import teacherApi from '@/api/edu/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
export default {
    components: { ImageCropper, PanThumb, teacherApi },
    data() {
        return {
            teacher: {
                name: '',
                sort: 0,
                level: 1,
                career: '',
                intro: '',
                avatar: ''
            },
            saveBtnDisabled: false, // 保存按钮是否禁用,

            imagecropperKey: 0, // 上传组件唯一标识

            BASE_API: process.env.BASE_API,  // 获取dev.env.js 里面地址

            // 上传弹框组件是否显示
            imagecropperShow: false

        }
    },
    created() {

        this.init()

    },

    watch: { // 监听

        $route(to, from) {

            this.init()

        }  // 路由变化方式, 路由发生变化，就会执行

    },

    methods: {

        // 根据id 查询讲师
        getInfo(id) {
            teacherApi.getTeacherInfo(id).then(response => {
                this.teacher = response.data.item
            })
        },

        // 修改讲师
        updateTeacher() {
            teacherApi.updateTeacherInfo(this.teacher)
                .then(response => {
                    // 提示信息
                    this.$message({
                        type: 'success',
                        message: "修改成功"
                    });
                })
            // 回到列表页面
            this.$router.push({ path: "/edu/teacher/table" })
        },

        close() {  // 关闭上传弹窗的方法
            this.imagecropperShow = false
            // 上传失败后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
            this.imagecropperKey = this.imagecropperKey + 1
        },

        // 上传成功方法
        cropSuccess(data) {
            console.log(data)
            this.imagecropperShow = false
            this.teacher.avatar = data.url
            // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
            this.imagecropperKey = this.imagecropperKey + 1
        },

        saveOrUpdate() {
            // 判断修改还是添加
            // 判断 teacher 中是否有 id
            this.saveBtnDisabled = true

            if (!this.teacher.id)
                this.saveData()
            else
                this.updateTeacher();
        },

        // 保存
        saveData() {
            console.log(this.teacher)
            teacherApi.addTeacher(this.teacher)
                .then(response => {
                    return this.$message({
                        type: 'success',
                        message: '保存成功！'
                    })
                }).then(response => {
                    this.$router.push({ path: '/edu/teacher/table' })
                }).catch((response => {
                    this.$message({
                        type: 'error',
                        message: "保存失败"
                    })
                }))
        },

        init() {
            // 判断路径是否有 id
            if (this.$route.params && this.$route.params.id) {
                // 从路径获取id
                const id = this.$route.params.id
                // 调用根据id查询方法
                this.getInfo(id)
            } else {
                this.teacher = {}
            }
        }

    }
}

</script>