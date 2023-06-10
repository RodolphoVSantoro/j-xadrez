package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import tests.config.SetupPecasTest;
import tests.config.SetupTabuleiroTest;
import tests.maquinaDeRegras.IATest;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SetupPecasTest.class, SetupTabuleiroTest.class, IATest.class);
        System.out.println("Testes executados: " + result.getRunCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}