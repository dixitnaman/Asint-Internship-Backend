public class TryCatch{
    public static void main(String[] args) {
        try{
            int result = 10/0;
            System.out.println(result);
        }catch(ArithmeticException e){
            System.out.println("Cannot divide by Zero");
        }finally{
            System.out.println("Finally Statement");
        }
    }
}