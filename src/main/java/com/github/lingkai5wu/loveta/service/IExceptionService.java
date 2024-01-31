package com.github.lingkai5wu.loveta.service;

import com.github.lingkai5wu.loveta.model.vo.exception.ExceptionVO;
import com.github.lingkai5wu.loveta.model.vo.exception.MethodArgumentNotValidExceptionVO;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface IExceptionService {
    ExceptionVO getExceptionVO(Exception e);

    MethodArgumentNotValidExceptionVO MethodArgumentNotValidException(MethodArgumentNotValidException e);
}
