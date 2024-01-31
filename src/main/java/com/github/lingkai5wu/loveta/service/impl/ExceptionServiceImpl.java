package com.github.lingkai5wu.loveta.service.impl;

import com.github.lingkai5wu.loveta.model.vo.exception.ExceptionVO;
import com.github.lingkai5wu.loveta.model.vo.exception.MethodArgumentNotValidExceptionVO;
import com.github.lingkai5wu.loveta.service.IExceptionService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ExceptionServiceImpl implements IExceptionService {
    @Override
    public ExceptionVO getExceptionVO(Exception e) {
        return new ExceptionVO()
                .setClassName(e.getClass().getName())
                .setMessage(e.getMessage());
    }

    @Override
    public MethodArgumentNotValidExceptionVO MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> invalidArgument = new LinkedHashMap<>();
        e.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            invalidArgument.put(field, message);
        });
        return new MethodArgumentNotValidExceptionVO()
                .setInvalidArgument(invalidArgument);
    }
}
