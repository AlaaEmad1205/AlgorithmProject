package PhoneNetworkApp;

import GraphFramework.*;

public class Office extends Vertex{
    
  private char OfficeNo;

    public Office() {

    }
    
    public Office(String label) {
      super(label);
    }
    
    public Office(int label, char OfficeNo) {
      super(Integer.toString(label));
      this.OfficeNo = OfficeNo;
    }
   
    @Override
    public void displayInfo(){
      System.out.print("Office No." + this.OfficeNo);

    }

    public char getOfficeNo(){

      return OfficeNo;
    }

}
