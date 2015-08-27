package com.lemon.modules.db;

import android.content.Context;

import com.lemon.common.BaseModules;
import com.lemon.common.util.AssertUtil;

/**
 * 项目名称:  [lemon]
 * 包:        [com.lemon.modules.db]
 * 类描述:    [简要描述]
 * 创建人:    [xflu]
 * 创建时间:  [2015/8/27 15:42]
 * 修改人:    [xflu]
 * 修改时间:  [2015/8/27 15:42]
 * 修改备注:  [说明本次修改内容]
 * 版本:      [v1.0]
 */
public class DbModules extends BaseModules {

    private Context mContext;
    private static DbModules modules;

    /**
     * init Modules , create instance of this Modules
     * @param mContext
     */
    public static void initModules(Context mContext) {
        AssertUtil.notNull(mContext);
        modules = new DbModules(mContext);
    }

    private DbModules(Context mContext){
        this.mContext = mContext;
    }

    public static DbModules getInstance(){
        AssertUtil.notNull(modules);
        return modules;
    }

}
