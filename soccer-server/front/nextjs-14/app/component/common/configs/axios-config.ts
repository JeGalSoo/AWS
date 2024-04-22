import AxiosConfig from '@/redux/common/configs/axios-config'
import { ConstructionOutlined } from '@mui/icons-material'
import axios from 'axios'
import { Console } from 'console'
import { parseCookies } from 'nookies'

// export default function AxiosConfig(){
//     return {
//         headers: {
//             "Cache-Control": "no-cache",
//             "Content-Type": "application/json",
//             Authorization: `Bearer blah ~`,
//             "Access-Control-Allow-Origin": "*",
//         }
//     }
// }


const instance = axios.create({ baseURL: process.env.NEXT_PUBLIC_API_URL})

instance.interceptors.request.use(
    (config)=>{
        const accessToken = parseCookies().accessToken;
        console.log('axios interceptor가 cookie에서 token을 추출함' + accessToken)
        config.headers["Content-Type"] = "application/json"
        config.headers['Authorization'] = `Bearer ${accessToken}`
        return config
    },
    (error)=>{
        console.log('axios interceptor의 request에서 발생한 에러부분 : '+error)
        return Promise.reject(error)
    }
)

instance.interceptors.response.use(
    (response)=>{
        if(response.status === 404){
            console.log('axios interceptor의 response에서 발생한 에러부분 : 토큰이 없어서 404로 넘어감')
        }
        return response
    },
)


export default instance