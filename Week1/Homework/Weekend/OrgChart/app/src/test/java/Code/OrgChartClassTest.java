package Code;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrgChartClassTest {

    @Test
    public void print() {
        String[] Input = new String[]{"B2,E5,F6", "A1,B2,C3,D4", "D4,G7,I9", "G7,H8"};

        OrgChartClass og = new OrgChartClass();

        String result = og.Print(Input);

        System.out.println(result);
    }
}