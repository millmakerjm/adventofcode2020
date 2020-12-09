package eu.molenmaker.adventofcode.gameconsole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instruction {
    private Operation operation;
    private long argument;


    private int executionCounter = 0;

    private static Pattern instructionPattern = Pattern.compile("([a-z]+)\\s([+-]{1}[0-9]+)");


    public Instruction(Operation operation, long argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public static Instruction parseLine(String line) {
        Instruction parsedInstruction = null;
        Matcher matcher = instructionPattern.matcher(line);
        if (matcher.find()) {
            Operation op = Operation.valueOf(matcher.group(1));
            Long argument = Long.parseLong(matcher.group(2));
            parsedInstruction = new Instruction(op, argument);
        }
        return parsedInstruction;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public long getArgument() {
        return argument;
    }

    public void setArgument(long argument) {
        this.argument = argument;
    }

    public int getExecutionCounter() {
        return executionCounter;
    }

    public void resetExecution() {
        executionCounter = 0;
    }

    public void setExecuted() {
        executionCounter++;
    }
}
