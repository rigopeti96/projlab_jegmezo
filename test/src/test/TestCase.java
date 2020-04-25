package test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase {
    private String name;
    private List<String> inputLines = new ArrayList<>();
    private List<String> outputLines = new ArrayList<>();
    private List<String> actualOutputLines = new ArrayList<>();
    private int currentLine = 0;

    public TestCase(File file) throws IOException, InvalidTestCaseFormatException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        try {
            String line;
            while ((line = reader.readLine()) != null && !line.equals("# Input")) {
                if (line.startsWith("#")) continue;
                name = line;
                reader.readLine();
                break;
            }

            while ((line = reader.readLine()) != null && !line.equals("# Output")) {
                if (line.startsWith("#")) continue;
                inputLines.add(line);
            }

            if (line == null) throw new InvalidTestCaseFormatException();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;
                outputLines.add(line);
            }
        }
        catch (IOException | InvalidTestCaseFormatException e) {
            throw e;
        }
        finally {
            reader.close();
        }
    }

    public String getName() {
        return name;
    }

    public boolean run(String processName) {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder();

            File tempInput = new File("tempInput.txt");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempInput), StandardCharsets.UTF_8));
            for (String line: inputLines) writer.write(line + "\n");
            writer.close();
            Process process = processBuilder.command(processName.split(" "))
                    .redirectInput(new File("tempInput.txt"))
                    .redirectOutput(new File("tempOutput.txt"))
                    .start();

            process.waitFor(5000, TimeUnit.MILLISECONDS);

            File tempOutput = new File("tempOutput.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempOutput), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                actualOutputLines.add(line);
            }
            tempInput.delete();
            tempOutput.delete();

            currentLine = -1;
            while (true) {
                currentLine++;
                if (currentLine == actualOutputLines.size() && currentLine == outputLines.size()) break;
                String actualLine = currentLine < actualOutputLines.size() ? actualOutputLines.get(currentLine): null;
                if (currentLine >= outputLines.size() || !outputLines.get(currentLine).equals(actualLine)) {
                    System.out.println("[error] " + name);
                    System.out.println();
                    System.out.println("Expected output:");
                    if (currentLine - 2 >= 0  && currentLine - 2 < outputLines.size()) System.out.println(outputLines.get(currentLine - 2));
                    if (currentLine - 1 >= 0  && currentLine - 1 < outputLines.size()) System.out.println(outputLines.get(currentLine - 1));
                    if (currentLine >= 0  && currentLine < outputLines.size()) System.out.println(outputLines.get(currentLine));
                    System.out.println("Actual output:");
                    if (currentLine - 2 >= 0 && currentLine - 2 < actualOutputLines.size()) System.out.println(actualOutputLines.get(currentLine - 2));
                    if (currentLine - 1 >= 0 && currentLine -1 < actualOutputLines.size()) System.out.println(actualOutputLines.get(currentLine - 1));
                    if (currentLine >= 0 && currentLine < actualOutputLines.size()) System.out.println(actualOutputLines.get(currentLine));
                    System.out.println();
                    return false;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("[error] " + name);
            System.out.println();
            System.out.println("Test failed because of an IOException " + e.toString());
            if (currentLine >= 0) System.out.println("Expected output:");
            if (currentLine - 2 >= 0) System.out.println(outputLines.get(currentLine - 2));
            if (currentLine - 1 >= 0) System.out.println(outputLines.get(currentLine - 1));
            if (currentLine >= 0) System.out.println(outputLines.get(currentLine));
            if (currentLine >= 0) System.out.println("Actual output:");
            if (currentLine - 2 >= 0) System.out.println(actualOutputLines.get(currentLine - 2));
            if (currentLine - 1 >= 0) System.out.println(actualOutputLines.get(currentLine - 1));
            if (currentLine >= 0) System.out.println(actualOutputLines.get(currentLine));
            System.out.println();
            return false;
        } catch (InterruptedException e) {
        }
        finally {

        }

        System.out.println("[success] " + name);
        return true;
    }
}
