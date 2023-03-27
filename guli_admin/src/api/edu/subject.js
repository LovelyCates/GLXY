import request from '@/utils/request'

export default {

    /**
     * 
     * @returns 获得树形列表
     */
    getSubjectList(){
        return request({
            url: `/eduservice/subject/getAllSubject`,
            method: 'get',
          })
    },
}

