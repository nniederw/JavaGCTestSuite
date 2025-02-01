public class BarrierCountAndTest {
    private static ReferenceObject obj;
    public static void GenerateReferenceWrites(int count){
        System.out.println("Start of GenerateReferenceWrites");
        obj = new ReferenceObject(); //this should be one reference write, prob on the heap
        for (int i = 0; i < count-1; i++) {
            if(i % 2 == 0){
                obj.obj = obj;
            }
            else {
                obj.obj = null;
            }
        }
        System.out.println("Should have observed about "+count+" object reference writes");
    }
    static class ReferenceObject{
        public Object obj;
    }
}