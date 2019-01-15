package br.edu.utfpr.cp.emater.midmipsystem.domain.mip;

import br.edu.utfpr.cp.emater.midmipsystem.domain.survey.SurveyField;
import br.edu.utfpr.cp.emater.midmipsystem.library.AuditingPersistenceEntity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MipPestSurvey extends AuditingPersistenceEntity implements Serializable {
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne (fetch = FetchType.EAGER)
    private SurveyField surveyField;        
}
