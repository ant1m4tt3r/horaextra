package models;

import org.junit.Test;

public class ReaderTest {

	@Test(expected = NullPointerException.class)
	public void fileInNotNull() {
		new Reader(null);

	}

	@Test(expected = NullPointerException.class)
	public void fileInNotEmpty() {
		new Reader("");
	}

}
