package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AppInstall implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 设备唯一标识
     */
    private String imei;

    /**
     * 设备号
     */
    private String device;

    /**
     * 安装时间
     */
    private LocalDateTime createTime;

    /**
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imei=").append(imei);
        sb.append(", device=").append(device);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static AppInstall.Builder builder() {
        return new AppInstall.Builder();
    }

    public static class Builder {
        private AppInstall obj;

        public Builder() {
            this.obj = new AppInstall();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder imei(String imei) {
            obj.setImei(imei);
            return this;
        }

        public Builder device(String device) {
            obj.setDevice(device);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public AppInstall build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        imei("imei"),
        device("device"),
        createTime("create_time"),
        remark("remark");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Column(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}