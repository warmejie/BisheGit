package cn.wj.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
    private Long id;

    private String cid;

    private String title;

    private String image;

    private Integer uid;

    private Integer status;

    private BigDecimal price;

    private String address;

    private Integer dpetId;

    private Integer gredeId;

    private Integer zhuanyeId;

    private Date createTime;

    private Date updateTime;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getDpetId() {
        return dpetId;
    }

    public void setDpetId(Integer dpetId) {
        this.dpetId = dpetId;
    }

    public Integer getGredeId() {
        return gredeId;
    }

    public void setGredeId(Integer gredeId) {
        this.gredeId = gredeId;
    }

    public Integer getZhuanyeId() {
        return zhuanyeId;
    }

    public void setZhuanyeId(Integer zhuanyeId) {
        this.zhuanyeId = zhuanyeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}