package br.edu.utfpr.cp.emater.midmipsystem.domain.mip;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SamplePest extends AuditingPersistenceEntity implements Serializable {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal (TemporalType.DATE)
    private Date sampleDate;
    
    private int daysAfterEmergence;
    private int defoliation;
    
    @Enumerated (EnumType.STRING)
    private GrowthPhase growthPhase;
    
    @ElementCollection
    private List<PestOccurrence> pestOccurrenceList; 

    @ManyToOne
    private MipPestSurvey mipPestSurvey;
}
