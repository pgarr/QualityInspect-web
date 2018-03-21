package com.pgarr.qualityinspect.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "object")
public class Object {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "object_detail_id")
	private ObjectDetail objectDetail;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "object", cascade = CascadeType.ALL)
	private List<Form> forms;

	public Object() {

	}

	public Object(String name, ObjectDetail objectDetail) {
		this.name = name;
		this.objectDetail = objectDetail;
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

	public ObjectDetail getObjectDetail() {
		return objectDetail;
	}

	public void setObjectDetail(ObjectDetail objectDetail) {
		this.objectDetail = objectDetail;
	}

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public void addForm(Form form) {

		if (forms == null)
			forms = new ArrayList<Form>();

		forms.add(form);
	}

	@Override
	public String toString() {
		return "Object [id=" + id + ", name" + name + "objectDetail=" + objectDetail + "]";
	}

}
