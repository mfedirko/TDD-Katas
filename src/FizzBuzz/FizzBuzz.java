package FizzBuzz;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FizzBuzz {
    private List<Consumer<String>> consumers = new ArrayList<>();
    void addLineConsumer(Consumer<String> lineConsumer) {
        consumers.add(lineConsumer);
    }

    public void execute() {
        for (int i = 1; i <= 100; i++) {
            final String output = getOutput(i);
            consumers.forEach(cons -> cons.accept(output));
        }
    }

    private String getOutput(int i) {
        String output = "";
        if (i % 3 == 0) {
             output += "Fizz";
        }
        if (i % 5 == 0) {
             output += "Buzz";
        }
        if (output.isEmpty()){
             output = String.valueOf(i);
        }
        return output;
    }
}
