package com.pgarr.qualityinspect.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "object_detail")
public class ObjectDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "maker")
	private String maker;

	@Column(name = "description")
	private String description;

	@OneToOne(mappedBy = "objectDetail", cascade = CascadeType.ALL)
	private Object object;

	public ObjectDetail() {

	}

	public ObjectDetail(String maker, String description, Object object) {

		this.maker = maker;
		this.description = description;
		this.object = object;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "ObjectDetail [id=" + id + ", maker" + maker + "description=" + description + "]";
	}

}
