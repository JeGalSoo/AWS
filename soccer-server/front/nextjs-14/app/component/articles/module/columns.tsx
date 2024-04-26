import { Button, Typography } from "@mui/material";
import { GridColDef } from "@mui/x-data-grid";
import { ArticleColumn } from "../model/aritlcle-colums";
import Link from "next/link";
import { PG } from "@/redux/common/enums/PG";
import { useDispatch, useSelector } from "react-redux";
import { deleteArticleById } from "../service/article.service";
import { useState } from "react";
import {useForm} from 'react-hook-form'

interface CellType{
    row : ArticleColumn
}

export default function ArticleColumns(): GridColDef[]{
    const {register, handleSubmit, formState:{errors}} = useForm();
    const dispatch = useDispatch()
    const [id,setId] = useState(Number)

    const handleDelete = ()=>{
        console.log((id))
        dispatch(deleteArticleById({id}))
    }
   
    return [
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'id',
            headerName: 'No.',
            renderCell: ({row}:CellType) =>  <span {...register('id', { required: true })}><Typography textAlign="center" sx={{fontSize:"1.5rem"}}>  {row.id}</Typography></span>
            },
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'title',
            headerName: '제목',
            renderCell: ({row}:CellType) =>  <Typography textAlign="center" sx={{fontSize:"1.5rem"}}> 
             <Link href={`${PG.ARTICLE}/detail/${row.id}`}>{row.title}</Link></Typography>
            }, 
            {
                flex: 0.04,
                minWidth: 30,
                sortable: false,
                field: 'content',
                headerName: '제목',
                renderCell: ({row}:CellType) =>  <Typography textAlign="center" sx={{fontSize:"1.5rem"}}> 
                 <Link href={`${PG.ARTICLE}/detail/${row.id}`}>{row.content}</Link></Typography>
                }, 
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'description',
            headerName: '내용',
            renderCell: ({row}:CellType) =>  <Typography textAlign="center" sx={{fontSize:"1.5rem"}}>  {row.description}</Typography>
            },
        {
            flex: 0.04,
            minWidth: 30,
            sortable: false,
            field: 'registerDate',
            headerName: '등록일',
            renderCell: ({row}:CellType) =>  <Typography textAlign="center" sx={{fontSize:"1.5rem"}}>  {row.regDate}</Typography>
            },
            {
                flex: 0.04,
                minWidth: 30,
                sortable: false,
                field: 'writerId',
                headerName: '작성자',
                renderCell: ({row}:CellType) =>  <Typography textAlign="center" sx={{fontSize:"1.5rem"}}>  {row.writerId}</Typography>
                },
                {
                    flex: 0.04,
                    minWidth: 30,
                    sortable: false,
                    field: 'boardId',
                    headerName: '등록일',
                    renderCell: ({row}:CellType) =>  <Typography textAlign="center" sx={{fontSize:"1.5rem"}}>  {row.boardId}</Typography>
                    },
                    {
                        flex: 0.04,
                        minWidth: 30,
                        sortable: false,
                        field: 'delete',
                        headerName: '삭제',
                        renderCell: () => <button onClick={handleDelete}><Typography textAlign="center" sx={{fontSize:"1.5rem"}}>  삭제</Typography></button>
                        },
    ]

}