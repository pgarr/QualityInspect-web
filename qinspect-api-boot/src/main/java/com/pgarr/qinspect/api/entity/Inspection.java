package com.pgarr.qinspect.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pgarr.qinspect.api.entity.enums.ResultType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "inspection", uniqueConstraints = @UniqueConstraint(name = "INSPECTION_UNIQUE", columnNames = {
        "serial_number", "form_id"}))
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "serial_number")
    @NotBlank
    private String serialNumber;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "inspector")
    private String inspector;

    @Column(name = "place")
    private String place;

    @Column(name = "batch")
    private int batch;

    @Enumerated
    @Column(name = "main_result", columnDefinition = "smallint")
    private ResultType mainResult;

    @Column(name = "completed")
    private boolean completed;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "form_id")
    private Form form;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "inspection_id")
    private List<Result> results;

    public Inspection() {
    }

    public Inspection(@NotBlank String serialNumber, Date createdAt, Date updatedAt, String inspector, String place,
                      int batch, ResultType mainResult, boolean completed) {
        this.serialNumber = serialNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.inspector = inspector;
        this.place = place;
        this.batch = batch;
        this.mainResult = mainResult;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public ResultType getMainResult() {
        return mainResult;
    }

    public void setMainResult(ResultType mainResult) {
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
        return "Inspection{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", inspector='" + inspector + '\'' +
                ", place='" + place + '\'' +
                ", batch=" + batch +
                ", mainResult=" + mainResult +
                ", completed=" + completed +
                '}';
    }

    public void addResult(Result result) {
        if (results == null)
            results = new ArrayList<>();
        results.add(result);
    }

    public void sortResults() {

        if (results != null)
        results = results
                .stream()
                .sorted(Comparator.comparingInt(result -> result.getStep().getNumber()))
                .collect(Collectors.toList());
    }
}