public class ScramblerTest {
    public static void RunTest(){
        RunTest(10000,1000,1000);
    }

    public static void RunTest(final int elements, final int batchScrambleCount, final int batchCount){
        NaturalNumbersScrambler nns = new NaturalNumbersScrambler(elements);
        for (int i = 0; i < batchCount; i++) {
            nns.CheckValidity();
            nns.Scramble(batchScrambleCount);
        }
        nns.CheckValidity();
        System.out.println("PASSED, No invalidity found");
    }
}