package com.pgarr.qualityinspect.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "inspection", uniqueConstraints = @UniqueConstraint(name = "INSPECTION_UNIQUE", columnNames = {
		"serial_number", "form_id" }))
public class Inspection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "creation_time")
	private Time creationTime;

	@Column(name = "completion_date")
	private Date completionDate;

	@Column(name = "completion_time")
	private Time completionTime;

	@Column(name = "inspector")
	private String inspector;

	@Column(name = "place")
	private String place;

	@Column(name = "batch")
	private int batch;

	@Column(name = "main_result")
	private int mainResult;

	@Column(name = "completed")
	private boolean completed;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "form_id")
	private Form form;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "inspection_id")
	private List<Result> results;

	public Inspection() {

	}

	public Inspection(int id, String serialNumber, Date creationDate, Time creationTime, Date completionDate,
			Time completionTime, String inspector, String place, int batch, int mainResult, boolean completed) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.creationDate = creationDate;
		this.creationTime = creationTime;
		this.completionDate = completionDate;
		this.completionTime = completionTime;
		this.inspector = inspector;
		this.place = place;
		this.batch = batch;
		this.mainResult = mainResult;
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Time getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date competionDate) {
		this.completionDate = competionDate;
	}

	public Time getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Time completionTime) {
		this.completionTime = completionTime;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public int getMainResult() {
		return mainResult;
	}

	public void setMainResult(int mainResult) {
		this.mainResult = mainResult;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Inspection [id=" + id + ", serialNumber=" + serialNumber + ", creationDate=" + creationDate
				+ ", creationTime=" + creationTime + ", completionDate=" + completionDate + ", completionTime="
				+ completionTime + ", inspector=" + inspector + ", place=" + place + ", batch=" + batch
				+ ", mainResult=" + mainResult + ", completed=" + completed + ", form=" + form + ", results=" + results
				+ "]";
	}

}
