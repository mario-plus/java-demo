package com.mario.input;

import com.sun.jna.Library;


import com.sun.jna.Native;
import com.sun.jna.Platform;


/**
 * @author zxz
 * @date 2023年03月06日 9:59
 */
public interface ColorLightSDK extends Library {

    ColorLightSDK colorLightInstance = Native.load((Platform.isWindows() ?
                    "C:\\Users\\zhouxiaoze\\Desktop\\colorT\\SDKPlatform_2022.12.05\\SDKPlatform\\CLTDeviceSDK\\Win\\MSVC\\x64\\Release\\CLTDevice.dll" : "123")
            , ColorLightSDK.class);


    //查询所有设备，成功返回0
    Integer CLTSearchAllDevice();

    //探测设备数量
    Integer CLTGetDeviceCounts();

    ColorLightInfo CLTProcessorSGetInfo(int prcIndex);


   Integer CLTProcessorSDetectOne(int prcIndex);


    static void main(String[] args) {

        System.out.println("SearchAllDevice:" + colorLightInstance.CLTSearchAllDevice());
        //System.out.println("CLTGetDeviceCounts:" + colorLightInstance.CLTGetDeviceCounts());

        System.out.println("CLTProcessorSDetectOne:"+colorLightInstance.CLTProcessorSDetectOne(0));

//        System.out.println(colorLightInstance.CLTProcessorSGetInfo(0));


    }
}
