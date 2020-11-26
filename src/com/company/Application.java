package com.company;

import com.company.model.Entries;

import java.io.File;

public class Application {

    private String url;
    private String driver;
    private String username;
    private String password;
    private Integer n;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }
    
    public void run() {
        Entries entries = new Entries();
        try (EntryRepository entryRepository = new EntryRepository(url, driver, username, password)) {
            entryRepository.truncate();
            entryRepository.insertNRecords(n);
            entries.setEntries(entryRepository.findAll());
        } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении запросов к БД");
            throw new RuntimeException(e);
        }

        File file1 = new File("1.xml");
        File file2 = new File("2.xml");
        File xsl = new File("entry.xsl");

        XmlService.createXml(file1, entries);
        XmlService.xslt(xsl, file1, file2);
        System.out.printf("Сумма = %d", XmlService.calcSum(file2));
    }
}
