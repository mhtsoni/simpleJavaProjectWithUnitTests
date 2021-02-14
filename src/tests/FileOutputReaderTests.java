package tests;

import com.main.FileOutputReader;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class FileOutputReaderTests {
    FileOutputReader file;
    @Before
    public void init(){
        file = new FileOutputReader("src/tests/tests.txt");
    }
    @Test
    public void FileOutputReaderTest(){
        String actual = file.getText();
        String expected = "Muskan Jhamb ";
        assertEquals(expected,actual);
    }
    @Test
    public void getFrequencyMapFromTextTest(){
        HashMap<String,Integer> actual = file.getFrequencyMapFromText("Muskan Muskan Jhamb");
        HashMap<String,Integer> expected = new HashMap<String,Integer>();
        expected.put("muskan",2);
        expected.put("jhamb",1);
        assertEquals(expected,actual);
    }
    @Test
    public void mostFrequentAndLeastFrequentWordTest(){
        String actualHighest = file.mostFrequentAndLeastFrequentWord("Muskan Muskan Jhamb",true);
        String actualLowest = file.mostFrequentAndLeastFrequentWord("Muskan Muskan Jhamb",false);
        String expectedHighest="muskan";
        String expectedLowest="jhamb";
        assertEquals(expectedHighest,actualHighest);
        assertEquals(expectedLowest,actualLowest);
    }
    @Test
    public void isAlphabetTest(){
        Boolean expected = true;
        Boolean actual = file.isAlphabet('a');
        assertEquals(expected,actual);
    }
    @Test
    public void getTotalConsonantsTest(){
        int expected=2;
        int actual = file.getTotalConsonantsandVowels("a e i o u b c",true);
        assertEquals(expected,actual);
    }
    @Test
    public void sortByValueTest(){
        HashMap<String,Integer> inputHashMap = new HashMap<String,Integer>();
        inputHashMap.put("Muskan",2);
        inputHashMap.put("Jhamb",1);
        inputHashMap.put("is",1);
        inputHashMap.put("cool",4);
        HashMap<String,Integer> actual = file.sortByValue(inputHashMap);
        HashMap<String,Integer> expected = new HashMap<String,Integer>();
        expected.put("cool",4);
        expected.put("Muskan",2);
        expected.put("Jhamb",1);
        expected.put("is",1);
        assertEquals(expected,actual);
    }
}
