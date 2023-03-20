package com.mario.client.bo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxz
 * @date 2023年03月09日 18:10
 */
public class FunctionKeyParams {

    private static ConcurrentHashMap<String, List<ByteSplit>> downFormatMap;//下行数据格式

    private static ConcurrentHashMap<String, List<ByteSplit>> upFormatMap;//上线数据格式

    static {

        downFormatMap = new ConcurrentHashMap<>();
        downFormatMap.put(FunctionKey.createScreen, createScreen());
        downFormatMap.put(FunctionKey.resetAllScreens, resetAllScreens());
        downFormatMap.put(FunctionKey.setScreenRect, setScreenRect());
        downFormatMap.put(FunctionKey.getScreenRect, getScreenRect());
        downFormatMap.put(FunctionKey.setScreenSrc, setScreenSrc());
        downFormatMap.put(FunctionKey.getScreenSrc, getScreenRect());
        downFormatMap.put(FunctionKey.deleteScreen, getScreenRect());
        downFormatMap.put(FunctionKey.setBright, setBright());
        downFormatMap.put(FunctionKey.setAllBright, setAllBright());
        downFormatMap.put(FunctionKey.setGCurScreen, setGCurScreen());
        downFormatMap.put(FunctionKey.getTotalScreenRectByGid, resetAllScreens());
        downFormatMap.put(FunctionKey.readDataScreenInfo, readDataScreenInfo());
        downFormatMap.put(FunctionKey.readDataGetScreen, readDataGetScreen());


        upFormatMap = new ConcurrentHashMap<>();
        upFormatMap.put(FunctionKey.getScreenRect, getScreenRect_up());
        upFormatMap.put(FunctionKey.readDataGetScreen, readDataGetScreen_up());
        upFormatMap.put(FunctionKey.readDataScreenInfo, readDataScreenInfo_up());
        upFormatMap.put(FunctionKey.getTotalScreenRectByGid, getTotalScreenRectByGid_up());
        upFormatMap.put(FunctionKey.getScreenSrc, getScreenSrc_up());

    }

    /**************************************************Down*****************************************************************/
    private static List<ByteSplit> readDataGetScreen() {
        return Collections.singletonList(new ByteSplit(1, "ScreenId", "显示屏id", DataType.DATA_TYPE_INTEGER));
    }


    private static List<ByteSplit> readDataScreenInfo() {
        return Arrays.asList(
                new ByteSplit(1, "save", "保留参数", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "ugid", "用户组id", DataType.DATA_TYPE_INTEGER)

        );
    }

    private static List<ByteSplit> setGCurScreen() {
        return Arrays.asList(
                new ByteSplit(1, "ugid", "用户组id", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "sid", "显示屏id", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "outslot", "输出槽位号", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(4, "outport", "输出接口号", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(5, "type", "输出接口类型，默认为DVI-12", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(6, "bright", "亮度，默认为50", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(7, "x", "显示屏水平起点坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(8, "y", "显示屏垂直起点坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(9, "w", "显示屏宽度（像素点数）", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(10, "h", "显示屏高度（像素点数)", DataType.DATA_TYPE_INTEGER)

        );
    }


    private static List<ByteSplit> setAllBright() {
        return Collections.singletonList(new ByteSplit(1, "bright", "亮度值(0-100)", DataType.DATA_TYPE_INTEGER));
    }


    private static List<ByteSplit> setBright() {
        return Arrays.asList(
                new ByteSplit(1, "outslot", "输出槽位号", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "outport", "输出接口号", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "bright", "亮度值(0-100)", DataType.DATA_TYPE_INTEGER)
        );
    }


    private static List<ByteSplit> setScreenSrc() {
        return Arrays.asList(
                new ByteSplit(1, "sid", "显示屏id", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "outslot", "输出槽位号", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "outport", "输出接口号", DataType.DATA_TYPE_INTEGER)
        );
    }

    //下行
    private static List<ByteSplit> getScreenRect() {
        return Collections.singletonList(new ByteSplit(1, "sid", "显示屏id", DataType.DATA_TYPE_INTEGER));
    }


    private static List<ByteSplit> setScreenRect() {
        return Arrays.asList(
                new ByteSplit(1, "sid", "显示屏id", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "screenx", "显示屏横坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "screeny", "显示屏纵坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(4, "screenw", "显示屏宽度", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(5, "screenh", "显示屏高度", DataType.DATA_TYPE_INTEGER)

        );
    }


    private static List<ByteSplit> resetAllScreens() {
        return Collections.singletonList(new ByteSplit(1, "ugid", "用户组id", DataType.DATA_TYPE_INTEGER));
    }

    private static List<ByteSplit> createScreen() {
        return Arrays.asList(new ByteSplit(1, "ugid", "用户组id", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "outslot", "输出槽位", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "outport", "输出接口", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(4, "screenx", "显示屏横坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(5, "screeny", "显示屏纵坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(6, "screenw", "显示屏宽度", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(7, "screenh", "显示屏高度", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(8, "bright", "显示屏亮度", DataType.DATA_TYPE_INTEGER)
        );
    }


    /***************************************************上行************************************************************/
    private static List<ByteSplit> getScreenRect_up() {
        return Arrays.asList(
                new ByteSplit(1, "screenx", "显示屏横坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "screeny", "显示屏纵坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "screenw", "显示屏宽度", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(4, "screenh", "显示屏高度", DataType.DATA_TYPE_INTEGER)

        );
    }

    private static List<ByteSplit> getScreenSrc_up() {
        return Arrays.asList(
                new ByteSplit(1, "outslot", "输出槽位号", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "outport", "输出接口号", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "type", "接口类型", DataType.DATA_TYPE_INTEGER)
        );
    }

    private static List<ByteSplit> getTotalScreenRectByGid_up() {
        return Arrays.asList(
                new ByteSplit(1, "x", "横坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "y", "纵坐标", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(3, "w", "宽度", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(4, "h", "高度", DataType.DATA_TYPE_INTEGER)
        );
    }

    private static List<ByteSplit> readDataScreenInfo_up() {
        return Arrays.asList(
                new ByteSplit(1, "ScreenNum", "有效显示屏数", DataType.DATA_TYPE_INTEGER),
                new ByteSplit(2, "ScreenIds", "纵坐标", DataType.DATA_TYPE_ARRAY)
        );
    }


    private static List<ByteSplit> readDataGetScreen_up() {
        return createScreen();
    }

    public static List<ByteSplit> getDownParamFormat(String functionKey) {
        return downFormatMap.get(functionKey);
    }

    public static List<ByteSplit> getUpParamFormat(String functionKey) {
        return upFormatMap.get(functionKey);
    }


}
