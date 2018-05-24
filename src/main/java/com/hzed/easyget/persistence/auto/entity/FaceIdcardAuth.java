package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FaceIdcardAuth implements Serializable {
    /**
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户认证id
     */
    private Long userStatusId;

    /**
     * 身份证图片路径
     */
    private String idCardPhotoPath;

    /**
     * 活体认证路径
     */
    private String facePhotoPath;

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
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(Long userStatusId) {
        this.userStatusId = userStatusId;
    }

    public String getIdCardPhotoPath() {
        return idCardPhotoPath;
    }

    public void setIdCardPhotoPath(String idCardPhotoPath) {
        this.idCardPhotoPath = idCardPhotoPath == null ? null : idCardPhotoPath.trim();
    }

    public String getFacePhotoPath() {
        return facePhotoPath;
    }

    public void setFacePhotoPath(String facePhotoPath) {
        this.facePhotoPath = facePhotoPath == null ? null : facePhotoPath.trim();
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
        sb.append(", userId=").append(userId);
        sb.append(", userStatusId=").append(userStatusId);
        sb.append(", idCardPhotoPath=").append(idCardPhotoPath);
        sb.append(", facePhotoPath=").append(facePhotoPath);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static FaceIdcardAuth.Builder builder() {
        return new FaceIdcardAuth.Builder();
    }

    public static class Builder {
        private FaceIdcardAuth obj;

        public Builder() {
            this.obj = new FaceIdcardAuth();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder userStatusId(Long userStatusId) {
            obj.setUserStatusId(userStatusId);
            return this;
        }

        public Builder idCardPhotoPath(String idCardPhotoPath) {
            obj.setIdCardPhotoPath(idCardPhotoPath);
            return this;
        }

        public Builder facePhotoPath(String facePhotoPath) {
            obj.setFacePhotoPath(facePhotoPath);
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

        public FaceIdcardAuth build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        userStatusId("user_status_id"),
        idCardPhotoPath("id_card_photo_path"),
        facePhotoPath("face_photo_path"),
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