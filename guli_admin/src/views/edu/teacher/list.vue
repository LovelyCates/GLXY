<template>
  <div class="app-container">
    <!-- 查询列表 -->
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="讲师名" />
      </el-form-item>

      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker v-model="teacherQuery.begin" type="datetime" placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss" default-time="00:00:00" />
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="teacherQuery.end" type="datetime" placeholder="选择截止时间" value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00" />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getlist()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    讲师列表
    <!-- 表格 -->
    <!-- 表格 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="数据加载中" border fit highlight-current-row>

      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="名称" width="80" />

      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? '高级讲师' : '首席讲师' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="资历" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/teacher/save/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="delTeacherById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="page" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper" @current-change="getlist" />
  </div>
</template>
<script>
// 引入 teacher.js
import teacher from '@/api/edu/teacher'
export default {
  // 写核心代码
  data() {  // 定义变量和初始值
    return {
      listLoading: false,  // 是否显示loading信息
      list: null,  // 查询之后接口返回集合
      page: 1, // 当前页
      total: 0,  // 总记录数
      limit: 10, // 每页记录数
      teacherQuery: {}  // 条件封装对象
    }
  },
  created() {  // 页面渲染之前执行，一般调用methods定义的方法
    this.getlist()
  },
  methods: {  // 创建具体的方法，调用 teacher.js定义的方法
    // 讲师列表的方法
    getlist(page = 1) {
      this.page = page
      teacher.getTeacherListPage(this.page, this.limit, this.teacherQuery)
        .then(response => {
          // response 接口返回的数据
          // console.log(response)
          this.list = response.data.rows
          this.total = response.data.total
          console.log(this.list)
          console.log(this.total)
        })  // 请求成功
        .catch(error => {
          console.log(error)
        })  // 请求失败
    },
    // 清空查询表单
    resetData() {
      this.teacherQuery = {}
      this.getlist()
    },
    // 根据ID删除讲师
    delTeacherById(id) {
      // 确认？
      this.$confirm('是否删除该老师记录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {  // 确定
        teacher.delTeacherById(id)
          .then(response => {
            // 提示信息
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            // 回到列表页面
            this.getlist()
          })
          .catch(error => {
            this.$message({
              type: 'warning',
              message: "删除失败!"
            })
          })
      })

    },
  },

}

</script>