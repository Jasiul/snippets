package com.yashuul.junit;

import static org.unit.Assert.*;

import org.junit.Test;

public class MathsTest {
	
	//Maths.sum
	// 1,2,3 => 6
	@Test
	public void sum_3num() {
		Maths maths = new Maths();
		int result = maths.sum(new int[]{1,2,3}]);
		
		assertEquals(6, result);
	}
}

////////////////

@RunWith(Parameterized.class)
public class FibonacciTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }  
           });
    }

    private int fInput;

    private int fExpected;

    public FibonacciTest(int input, int expected) {
        this.fInput = input;
        this.fExpected = expected;
    }

    @Test
    public void test() {
        assertEquals(fExpected, Fibonacci.compute(fInput));
    }
}

////////////////////

org.junit.Assert.assertThat 
assertThat(actual, is(equalTo(expected)));
assertThat(actual, is(not(equalTo(expected))));
assertThat("hello world", anyOf(is("hello world"), containsString("hello")));
assertThat("Sory", both(containsString("S")).and(containsString("r")));
assertThat(items, hasItems("Jon", "Jim"));
assertThat(items, everyItem(containsString("J")));
assertThat("Kek", allOf(equalTo("Kek"), startsWith("K")));
assertThat("Kek", not(allOf(equalTo("test"), containsString("test"))));

////////////////////////

@Test(expected = FileNotFoundException.class) 
    public void testReadFile() throws IOException { 
        //code X
    }
	
@Test
    public void testReadFile2() { 
        try {
            //code X
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            assertThat(e.getMessage(), is("Im a dog"));
        }
                 
    }

@Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testReadFile3() throws IOException {
                
        thrown.expect(IOException.class);
        thrown.expectMessage(startsWith("Im a god");
		//code X
    }
	
////////////////////////////
	
@RunWith(Parameterized.class)
public class SomeTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { "a a", 3 }, { "aa", 2 }, { "a", 1 }
           });
    }

    private String input;
    private int expected;
    
    public SomeTest(String input, int expected) {
        this.input = input;
        this.expected = expected;
    }
    
    @Test
    public void test() {        
        Letters letters = new Letters();
        assertThat(letters.getCharsNoSpaces(input), is(expected));
    }
	//////////////
	//or
	@Parameter 
    public int input;

    @Parameter(1)
    public int expected;

    @Test
    public void test() {
        assertEquals(expected, method(input));
    }

}