package br.edu.utfpr.cp.emater.mip.domain.pest;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PestOccurrence implements Serializable {
    
    private double value;
    
    @ManyToOne (fetch = FetchType.EAGER)
    private Pest pest;
}
