import request from '@/utils/request'

export default {
    // 1. 讲师列表 条件分页查询
    /**
     * 
     * @param {当前页} current 
     * @param {每页记录数} limit 
     * @param {teacherqUERY 条件对象} teacherQuery 
     * @returns 
     */
    getTeacherListPage(current, limit, teacherQuery){
        return request({
            // url: '/table/list/' + current + "/" + limit,
            url: `eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
           // teacherQuery 条件对象，后端使用 RequestBody 获取数据
           // data 表示把对象 转换成 json 传递到接口
           data: teacherQuery
          })
    },

    /**
     * 
     * @param {讲师id} id 
     * @returns 
     */
    delTeacherById(id){
        return request({
            // url: '/table/list/' + current + "/" + limit,
            url: `eduservice/teacher/${id}`,
            method: 'delete',
          })
    },

    /**
     * 
     * @param {讲师详细信息} teacher 
     * @returns 
     */
    addTeacher(teacher) {
        return request({
            url: `eduservice/teacher/save`,
            method: 'post',
            data: teacher
        })
    },

    /**
     * 根据 id 查询讲师
     * @param {讲师id} id 
     * @returns 
     */
    getTeacherInfo(id){
        return request({
            url: `/eduservice/teacher/getTeacher/${id}`,
            method: 'post'
        })
    },


    /**
     * 
     * @param {需要修改的讲师信息对象} teacher 
     * @returns 
     */
    updateTeacherInfo(teacher){
        return request({
            url: `/eduservice/teacher/updateTeacher`,
            method: 'post',
            data: teacher
        })
    }
}

