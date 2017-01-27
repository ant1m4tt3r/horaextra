package models;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class WriterTest {

	@Test(expected = NullPointerException.class)
	public void fileOutNotNull() {
		new Writer(null);
	}

	@Test(expected = NullPointerException.class)
	public void fileOutNotEmpty() {
		new Writer("");
	}

	@Test
	public void formattDate() throws Exception {
		String oldFomat = "0001/01/01";
		String newFomat = "01/01/0001";
		Writer writer = new Writer("notNull");
		Method method = Writer.class.getDeclaredMethod("formattDate", String.class);
		method.setAccessible(true);
		Assert.assertEquals(newFomat, method.invoke(writer, oldFomat));
	}

}
