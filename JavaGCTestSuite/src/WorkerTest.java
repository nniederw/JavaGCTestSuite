import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerTest {
    private Data[] datas = new Data[0];
    private final int ThreadCount = 8;
    private final int GarbageGeneratorThreadCount = 2;

    private Random rng = new Random(0);
    private int WorkTime = 0;

    public static void RunTest() throws InterruptedException {
        RunTest(10000, 100000);
    }

    public static void RunTest(int elements, int workTime) throws InterruptedException {
        WorkerTest workerTest = new WorkerTest();
        workerTest.datas = new Data[elements];
        for (int i = 0; i < elements; i++) {
            workerTest.datas[i] = new Data(i + 1);
        }
        workerTest.WorkTime = workTime;
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < workerTest.ThreadCount; i++) {
            workers.add(new Worker(workerTest));
            if(i < workerTest.GarbageGeneratorThreadCount){
                workers.get(i).GarbageGenerator = true;
            }
        }
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < workerTest.ThreadCount; i++) {
            threads.add(new Thread(workers.get(i)));
        }
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
        for (var worker : workers) {
            if(!worker.FinishedCorrectly){
                throw new RuntimeException("Unexpected events happened. Thread didn't finish correctly.");
            }
        }
        System.out.println("PASSED, No invalidity found");
    }

    private int RandomIndexInArr() {
        return rng.nextInt(datas.length);
    }

    public void SwitchMultipleTimes(final int amount) {
        for (int i = 0; i < amount; i++) {
            SwitchOneRandom();
        }
    }

    public void SwitchOneRandom() {
        Switch(RandomIndexInArr(), RandomIndexInArr());
    }

    private synchronized void Switch(final int i1, final int i2) {
        var d1 = datas[i1];
        var d2 = datas[i2];
        datas[i1] = d2;
        datas[i2] = d1;
    }

    public void GenerateGarbage() {
        GenerateGarbage(100);
    }

    public Data GenerateGarbage(final int objectCount) {
        Data res = null;
        for (int i = 0; i < objectCount; i++) {
            Data t = new Data(0); //let's hope this isn't optimized out
            res = t;
        }
        return res;
    }

    public void Work() { //calculates a high power of a number
        double startX = 1.00000081;
        double x = 1;
        int power = WorkTime * 100;
        for (int i = 0; i < power; i++) {
            x = x * x / x; //should increase work
            x *= startX;
        }
        System.out.println("Calculated the " + power + "'th power of " + startX + ", Result = " + x);
    }

    public synchronized void CheckValidity() {
        long sum = 0;
        for (Data data : datas) {
            sum += data.Number;
        }
        long exp = ExpectedSumOfNumbers();
        if (sum != exp) {
            throw new RuntimeException("Sum of numbers (" + sum + ") was not the expected sum: (" + exp + ")");
        }
    }

    private long ExpectedSumOfNumbers() {
        long n = datas.length;
        return (n * (n + 1)) / 2;
    }
}

class Data {
    public int Number;

    public Data(int number) {
        Number = number;
    }
}

class Worker implements Runnable {
    private final WorkerTest WorkerTest;
    public boolean GarbageGenerator = false;
    public boolean FinishedCorrectly = false;
    public Worker(WorkerTest workerTest) {
        WorkerTest = workerTest;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(GarbageGenerator){
                WorkerTest.GenerateGarbage(10000);
            }
            else {
                WorkerTest.SwitchMultipleTimes(5);
                WorkerTest.CheckValidity();
                WorkerTest.GenerateGarbage(10);
                WorkerTest.CheckValidity();
                WorkerTest.Work();
                WorkerTest.CheckValidity();
            }
        }
        FinishedCorrectly = true;
    }
}