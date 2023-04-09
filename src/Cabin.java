public class Cabin extends Passenger {
    String CabinName;
    int CabinNum, PassengerNum;

    public Cabin() {
        this.CabinName = "empty";
    }

    public void setCabinName(String cabin_name) {
        this.CabinName = cabin_name;
    }

    public String getCabinName() {
        return CabinName;
    }

    public void Expense() {
        System.out.println(FirstName+" "+SureName+" Expense = "+Expense);
    }

}