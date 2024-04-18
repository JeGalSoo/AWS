'use client'

import { useEffect, useState } from "react"
import axios from 'axios';
import Link from "next/link";
import './globals.css'
import { Button, Input } from "@mui/material";
import { NextPage } from "next";
import { PG } from "@/redux/common/enums/PG";
import { API } from "@/redux/common/enums/API";
import AxiosConfig from "./component/common/configs/axios-config";
import { loginUser } from "./component/users/service/user.service";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { getAuth } from "./component/users/service/user.slice";
import { useRouter } from "next/navigation";
import { IUser } from "./component/users/model/user";
import nookies,{parseCookies,destroyCookie,setCookie} from 'nookies';





const SERVER = 'http://localhost:8080'
const HomePage:NextPage=()=> {
  const dispatch = useDispatch()
  const logindata= useSelector(getAuth)
 
  const handleClick = ()=> {
    alert('리퀘스트가 가져가는 이름 : '+name)
    const url = `${SERVER}/name`
    const data = {'name': name}
    const config = {
      headers:{
        "Cache-Control": "no-cache",
        "Content-Type": "application/json",
         Authorization: `Bearer blah ~` ,
        "Access-Control-Allow-Origin": "*",
    }}
    axios.post(url,data,config)
    .then(res=>{
      alert('alert : '+JSON.stringify(res.data))
      console.log('console : '+JSON.stringify(res.data))
    }
      )
  }
  const [user, setUser] = useState({} as IUser)//뜻 : IUser user = new IUser


  const handleUsername = (e: any) => {
      setUser({
        ...user,username:e.target.value})
  }

  const handlePassword = (e: any) => {
    setUser({
      ...user,password:e.target.value})
  }
      const router = useRouter()

      useEffect(() => {
        console.log('113131313131')
        console.log(logindata.message)
        if(logindata.message==="SUCCESS"){
          setCookie({},'message',logindata.message,{ httpOnly: false, path: '/' })
          setCookie({},'token',logindata.token,{ httpOnly: false, path: '/' })
          console.log('서버에서 넘어온 메시지'+parseCookies().message)
          console.log('서버에서 넘어온 토큰'+parseCookies().token)
        router.push(`${PG.BOARD}/list`)
      }else{
        console.log('LOGIN FAIL')
      }
    }, [logindata])

  const handleSubmit = () => {
    dispatch(loginUser(user))
    console.log("000000")
    console.log(logindata)
      // axios.post(url, data, AxiosConfig())
      //     .then(res => { 
      //         const message=res.data.message
      //         alert(message)
      //         if(message === 'SUCCESS'){
                                      
      //          }else if (message === 'FAIL'){
      //          }else if (message === 'WRONG._PASSWORD'){
      //          }else{
      //          }
      //     })
  }


  return (
    <div className="flex items-center justify-center h-screen w-full px-5 sm:px-0">
      <div className="flex bg-white rounded-lg shadow-lg border overflow-hidden max-w-sm lg:max-w-4xl w-full">
        <div
          className="hidden md:block lg:w-1/2 bg-cover bg-blue-700"
          style={{
            backgroundImage: `url(https://www.tailwindtap.com//assets/components/form/userlogin/login_tailwindtap.jpg)`,
          }}
        ></div>
        <div className="w-full p-8 lg:w-1/2">
          <p className="text-xl text-gray-600 text-center">Welcome back!</p>
          <div className="mt-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
            아이디를 입력하세요
            </label>
            <input
              className="text-gray-700 border border-gray-300 rounded py-2 px-4 block w-full focus:outline-2 focus:outline-blue-700"
              type="email"
              onChange={handleUsername}
              required
            />
          </div>
          <div className="mt-4 flex flex-col justify-between">
            <div className="flex justify-between">
              <label className="block text-gray-700 text-sm font-bold mb-2">
              비밀번호를 입력하세요
              </label>
            </div>
            <input
              className="text-gray-700 border border-gray-300 rounded py-2 px-4 block w-full focus:outline-2 focus:outline-blue-700"
              onChange={handlePassword}
              type="password"
            />
            <a
              href="#"
              className="text-xs text-gray-500 hover:text-gray-900 text-end w-full mt-2"
            >
              Forget Password?
            </a>
          </div>
          <div className="mt-8">
            <button className="bg-blue-700 text-white font-bold py-2 px-4 w-full rounded hover:bg-blue-600" onClick={handleSubmit}>
              Login
            </button>
          </div>
          <a
            href="#"
            className=" flex items-center justify-center mt-4 text-white rounded-lg shadow-md hover:bg-gray-100"
          >
            <div className="flex px-5 justify-center w-full py-3">
              <div className="min-w-[30px]">
                <svg className="h-6 w-6" viewBox="0 0 40 40">
                  <path
                    d="M36.3425 16.7358H35V16.6667H20V23.3333H29.4192C28.045 27.2142 24.3525 30 20 30C14.4775 30 10 25.5225 10 20C10 14.4775 14.4775 9.99999 20 9.99999C22.5492 9.99999 24.8683 10.9617 26.6342 12.5325L31.3483 7.81833C28.3717 5.04416 24.39 3.33333 20 3.33333C10.7958 3.33333 3.33335 10.7958 3.33335 20C3.33335 29.2042 10.7958 36.6667 20 36.6667C29.2042 36.6667 36.6667 29.2042 36.6667 20C36.6667 18.8825 36.5517 17.7917 36.3425 16.7358Z"
                    fill="#FFC107"
                  />
                  <path
                    d="M5.25497 12.2425L10.7308 16.2583C12.2125 12.59 15.8008 9.99999 20 9.99999C22.5491 9.99999 24.8683 10.9617 26.6341 12.5325L31.3483 7.81833C28.3716 5.04416 24.39 3.33333 20 3.33333C13.5983 3.33333 8.04663 6.94749 5.25497 12.2425Z"
                    fill="#FF3D00"
                  />
                  <path
                    d="M20 36.6667C24.305 36.6667 28.2167 35.0192 31.1742 32.34L26.0159 27.975C24.3425 29.2425 22.2625 30 20 30C15.665 30 11.9842 27.2359 10.5975 23.3784L5.16254 27.5659C7.92087 32.9634 13.5225 36.6667 20 36.6667Z"
                    fill="#4CAF50"
                  />
                  <path
                    d="M36.3425 16.7358H35V16.6667H20V23.3333H29.4192C28.7592 25.1975 27.56 26.805 26.0133 27.9758C26.0142 27.975 26.015 27.975 26.0158 27.9742L31.1742 32.3392C30.8092 32.6708 36.6667 28.3333 36.6667 20C36.6667 18.8825 36.5517 17.7917 36.3425 16.7358Z"
                    fill="#1976D2"
                  />
                </svg>
              </div>
              <div className="flex w-full justify-center">
                <h1 className="whitespace-nowrap text-gray-600 font-bold">
                  Sign in with Google
                </h1>
              </div>
            </div>
          </a>
          <div className="mt-4 flex items-center w-full text-center">
            <Link
              href="/pages/users/join"
              className="text-xs text-gray-500 capitalize text-center w-full"
            >
              Don&apos;t have any account yet?
              <span className="text-blue-700"> Sign Up</span>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );

  // return (<div className='text-center'>
  // <div>welcom to react world !</div><br />
  // <h3 className='text-red-500'>이름 입력</h3><br />
  // <h3>사이트</h3>
  //   <Link href={PG.USER}>user</Link><br/>
  //   <Link href={"/store"} >store</Link><br/>
  //   <Link href={PG.ARTICLE} >articles</Link><br/>
  //   <Link href={PG.BOARD} >board</Link><br/>
  //   <Link href={PG.DEMO+"/companys"} >companys</Link><br/>
  //   <Link href={PG.DEMO+"/mui-demo"} >mui-demo</Link><br/>
  //   <Link href={PG.DEMO+"/counter"} >demo</Link><br/>
  //   <Link href={PG.DEMO+"/redux-counter"} >redux-counter</Link>
  // </div>)


}
export default HomePage