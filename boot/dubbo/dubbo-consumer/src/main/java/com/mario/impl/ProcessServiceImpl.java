package com.mario.impl;

import com.mario.api.BusinessService;
import com.mario.version.BusinessServiceVersion;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zxz
 * @date 2023年02月28日 10:51
 */
@Service
public class ProcessServiceImpl {

    @DubboReference(version = BusinessServiceVersion.version_v1)
    BusinessService businessService;

    /**
     * 启动后直接调用
     */
    @PostConstruct
    public void process() {
        Object o = businessService.doBusiness("dsfjdjfkdlfhkd");
        System.out.println(o.toString());

    }

}
