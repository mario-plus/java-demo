package com.mario.metadata;

import com.mario.metadata.down.DownLinkMapping;
import com.mario.metadata.up.UpLinkMapping;
import lombok.Data;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月22日 20:30
 */
@Data
public class Metadata {

    private List<UpLinkMapping> upMappings;

    private List<DownLinkMapping> downMappings;

    public DownLinkMapping getDownMappingByServiceName(String serviceName) {
        return downMappings.stream().filter(e -> e.getServiceName().equals(serviceName)).findFirst().get();
    }

    public UpLinkMapping getUpMappingByServiceName(String serviceName){
        return upMappings.stream().filter(e -> e.getServiceName().equals(serviceName)).findFirst().get();
    }


}
