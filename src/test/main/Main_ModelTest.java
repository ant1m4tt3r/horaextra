package main;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class Main_ModelTest {

	@Test
	public void testCheckFile() throws Exception {
		String pathEmpty = "";
		String pathNull = null;
		String pathNotEmpty = "teste";
		Main_Model model = new Main_Model(null);
		Method method = Main_Model.class.getDeclaredMethod("checkFile", String.class);
		Assert.assertFalse((boolean) method.invoke(model, pathEmpty));
		Assert.assertFalse((boolean) method.invoke(model, pathNull));
		Assert.assertTrue((boolean) method.invoke(model, pathNotEmpty));
	}

}
