package com.pgarr.qinspect.api.entity;

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

@Entity
@Table(name = "result")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "result")
	private int result;

	@Column(name = "note")
	private String note;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "step_id")
	private Step step;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "result_id")
	private List<FaultPicture> faultPictures;

	public Result() {

	}

	public Result(int result, String note, Step step) {
		this.result = result;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public List<FaultPicture> getFaultPictures() {
		return faultPictures;
	}

	public void setFaultPictures(List<FaultPicture> faultPictures) {
		this.faultPictures = faultPictures;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", result=" + result + ", note=" + note + ", step=" + step + ", faultPictures="
				+ faultPictures + "]";
	}

}
