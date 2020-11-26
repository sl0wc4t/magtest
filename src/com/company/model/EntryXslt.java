package com.company.model;

import com.sun.xml.internal.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlElement
public class EntryXslt {
    private Integer field;

    public EntryXslt() {
    }

    public Integer getField() {
        return field;
    }

    @XmlAttribute
    public void setField(Integer field) {
        this.field = field;
    }
}
