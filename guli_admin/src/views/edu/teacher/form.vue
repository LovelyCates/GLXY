<template>
    <div class="app-container">
        <!-- 讲师头像 -->
        <el-form-item label="讲师头像">

            <!-- 头衔缩略图 -->
            <pan-thumb :image="teacher.avatar" />
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
                :url="BASE_API + '/admin/minio/file/upload'" field="file" @close="close" @crop-upload-success="cropSuccess" />

        </el-form-item>
    </div>
</template>

<script>
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'



</script>