package api.spec;
import lombok.Getter;

@Getter
public class Randomizer {
    private double randomDouble = Math.random(); // От 0.0 до 1.0

    @Getter
    private static Integer randomInt = (int) (Math.random() * 100000); // От 0 до 99
}
