package ua.com.javatraining;

public class Launcher {

//idea -> Edit configuration -> Program arguments -> add: LoadAgent

//final config (should be white_space between " ")
//"-javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\javaagent-example\build\libs\javaagent-example-1.0-SNAPSHOT-uber.jar"
//-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005

//when "suspend=y"  for debug should print ->      Listening for transport dt_socket at address: 5005

    public static void main(String[] args) throws Exception {
            new AgentLoader().run(args);
    }

}
