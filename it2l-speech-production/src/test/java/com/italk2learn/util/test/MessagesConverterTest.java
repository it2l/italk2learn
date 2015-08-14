package com.italk2learn.util.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import static org.junit.Assert.*;

import com.italk2learn.util.MessagesConverter;
import com.italk2learn.util.MessagesConverter.Language;

public class MessagesConverterTest {

    private static Map<String,String> sentenceMap = null;

    @BeforeClass
    public static void initSentenceMap() {
	  sentenceMap = new HashMap<String,String>();
	  sentenceMap.put("NoSpacesHere", "NoSpacesHere");
	  sentenceMap.put(" just trim ", "just trim");
	  sentenceMap.put("only trim at the end ", "only trim at the end");
	  sentenceMap.put("a   B", "a B");
	  sentenceMap.put(" a   c", "a c");
	  sentenceMap.put("a   d ", "a d");
	  sentenceMap.put(" a   e ", "a e");
	  sentenceMap.put(" a f ", "a f");
	  sentenceMap.put(" a  g ", "a g");
	  sentenceMap.put("    ", "");
	  sentenceMap.put("", "");
	  sentenceMap.put("A long   space", "A long space");
	  sentenceMap.put("  Another long   space", "Another long space");
    }

    @Test
    public void testStringNormalisation() {
	  for (String str : sentenceMap.keySet()) {
		assertEquals(sentenceMap.get(str), MessagesConverter.normaliseString(str));
	  }
    }
    
    @Test
    public void testSentencesEnglish() {
	  Map<String,String> map = new HashMap<String,String>();
	  map.put("5/2","five halves");
	  map.put(" 2/3","two thirds");
	  map.put("5 /6","five sixths");
	  map.put("4/ 5","four fifths");
	  map.put(" 9 / 10 ","nine tenths");
	  map.put("Now create 1/2.","Now create one half.");
	  map.put("Now create 1 /2.","Now create one half.");
	  map.put("Now create 1/ 2.","Now create one half.");
	  map.put("Now create 1 / 2.","Now create one half.");
	  map.put("Now create 1   / 2.","Now create one half.");
	  map.put("Now create 1/2 .","Now create one half .");
	  map.put("Now create 1/ 4 and 3 / 8...","Now create one quarter and three eighths...");
	  map.put("Is that 3 /4?","Is that three quarters?");
	  map.put("Now create1/2.","Now createone half."); // feature, not bug
	  map.put(" 3/ 4 plus 1/2 is how much?","three quarters plus one half is how much?");
	  for (String msg : map.keySet()) {
		assertEquals(MessagesConverter.transformFractionsInString(msg, Language.EN), map.get(msg));
	  }
    }

    @Test
    public void testFractionsEnglish() {
	  assertEquals(MessagesConverter.transformFractions(1,2, Language.EN), "one half");
	  assertEquals(MessagesConverter.transformFractions(1,3, Language.EN), "one third");
	  assertEquals(MessagesConverter.transformFractions(1,4, Language.EN), "one quarter");
	  assertEquals(MessagesConverter.transformFractions(1,5, Language.EN), "one fifth");
	  assertEquals(MessagesConverter.transformFractions(3,2, Language.EN), "three halves");
	  assertEquals(MessagesConverter.transformFractions(5,6, Language.EN), "five sixths");
	  assertEquals(MessagesConverter.transformFractions(3,4, Language.EN), "three quarters");
	  assertEquals(MessagesConverter.transformFractions(12,20, Language.EN), "twelve twentieths");
    }
}
