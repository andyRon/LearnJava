package objectStream;

public class Manager extends Employee {
    private Employee secretary;

    public Manager(String n, double s, int year, int month, int day) {
        super(n, s, year, month, day);
        secretary = null;
    }

    /**
     * Assigns a secretary to the manager.
     * @param s the secretary
     */
    public void setSecretary(Employee s) {
        secretary = s;
    }

    public String toString() {
        return super.toString() + "[secretary=" + secretary + "]";
    }
}
