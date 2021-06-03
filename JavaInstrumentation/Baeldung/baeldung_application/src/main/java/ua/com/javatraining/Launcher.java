package ua.com.javatraining;

public class Launcher {

//idea -> Edit configuration -> Program arguments -> add: StartMyAtmApplication 30000 3 11

/*
start with -javaagent:

final config VM options with Baeldung\baeldung_agent
-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005
"-javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Baeldung\baeldung_agent\build\libs\baeldung_agent-1.0-SNAPSHOT-uber.jar"

final working with Balakrishnan_Sathiyakugan\javaagent-example
"-javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\javaagent-example\build\libs\javaagent-example-1.0-SNAPSHOT-uber.jar"
-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005

*/


    public static void main(String[] args) throws Exception {
        System.out.println("Launcher module ->  " + Launcher.class.getModule());

        if (args[0].equals("StartMyAtmApplication")) {
            new MyAtmApplication().run(args);
        } else if (args[0].equals("LoadAgent")) {
            throw new UnsupportedOperationException();
//            new AgentLoader().run(args);//todo start in different project
        }

    }

//RESERVE:
//-Djdk.attach.allowAttachSelf=true
//when "suspend=y"  for debug should print ->      Listening for transport dt_socket at address: 5005
//--add-modules java.base


    /*
    "C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\jbr\bin\java.exe"
            -javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\javaagent-example\build\libs\javaagent-example-1.0-SNAPSHOT-uber.jar
            -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005
            "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\lib\idea_rt.jar=55235:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\bin"
            -Dfile.encoding=UTF-8
            -p
                D:\projects\archive\JavaLibraries\JavaInstrumentation\Baeldung\baeldung_application\out\production\classes;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-classic\1.2.3\7c4f3c474fb2c041d8028740440937705ebb473a\logback-classic-1.2.3.jar;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-core\1.2.3\864344400c3d4d92dfeb0a305dc87d953677c03c\logback-core-1.2.3.jar;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\javassist\javassist\3.12.1.GA\526633327faa61aee448a519e8a4d53ec3057885\javassist-3.12.1.GA.jar;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm-util\9.1\36464a45d871779f3383a8a9aba2b26562a86729\asm-util-9.1.jar;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm-analysis\9.1\4f61b83b81d8b659958f4bcc48907e93ecea55a0\asm-analysis-9.1.jar;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm-tree\9.1\c333f2a855069cb8eb17a40a3eb8b1b67755d0eb\asm-tree-9.1.jar;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm\9.1\a99500cf6eea30535eeac6be73899d048f8d12a8\asm-9.1.jar;
                C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.slf4j\slf4j-api\1.7.25\da76ca59f6a57ee3102f8f9bd9cee742973efa8a\slf4j-api-1.7.25.jar

            -m baeldung.application.main/ua.com.javatraining.Launcher StartMyAtmApplication 60000 3 11
---
Usage:
    java [options] -m <module>[/<mainclass>] [args...]

where options include:
    -p <module path>
*/


}
