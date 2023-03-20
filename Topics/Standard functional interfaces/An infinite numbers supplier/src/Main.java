import java.util.function.*;

class FunctionUtils {

    public static Supplier<Integer> getInfiniteRange() {
        final int[] i = {0};
        while (true) {
            return () -> i[0]++;
        }
    }

}