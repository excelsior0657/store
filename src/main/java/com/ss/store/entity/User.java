package com.ss.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String PASSWORD;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getUid(), user.getUid()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPASSWORD(), user.getPASSWORD()) && Objects.equals(getSalt(), user.getSalt()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getGender(), user.getGender()) && Objects.equals(getAvatar(), user.getAvatar()) && Objects.equals(getIsDelete(), user.getIsDelete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUid(), getUsername(), getPASSWORD(), getSalt(), getPhone(), getEmail(), getGender(), getAvatar(), getIsDelete());
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
