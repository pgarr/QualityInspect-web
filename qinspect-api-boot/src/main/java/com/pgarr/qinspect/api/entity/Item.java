package com.pgarr.qinspect.api.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "item", uniqueConstraints = @UniqueConstraint(name = "ITEM_UNIQUE", columnNames = {
        "name"}))
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    @NotBlank
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_detail_id")
    private ItemDetail itemDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    private List<Form> forms;

    public Item() {
    }

    public Item(@NotBlank String name, ItemDetail itemDetail, List<Form> forms) {
        this.name = name;
        this.itemDetail = itemDetail;
        this.forms = forms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemDetail=" + itemDetail +
                ", forms=" + forms +
                '}';
    }
}