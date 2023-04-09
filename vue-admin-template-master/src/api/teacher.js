import request from '@/utils/request'


export default{
    getTeacherList(searchModel){
        return request({
            url: '/teacher/list',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                // TODO 改
                tno: searchModel.tno,
                tname: searchModel.tname,
            }
        });
    },
    addTeacher(teacher){
        return request({
            url: '/teacher',
            method: 'post',
            data: teacher
        });
    },
    getTeacherByTno(id){
        return request({
            // url: '/teacher',
            url: `/teacher/${id}`,
            method: 'get'
        });
    },
    updateTeacher(teacher){
        return request({
            url: '/teacher',
            method: 'put',
            data: teacher
        });
    },
    saveTeacher(teacher,id){
        if(id == 0) return this.addTeacher(teacher);
        else if(id == 1) return this.updateTeacher(teacher);
    },
    deleteTeacher(id){
        return request({
            url: '/teacher/'+id,
            method: 'delete'
        });
    },
    getTeachCList(searchModel){
        return request({
            url: '/teacher/tcList',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                // TODO 改
                tno: searchModel.tno,
                tname: searchModel.tname,
            }
        });
    },
}