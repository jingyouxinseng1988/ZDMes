package com.zhengda.platform.enums;

/**
 * 用户状态
 * 
 * @author wolf
 */
public enum TunnelLibraryType
{
    ENTER(1, "入库"), OUT(2, "出库");

    private final Integer code;
    private final String info;

    TunnelLibraryType(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
