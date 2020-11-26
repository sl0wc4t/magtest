package com.company;

import com.company.model.Entries;
import com.company.model.EntriesXslt;
import com.company.model.EntryXslt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XmlService {

    public static void createXml(File file, Entries entries) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(entries, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void xslt(File xsl, File src, File dst) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xsl);

            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(src);

            transformer.transform(xml, new StreamResult(dst));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static int calcSum(File file) {
        int sum = 0;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EntriesXslt.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            EntriesXslt entriesXslt = (EntriesXslt) jaxbUnmarshaller.unmarshal(file);
            for (EntryXslt entryXslt : entriesXslt.getEntries()) {
                sum += entryXslt.getField();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
