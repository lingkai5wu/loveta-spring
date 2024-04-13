package com.github.lingkai5wu.loveta.service;

import com.github.lingkai5wu.loveta.model.vo.exception.MethodArgumentNotValidExceptionVO;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * 异常 服务类
 *
 * @author lingkai5wu
 * @since 2024-01-31
 */
public interface IExceptionService {

    MethodArgumentNotValidExceptionVO getMethodArgumentNotValidExceptionVO(MethodArgumentNotValidException e);
}
