package com.mario.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxz
 * @date 2023年03月09日 18:10
 */
public class FunctionKeyParams {

    private static ConcurrentHashMap<String, List<String>> map;

    static {
        map = new ConcurrentHashMap<>();
        map.put(FunctionKey.createScreen, Arrays.asList("ugid", "outslot", "outport", "screenx", "screeny", "screenw", "screenh", "bright"));
        map.put(FunctionKey.resetAllScreens, Collections.singletonList("ugid"));
        map.put(FunctionKey.setScreenRect, Arrays.asList("sid", "screenx", "screeny", "screenw", "screenh"));
        map.put(FunctionKey.getScreenRect, Collections.singletonList("sid"));
        map.put(FunctionKey.setScreenSrc, Arrays.asList("sid", "outslot", "outport"));
        map.put(FunctionKey.getScreenSrc, Collections.singletonList("sid"));
        map.put(FunctionKey.deleteScreen, Collections.singletonList("sid"));
        map.put(FunctionKey.setBright, Arrays.asList("outslot", "outport", "bright"));
        map.put(FunctionKey.setAllBright, Collections.singletonList("bright"));
        map.put(FunctionKey.setGCurScreen, Arrays.asList("ugid", "sid", "outslot", "outport", "type", "bright", "x", "y", "w", "h"));
        map.put(FunctionKey.getTotalScreenRectByGid, Collections.singletonList("ugid"));
        map.put(FunctionKey.readDataScreenInfo, Arrays.asList("save", "ugid"));
        map.put(FunctionKey.readDataGetScreen, Collections.singletonList("ScreenId"));
    }

    public static List<String> getParamFormat(String functionKey) {
        return map.get(functionKey);
    }
}
