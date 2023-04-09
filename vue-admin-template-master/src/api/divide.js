import request from '@/utils/request'

// TODO 1 改divide为Divide

export default{
    getDivideList(searchModel){
        return request({
            url: '/divide/list',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                // TODO 改
                gno: searchModel.gno,
                sgroup: searchModel.sgroup,
            }
        });
    },
    addDivide(divide){
        return request({
            url: '/divide',
            method: 'post',
            data: divide
        });
    },
    getDivideByGno(id){
        return request({
            // url: '/divide',
            url: `/divide/${id}`,
            method: 'get'
        });
    },
    updateDivide(divide){
        return request({
            url: '/divide',
            method: 'put',
            data: divide
        });
    },
    saveDivide(divide,id){
        if(id == 0) return this.addDivide(divide);
        else if(id == 1) return this.updateDivide(divide);
    },
    deleteDivide(id){
        return request({
            url: '/divide/'+id,
            method: 'delete'
        });
    }
}