public class Main {
    public static void main(String[] args) throws InterruptedException {
        var argslength = args.length;
        if (argslength == 0) {
            ScramblerTest.RunTest();
        } else {
            switch (args[0].toLowerCase()) {
                case "scrambler":
                    if (argslength == 1) {
                        ScramblerTest.RunTest();
                    } else if (argslength == 2) {
                        int n = Integer.parseInt(args[1]);
                        ScramblerTest.RunTest(n);
                    } else if (argslength == 3) {
                        int n1 = Integer.parseInt(args[1]);
                        int n2 = Integer.parseInt(args[2]);
                        ScramblerTest.RunTest(n1, n2);
                    } else {
                        int n1 = Integer.parseInt(args[1]);
                        int n2 = Integer.parseInt(args[2]);
                        int n3 = Integer.parseInt(args[3]);
                        ScramblerTest.RunTest(n1, n2, n3);
                    }
                    break;
                case "worker":
                    if (argslength == 1) {
                        WorkerTest.RunTest();
                    } else if (argslength == 2) {
                        int n = Integer.parseInt(args[1]);
                        WorkerTest.RunTest(n);
                    } else {
                        int n1 = Integer.parseInt(args[1]);
                        int n2 = Integer.parseInt(args[2]);
                        WorkerTest.RunTest(n1, n2);
                    }
                    break;
                case "barrier":
                    BarrierCountAndTest.GenerateReferenceWrites(10000);
                    break;
            }
        }
    }
}