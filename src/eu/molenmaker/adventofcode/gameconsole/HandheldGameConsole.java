package eu.molenmaker.adventofcode.gameconsole;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

enum ProgramState {IDLE, RUNNING, HALT, FINISHED};

public class HandheldGameConsole {
    ArrayList<Instruction> instructions = new ArrayList<>();
    private long accumulator = 0;
    private long instructionPointer = 0;
    private ProgramState state = ProgramState.IDLE;
    private int lineToPatch = 0;
    private boolean patched = false;

    public ProgramState run() {
        if (instructions.size() > 0) {
            state = ProgramState.RUNNING;
        }
        while (state == ProgramState.RUNNING) {
            Instruction currentInstruction = instructions.get((int)instructionPointer);
            long currentInstructionPointer = instructionPointer;
            Operation currentOperation = currentInstruction.getOperation();

            if (instructionPointer == lineToPatch) {
                if (currentOperation == Operation.nop) {
                    currentOperation = Operation.jmp;
                } else if (currentOperation == Operation.jmp) {
                    currentOperation = Operation.nop;
                }
            }

            currentInstruction.setExecuted();
            switch (currentOperation) {
                case nop:
                    instructionPointer++;
                    break;
                case acc:
                    accumulator += currentInstruction.getArgument();
                    instructionPointer++;
                    break;
                case jmp:
                    instructionPointer += currentInstruction.getArgument();
                    break;
            }

            System.out.println(String.format("%5d %5s arg: %2d cnt: %5d acc: %5d", currentInstructionPointer, currentInstruction.getOperation(), currentInstruction.getArgument(), currentInstruction.getExecutionCounter(), accumulator));
            if (instructionPointer >= instructions.size()) {
                state = ProgramState.FINISHED;
            } else  if (instructions.get((int)instructionPointer).getExecutionCounter() > 0) {
                state = ProgramState.HALT;
            }
        }
        return state;
    }

    public void reset() {
        accumulator = 0;
        instructionPointer = 0;
        state = ProgramState.IDLE;
        patched = false;
        for (Instruction i : instructions) {
            i.resetExecution();
        }
    }

    private void setNextLineToPath() {

        for (int i = lineToPatch+1; i < instructions.size(); i++) {
            Operation o = instructions.get(i).getOperation();
            if (o == Operation.jmp || o == Operation.nop) {
                lineToPatch = i;
                return;
            }
        }
    }

    public void runWithPatching() {
        ProgramState endState;
        lineToPatch = -1;
        do {
            reset();
            setNextLineToPath();
            System.out.println(String.format("STARTING RUN -> patch line %d", lineToPatch));
            endState = run();

        } while (endState != ProgramState.FINISHED);

    }

    void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    public static HandheldGameConsole parseProgram(String fileName) throws IOException {
        HandheldGameConsole console = new HandheldGameConsole();
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        for (String line: lines) {
            console.addInstruction(Instruction.parseLine(line));
        }
        return console;
    }

}
