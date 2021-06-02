package ua.com.javatraining.baeldung.application;

import ua.com.javatraining.baeldung.application.AgentLoader;

public class Launcher {
//idea -> Edit configuration -> Program arguments -> add: LoadAgent
    public static void main(String[] args) throws Exception {

        if (args[0].equals("StartMyAtmApplication")) {
            new MyAtmApplication().run(args);
        } else if (args[0].equals("LoadAgent")) {
            new AgentLoader().run(args);
        }

    }

}
