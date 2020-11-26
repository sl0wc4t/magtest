package com.company.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "entries")
public class EntriesXslt {
    private List<EntryXslt> entries;

    public EntriesXslt() {
    }

    public EntriesXslt(List<EntryXslt> entries) {
        this.entries = entries;
    }

    public List<EntryXslt> getEntries() {
        return entries;
    }

    @XmlElement(name = "entry")
    public void setEntries(List<EntryXslt> entries) {
        this.entries = entries;
    }
}
