import AxiosConfig from "@/redux/common/configs/axios-config"
import instance from "../../common/configs/axios-config"

export const findAllArticlesAPI = async (page:any) =>{
    try{
        const response = await instance().get('/articles/list',{
            params: {page, limit: 10}
        })
        return response.data
    }catch(error){
        console.log(error)
        return error
    }
    
}

export const findArticleByIdAPI = async (id: number) =>{
    console.log('여기는 api'+id)
    try{
        const response = await instance().get('/articles/detail',{
            params: {id}
        })
        return response.data
    }catch(error){
        console.log(error)
        return error
    }   
}


export const saveAPI = async (articles: any) =>{
    try{
        const response = await instance().post('/articles/save',articles)
        console.log(JSON.stringify(articles))
        console.log('1111111111111111111'+articles)
        return response.data
    }catch(error){
        console.log(error)
        return error
    }   
}
