import request from '@/utils/request'


export default{
    upload(file){
        return request({
            url: '/file/upload',
            method: 'post',
            headers: {
                'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundarynl6gT1BKdPWIejNq'
            },
            data: file
        });
    }
}