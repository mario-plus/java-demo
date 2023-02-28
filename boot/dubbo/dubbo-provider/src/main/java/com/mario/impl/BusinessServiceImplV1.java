package com.mario.impl;

import com.mario.api.BusinessService;
import com.mario.version.BusinessServiceVersion;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zxz
 * @date 2023年02月28日 10:01
 */

@DubboService(version = BusinessServiceVersion.version_v1)
public class BusinessServiceImplV1 implements BusinessService {

    public Object doBusiness(Object o) {
        return "business V1";
    }

}
