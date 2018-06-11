package br.edu.utfpr.cp.emater.mipdatacollection.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class SampleEntry implements Serializable {

    @ManyToOne(targetEntity = SampleItem.class)
    private SampleItem sampleItem;
    private double value;
}