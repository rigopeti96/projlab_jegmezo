package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: test <jar file> <cases folder>");
            return;
        }
        File folder = new File(args[1]);
        if (!folder.exists()) {
            System.out.println(args[1] + " folder does not exist.");
            return;
        }
        System.out.println("Loading test cases...");
        ArrayList<File> files = (ArrayList<File>) Arrays.stream(folder.listFiles()).filter(f -> f.getAbsolutePath().endsWith(".txt")).collect(Collectors.toList());
        ArrayList<TestCase> testCases = new ArrayList<>();
        for (File file: files) {
            try {
                TestCase tc = new TestCase(file);
                testCases.add(tc);
                System.out.println("[success] Loaded test case " + tc.getName() + " (" + file.getName() + ").");
            } catch (InvalidTestCaseFormatException e) {
                System.out.println("[error] Test case " + file.getName() + " invalid format.");
            } catch (IOException e) {
                System.out.println("[error] Test case " + file.getName() + " failed to load.");
            }
        }
        System.out.println("Running test cases...");
        for (TestCase tc: testCases) {
            tc.run("java -cp ../game/class/jegmezo.jar jegmezo.Program");
        }
    }
}
