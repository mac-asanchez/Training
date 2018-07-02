package Code;

public class Employee {
    private String EmployeeId;
    private String ManagerId;
    private int Position;

    public Employee() {
        EmployeeId = "";
        ManagerId = "";
        Position = 0;
    }

    public Employee(String employeeId, String managerId, int position) {
        EmployeeId = employeeId;
        ManagerId = managerId;
        Position = position;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getManagerId() {
        return ManagerId;
    }

    public void setManagerId(String managerId) {
        ManagerId = managerId;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
}
