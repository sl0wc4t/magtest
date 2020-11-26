package com.company;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        application.setUrl("jdbc:mysql://localhost:3306/testdb?useUnicode=true&serverTimezone=UTC");
        application.setDriver("com.mysql.cj.jdbc.Driver");
        application.setUsername("root");
        application.setPassword("root");
        application.setN(1000000);
        
        application.run();
    }
}
