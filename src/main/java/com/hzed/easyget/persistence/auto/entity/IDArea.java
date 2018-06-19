package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;

public class IDArea implements Serializable {
    /**
     */
    private Long id;

    /**
     * 上级
     */
    private String parent;

    /**
     * 地名
     */
    private String name;

    /**
     * 省市区
     */
    private String district;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parent=").append(parent);
        sb.append(", name=").append(name);
        sb.append(", district=").append(district);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static IDArea.Builder builder() {
        return new IDArea.Builder();
    }

    public static class Builder {
        private IDArea obj;

        public Builder() {
            this.obj = new IDArea();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder parent(String parent) {
            obj.setParent(parent);
            return this;
        }

        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        public Builder district(String district) {
            obj.setDistrict(district);
            return this;
        }

        public IDArea build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        parent("parent"),
        name("name"),
        district("district");

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