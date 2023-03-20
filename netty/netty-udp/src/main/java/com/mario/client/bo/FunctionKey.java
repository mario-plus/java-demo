package com.mario.client.bo;

public interface FunctionKey {


    String resetAllScreens = "/resetAllScreens";    //1.21	重置所有显示屏
    String createScreen = "/createScreen";          //1.22	创建显示屏
    String setScreenRect = "/setScreenRect";        //1.23	设置显示屏尺寸
    String getScreenRect = "/getScreenRect";        //1.24	获取显示屏尺寸
    String setScreenSrc = "/setScreenSrc";          //1.25	切换指定显示屏输出口

    String getScreenSrc = "/getScreenSrc";          //1.26	获取指定显示屏输出口
    String deleteScreen = "/deleteScreen";          //1.27	关闭显示屏
    String setBright = "/setbright";                //1.28	设置接口亮度
    String setAllBright = "/setAllbright";          //1.29	设置所有接口亮度
    String setGCurScreen = "/setgCurScreen";        //1.30	设置显示屏
    String getTotalScreenRectByGid = "/getTotalScreenRectByGid";            //1.31	获取显示屏最大尺寸
    String readDataScreenInfo = "/readDatasScreenInfo";                     //1.32  读取用户组的有效显示屏号
    String readDataGetScreen = "/readDatasGetScreen";                       //1.33	读取指定显示屏的详细参数


}
