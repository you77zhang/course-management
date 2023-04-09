import request from '@/utils/request'


export default{
    getStuList(searchModel){
        return request({
            url: '/stu/list',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                sno: searchModel.sno,
                sname: searchModel.sname,
            }
        });
    },
    addStu(stu){
        return request({
            url: '/stu',
            method: 'post',
            data: stu
        });
    },
    getStuBySno(id){
        return request({
            // url: '/stu',
            url: `/stu/${id}`,
            method: 'get'
        });
    },
    updateStu(stu){
        return request({
            url: '/stu',
            method: 'put',
            data: stu
        });
    },
    saveStu(stu,id){
        if(id == 0) return this.addStu(stu);
        else if(id == 1) return this.updateStu(stu);
    },
    deleteStu(id){
        return request({
            url: '/stu/'+id,
            method: 'delete'
        });
    },
    getSCList(searchModel){
        return request({
            url: '/stu/sclist',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                sno: searchModel.sno,
                sname: searchModel.sname,
                sclass: searchModel.sclass,
            }
        });
    }
}