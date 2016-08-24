package com.ztiany.library;

import android.os.Build;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-16 21:32      <br/>
 * Description：App兼容性的处理
 */
public class AppVersion {

    private AppVersion() {

    }


    /**
     * @param level 要求的版本
     * @return true when the caller API version is > level
     */
    public static boolean require(int level) {
        return Build.VERSION.SDK_INT > level;
    }

    /**
     * @param level 要求的版本
     * @return true when the caller API version >= level
     */
    public static boolean requireInculde(int level) {
        return Build.VERSION.SDK_INT >= level;
    }

}
