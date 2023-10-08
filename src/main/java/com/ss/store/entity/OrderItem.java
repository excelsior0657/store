package com.ss.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;


    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getId(), orderItem.getId()) && Objects.equals(getOid(), orderItem.getOid()) && Objects.equals(getPid(), orderItem.getPid()) && Objects.equals(getTitle(), orderItem.getTitle()) && Objects.equals(getImage(), orderItem.getImage()) && Objects.equals(getPrice(), orderItem.getPrice()) && Objects.equals(getNum(), orderItem.getNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getOid(), getPid(), getTitle(), getImage(), getPrice(), getNum());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
