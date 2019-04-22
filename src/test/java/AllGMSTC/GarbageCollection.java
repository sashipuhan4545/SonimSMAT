package AllGMSTC;



public class GarbageCollection
{
   public static void main(String s[]) throws Exception
   {
      
	   
	   GarbageCollection f=new GarbageCollection();
	   GarbageCollection b=new GarbageCollection();
	   f=null;
	   b=null;
	   Runtime.getRuntime().gc();
	   
	   
   }
   
   public void finalize() {
	  
	   System.out.println("CALiiing");
	   
   }
}