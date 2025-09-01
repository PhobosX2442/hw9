package api.spec;
import lombok.Getter;

@Getter
public class Randomizer {
    private double randomDouble = Math.random();

    @Getter
    private static Integer randomInt = (int) (Math.random() * 100000000);
}
