package com.pgarr.qinspect.api.entity;

import com.pgarr.qinspect.api.entity.enums.ResultType;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated
    @Column(name = "result", columnDefinition = "smallint")
    private ResultType result;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "step_id")
    private Step step;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private List<FaultPicture> faultPictures;

    public Result() {

    }

    public Result(ResultType result, String note) {
        this.result = result;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
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
