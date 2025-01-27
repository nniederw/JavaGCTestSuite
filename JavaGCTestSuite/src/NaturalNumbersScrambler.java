import java.util.LinkedList;
import java.util.Random;

public class NaturalNumbersScrambler
{
    private int Elements;
    private LinkedList<Integer> Numbers = new LinkedList<>();
    private Random rng = new Random(0);

    public NaturalNumbersScrambler(int elements)
    {
        Elements = elements;
        FillList();
    }

    public void CheckValidity()
    {
        long exp = ExpectedSumOfNumbers();
        long sum = SumOfNumbers();
        if (exp != sum) {
            throw new RuntimeException("Sum of numbers (" + sum + ") was not the expected sum: (" + exp + ")");
        }
    }

    public void Scramble(int quantity)
    {
        for (int i = 0; i < quantity; i++) {
            Scramble();
        }
    }
    private void Scramble(){
        Integer toReAdd = RandomValueInList();
        Numbers.remove(toReAdd);
        Numbers.add(toReAdd);
    }
    private int RandomValueInList()
    {
        return rng.nextInt(Elements) + 1;
    }

    ///Adds 1 ... Elements numbers to the list
    private void FillList()
    {
        Numbers = new LinkedList<>();
        for (int i = 0; i < Elements; i++) {
            Numbers.add(new Integer(i + 1));
        }
    }

    private long ExpectedSumOfNumbers()
    {
        long n = Elements;
        return (n * (n + 1)) / 2;
    }

    private long SumOfNumbers()
    {
        return Numbers.stream().mapToLong(Integer::intValue).sum();
    }

}