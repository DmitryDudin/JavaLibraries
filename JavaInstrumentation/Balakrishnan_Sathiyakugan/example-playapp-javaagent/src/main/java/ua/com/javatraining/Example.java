package ua.com.javatraining;

import java.util.concurrent.TimeUnit;

public class Example {

//VM optins:   -javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\example-playapp-javaagent\java_agent\javaagent-example-1.0-SNAPSHOT-uber.jar
    //or
//-javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\javaagent-example\build\libs\javaagent-example-1.0-SNAPSHOT-uber.jar

//for remote debug
//-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
//-agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=127.0.0.1:8000

//final config (should be white_space between " ")
//"-javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\javaagent-example\build\libs\javaagent-example-1.0-SNAPSHOT-uber.jar"
//-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005

//when "suspend=y"  for debug should print ->      Listening for transport dt_socket at address: 5005

    public static void main(String[] args) throws InterruptedException {

//        TimeUnit.SECONDS.sleep(60); // not needed if "suspend=y"
        System.out.println("Hi I am main.");

    }

}

/*
"C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\jbr\bin\java.exe"
        -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:53604,suspend=y,server=n
        -javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\example-playapp-javaagent\java_agent\javaagent-example-1.0-SNAPSHOT-uber.jar
        -javaagent:C:\Users\dmytro.dudin\.IdeaIC2019.2\system\groovyHotSwap\gragent.jar
        -javaagent:C:\Users\dmytro.dudin\.IdeaIC2019.2\system\captureAgent\debugger-agent.jar
        -Dfile.encoding=UTF-8
        -classpath "D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\example-playapp-javaagent\out\production\classes;C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\lib\idea_rt.jar" ua.com.javatraining.Example
*/


/*
"C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\jbr\bin\java.exe"
        -javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\javaagent-example\build\libs\javaagent-example-1.0-SNAPSHOT-uber.jar
        -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005
        "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\lib\idea_rt.jar=56811:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\bin"
        -Dfile.encoding=UTF-8
        -classpath D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\example-playapp-javaagent\out\production\classes ua.com.javatraining.Example
*/
/*
"C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\jbr\bin\java.exe"
        -javaagent:D:\projects\archive\JavaLibraries\JavaInstrumentation\Balakrishnan_Sathiyakugan\javaagent-example\build\libs\javaagent-example-1.0-SNAPSHOT-uber.jar
        -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005
        "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\lib\idea_rt.jar=56881:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2018.3.5\bin"
        -Dfile.encoding=UTF-8
        -p D:\projects\archive\JavaLibraries\JavaInstrumentation\Baeldung\baeldung_application\out\production\classes;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-classic\1.2.3\7c4f3c474fb2c041d8028740440937705ebb473a\logback-classic-1.2.3.jar;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-core\1.2.3\864344400c3d4d92dfeb0a305dc87d953677c03c\logback-core-1.2.3.jar;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\javassist\javassist\3.12.1.GA\526633327faa61aee448a519e8a4d53ec3057885\javassist-3.12.1.GA.jar;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm-util\9.1\36464a45d871779f3383a8a9aba2b26562a86729\asm-util-9.1.jar;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm-analysis\9.1\4f61b83b81d8b659958f4bcc48907e93ecea55a0\asm-analysis-9.1.jar;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm-tree\9.1\c333f2a855069cb8eb17a40a3eb8b1b67755d0eb\asm-tree-9.1.jar;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.ow2.asm\asm\9.1\a99500cf6eea30535eeac6be73899d048f8d12a8\asm-9.1.jar;
           C:\Users\dmytro.dudin\.gradle\caches\modules-2\files-2.1\org.slf4j\slf4j-api\1.7.25\da76ca59f6a57ee3102f8f9bd9cee742973efa8a\slf4j-api-1.7.25.jar
        -m baeldung.application.main/ua.com.javatraining.Launcher StartMyAtmApplication 60000 3 11

        */
