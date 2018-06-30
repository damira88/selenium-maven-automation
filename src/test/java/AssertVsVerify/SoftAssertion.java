package AssertVsVerify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertion {

SoftAssert sa=new SoftAssert();
@Test
public void test1() {
	int i=100;
	int j=200;
	System.out.println("first assertion");
	sa.assertEquals(i, j);
	System.out.println("second  assertion");
	sa.assertNotEquals(i, j);
	System.out.println("============");
	sa.assertTrue(i>j);
//	sa.assertAll();
}
}
