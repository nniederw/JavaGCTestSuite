public class Main
{
    public static void main(String[] args)
    {
        NaturalNumbersScrambler nns = new NaturalNumbersScrambler(5000);
        for (int i = 0; i < 50; i++) {
            nns.CheckValidity();
            nns.Scramble(1000);
        }
        System.out.println("No invalidity found");
    }
}