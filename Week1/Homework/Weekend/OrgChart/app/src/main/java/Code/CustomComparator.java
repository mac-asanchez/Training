package Code;

import java.util.Comparator;

public class CustomComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        //return o1.getManagerId().compareTo(o2.getManagerId());
        if (o1.getManagerId() == null){
            return -1;
        }

        int c;
        c = o1.getManagerId().compareTo(o2.getManagerId());
        if (c == 0){
            c = o1.getEmployeeId().compareTo(o2.getEmployeeId());
        }

        return c;
    }
}