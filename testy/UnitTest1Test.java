import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTest1Test
{
    @Test
    public void test()
    {
        UnitTest1 test = new UnitTest1();
        int result = test.addNumber(5,2);
        assertEquals(7,result);

    }
}