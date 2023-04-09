import request from '@/utils/request'

// TODO 1 改course为Course

export default{
    getCourseList(searchModel){
        return request({
            url: '/course/list',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                // TODO 改
                cno: searchModel.cno,
                cname: searchModel.cname,
            }
        });
    },
    addCourse(course){
        return request({
            url: '/course',
            method: 'post',
            data: course
        });
    },
    getCourseByCno(id){
        return request({
            // url: '/course',
            url: `/course/${id}`,
            method: 'get'
        });
    },
    updateCourse(course){
        return request({
            url: '/course',
            method: 'put',
            data: course
        });
    },
    saveCourse(course,id){
        if(id == 0) return this.addCourse(course);
        else if(id == 1) return this.updateCourse(course);
    },
    deleteCourse(id){
        return request({
            url: '/course/'+id,
            method: 'delete'
        });
    }
}