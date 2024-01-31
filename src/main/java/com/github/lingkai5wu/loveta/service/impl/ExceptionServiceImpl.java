package com.github.lingkai5wu.loveta.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.github.lingkai5wu.loveta.model.vo.ExceptionVO;
import com.github.lingkai5wu.loveta.service.IExceptionService;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements IExceptionService {
    @Override
    public ExceptionVO getExceptionVO(Exception e) {
        return new ExceptionVO()
                .setClassName(e.getClass().getName())
                .setMessage(e.getMessage());
    }
}
