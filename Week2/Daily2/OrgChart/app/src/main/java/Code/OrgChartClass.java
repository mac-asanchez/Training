package Code;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class OrgChartClass {
    public String Print(String[] Input) {
        //region Validates Input
        if (Input == null) {
            return "";
        }
        //endregion

        //region Variables Declaration
        StringBuilder sbResult = new StringBuilder();
        List<Employee> Employees = new LinkedList<Employee>();
        //endregion

        //region Fill Employees
        for (int i = 0; i < Input.length; i++) {
            //region Get Management
            String[] Management = Input[i].split(("\\s*,\\s*"));
            //endregion

            //region Add Manager
            String ManagerId = Management[0];
            Employee emMgr = new Employee();
            emMgr.setEmployeeId(Management[0]);
            Employees.add(emMgr);
            //endregion

            //region Add Employees
            for (int j = 1; j < Management.length; j++) {
                Employee em = new Employee();
                em.setManagerId(ManagerId);
                em.setEmployeeId(Management[j]);
                Employees.add(em);
            }
            //endregion
        }
        //endregion

        //region Remove Duplicates
        List<Employee> IndexToRemove = new ArrayList<Employee>();

        for (int i = 0; i < Employees.size(); i++) {
            Employee em1 = Employees.get(i);
            if (em1.getManagerId().equals("")) {
                for (Employee em2 : Employees) {
                    if (em1.getEmployeeId().equals(em2.getEmployeeId()) && !em2.getManagerId().equals("")) {
                        IndexToRemove.add(em1);
                        break;
                    }
                }
            }
        }

        for (int i = IndexToRemove.size() - 1; i >= 0; i--) {
            Employees.remove(IndexToRemove.get(i));
        }
        //endregion

        Collections.sort(Employees, new CustomComparator());

        //region get Employees based on Upper Manager (without Manager)
        for (Employee em : Employees) {
            if (em.getManagerId().equals("")) {
                String EmployeeId = em.getEmployeeId();
                sbResult.append(EmployeeId).append("\n");
                sbResult.append(getChildren(EmployeeId, 0, Employees));
            }
        }
        //endregion

        return sbResult.toString();
    }

    private String getChildren(String EmployeeId, int tabs, List<Employee> Employees) {
        StringBuilder sb = new StringBuilder();

        for (Employee em : Employees) {
            String ManagerId = em.getManagerId();
            if (ManagerId.equals(EmployeeId)) {
                sb.append(Tabs(tabs + 1)).append(em.getEmployeeId()).append("\n");
                sb.append(getChildren(em.getEmployeeId(), tabs + 1, Employees));
            }
        }

        return sb.toString();
    }

    public String Tabs(int TabNumber) {
        String Result = "";
        for (int i = 0; i < TabNumber; i++) {
            Result += "\t";
        }

        return Result;
    }
}
