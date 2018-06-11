package br.edu.utfpr.cp.emater.mipdatacollection.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor
public class QuestionData implements Serializable {

    private boolean rustResistent;
    private boolean bt;

}