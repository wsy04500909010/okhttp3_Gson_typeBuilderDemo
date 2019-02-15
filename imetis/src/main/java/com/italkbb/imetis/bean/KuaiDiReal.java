package com.italkbb.imetis.bean;

/**
 * Created by WangSiYe on 2019/2/14.
 */
public class KuaiDiReal {

    /**
     * time : 2019-01-08 11:27:37
     * ftime : 2019-01-08 11:27:37
     * context : 快件已到达4街区菜场对面50栋102圆通妈妈驿站,联系电话025-58868397
     * location : null
     */

    private String time;
    private String ftime;
    private String context;
    private Object location;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }
}
