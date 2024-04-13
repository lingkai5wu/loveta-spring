package com.github.lingkai5wu.loveta.service.impl;

import com.github.lingkai5wu.loveta.model.vo.exception.MethodArgumentNotValidExceptionVO;
import com.github.lingkai5wu.loveta.service.IExceptionService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-01-31
 */
@Service
public class ExceptionServiceImpl implements IExceptionService {

    @Override
    public MethodArgumentNotValidExceptionVO getMethodArgumentNotValidExceptionVO(MethodArgumentNotValidException e) {
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
