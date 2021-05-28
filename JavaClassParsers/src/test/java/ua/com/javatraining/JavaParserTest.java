package ua.com.javatraining;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.PrettyPrintVisitor;
import org.junit.Test;

import java.io.InputStream;

public class JavaParserTest {
//https://github.com/javaparser/javaparser-visited

    @Test
    public void name() {
        final CompilationUnit parse = StaticJavaParser.parse(getClass().getResourceAsStream("/TestClassForParsing.java"));
//        final CompilationUnit parse = StaticJavaParser.parse(getClass().getResourceAsStream("Object.java"));//java.lang.AssertionError: A reference was unexpectedly null.
        System.out.println(parse.toString());// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        final JavaParser javaParser = new JavaParser();
        final ParseResult<CompilationUnit> testClassForParsing =
                javaParser.parse(getClass().getResourceAsStream("/TestClassForParsing.java"));
        System.out.println(testClassForParsing.toString());

        final CompilationUnit compilationUnit = testClassForParsing.getResult().get();
        System.out.println(compilationUnit.toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        String str = compilationUnit.

    }

    @Test
    public void name2() {
        //not working
        final String canonicalName = TestClassForParsing.class.getCanonicalName();
        final InputStream resourceAsStream = getClass().getResourceAsStream(canonicalName);
        final CompilationUnit parse = StaticJavaParser.parse(resourceAsStream);
        System.out.println(parse.toString());
    }
}
