'use client'


import CardButton from "@/app/atoms/button/CardButton";
import { IBoard } from "@/app/component/board/model/board";
import { findAllBoards } from "@/app/component/board/service/board-service";
import { getAllBoards } from "@/app/component/board/service/board-slice";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function BoardCards(){
    const dispatch = useDispatch()
    const allBoards = useSelector(getAllBoards)

    useEffect(() => {
        dispatch(findAllBoards(1))
    }, [])

    return (<>
    <h1>게시판 목록 들어옴</h1>
    {allBoards.map((elem: { title: any; description: any; })=>(elem.title,elem.description))}
    </>)
}

