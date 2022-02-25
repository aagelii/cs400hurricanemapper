public class test {
  public static void main(String[] args) {
    int x=4;
    int local_c=0;
    int local_8=0;
    while(local_8<28){
      local_c=local_c+x+local_8*local_8;
      local_8=local_8+1;
    }
    System.out.println(local_c);

    int param_1=4046;
    int correctParam=4052;
    System.out.println(correctParam*8+param_1);


  }
}
