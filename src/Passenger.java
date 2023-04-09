public class Passenger {
    String FirstName;
    String SureName;
    int Expense;

    public Passenger() {
        this.FirstName = "empty";
        this.SureName = "empty";
        this.Expense = 0;
    }

    public void setFirstName(String first_name) {
        this.FirstName = first_name;
    }

    public void setSureName(String sure_name) {
        this.SureName = sure_name;
    }

    public void setExpense(int expense) {
        this.Expense = expense;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSureName() {
        return SureName;
    }

    public int getExpense() {
        return Expense;
    }
}