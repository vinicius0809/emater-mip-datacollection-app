package br.edu.utfpr.cp.emater.mipdatacollection.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Sample implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date sampleDate;

    private int daysAfterEmergence;
    private int defoliation;

    @ElementCollection (fetch =  FetchType.EAGER)
    private List<SampleEntry> sampleEntries;

    @Enumerated (EnumType.STRING)
    private GrowthPhase growthPhase;

    @Embedded
    private SurveyField surveyField;
}