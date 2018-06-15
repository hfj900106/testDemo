package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AdsProduct implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片url
     */
    private String imgUrl;

    /**
     * 跳转url
     */
    private String linkUrl;

    /**
     * 是否可用 1-可用 0-不可用
     */
    private Boolean isUse;

    /**
     * 金额区间
     */
    private String amountInterval;

    /**
     * 权重 值越小，排在前
     */
    private Integer weights;

    /**
     * 上架时间
     */
    private LocalDateTime upTime;

    /**
     * 下架时间
     */
    private LocalDateTime downTime;

    /**
     */
    private Long createBy;

    /**
     */
    private LocalDateTime createTime;

    /**
     */
    private Long updateBy;

    /**
     */
    private LocalDateTime updateTime;

    /**
     * 描述
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public String getAmountInterval() {
        return amountInterval;
    }

    public void setAmountInterval(String amountInterval) {
        this.amountInterval = amountInterval == null ? null : amountInterval.trim();
    }

    public Integer getWeights() {
        return weights;
    }

    public void setWeights(Integer weights) {
        this.weights = weights;
    }

    public LocalDateTime getUpTime() {
        return upTime;
    }

    public void setUpTime(LocalDateTime upTime) {
        this.upTime = upTime;
    }

    public LocalDateTime getDownTime() {
        return downTime;
    }

    public void setDownTime(LocalDateTime downTime) {
        this.downTime = downTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", title=").append(title);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", isUse=").append(isUse);
        sb.append(", amountInterval=").append(amountInterval);
        sb.append(", weights=").append(weights);
        sb.append(", upTime=").append(upTime);
        sb.append(", downTime=").append(downTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static AdsProduct.Builder builder() {
        return new AdsProduct.Builder();
    }

    public static class Builder {
        private AdsProduct obj;

        public Builder() {
            this.obj = new AdsProduct();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder title(String title) {
            obj.setTitle(title);
            return this;
        }

        public Builder imgUrl(String imgUrl) {
            obj.setImgUrl(imgUrl);
            return this;
        }

        public Builder linkUrl(String linkUrl) {
            obj.setLinkUrl(linkUrl);
            return this;
        }

        public Builder isUse(Boolean isUse) {
            obj.setIsUse(isUse);
            return this;
        }

        public Builder amountInterval(String amountInterval) {
            obj.setAmountInterval(amountInterval);
            return this;
        }

        public Builder weights(Integer weights) {
            obj.setWeights(weights);
            return this;
        }

        public Builder upTime(LocalDateTime upTime) {
            obj.setUpTime(upTime);
            return this;
        }

        public Builder downTime(LocalDateTime downTime) {
            obj.setDownTime(downTime);
            return this;
        }

        public Builder createBy(Long createBy) {
            obj.setCreateBy(createBy);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public Builder updateBy(Long updateBy) {
            obj.setUpdateBy(updateBy);
            return this;
        }

        public Builder updateTime(LocalDateTime updateTime) {
            obj.setUpdateTime(updateTime);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public AdsProduct build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        title("title"),
        imgUrl("img_url"),
        linkUrl("link_url"),
        isUse("is_use"),
        amountInterval("amount_interval"),
        weights("weights"),
        upTime("up_time"),
        downTime("down_time"),
        createBy("create_by"),
        createTime("create_time"),
        updateBy("update_by"),
        updateTime("update_time"),
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