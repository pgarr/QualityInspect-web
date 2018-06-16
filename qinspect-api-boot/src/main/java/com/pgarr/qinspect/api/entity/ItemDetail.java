package com.pgarr.qinspect.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_detail")
public class ItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "maker")
    private String maker;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "itemDetail", cascade = CascadeType.ALL)
    private Item item;

    public ItemDetail() {
    }

    public ItemDetail(String maker, String description) {
        this.maker = maker;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "id=" + id +
                ", maker='" + maker + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
