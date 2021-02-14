package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileOutputReader {

    private String text;
    public FileOutputReader(String path){
        try {
            this.text="";
            File fileObj = new File(path);
            Scanner fileReader = new Scanner(fileObj);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                data+=" ";
                this.text+=data;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }
    public static HashMap<String,Integer> getFrequencyMapFromText(String fileText){
        fileText = fileText.toLowerCase();
        String[] splited = fileText.trim().split(" ");
        HashMap<String,Integer> frequencyMap = new HashMap<String,Integer>();
        for(int index=0;index<splited.length;index++){
            int newFrequency;
            if (frequencyMap.containsKey(splited[index]))
                newFrequency=frequencyMap.get(splited[index])+1;
            else
                newFrequency=1;
            frequencyMap.put(splited[index],newFrequency);
        }
        return frequencyMap;
    }
    public static String mostFrequentAndLeastFrequentWord(String fileText, Boolean shouldReturnMostFrequent) {
        Map<String,Integer> frequencyMap= getFrequencyMapFromText(fileText);
        String lowestFrequencyWord="";
        String highestFrequencyWord="";
        int lowestFrequency=Integer.MAX_VALUE;
        int highestFrequency=Integer.MIN_VALUE;
        for(String string : frequencyMap.keySet()){
            if(lowestFrequency>frequencyMap.get(string)){
                lowestFrequency=frequencyMap.get(string);
                lowestFrequencyWord=string;
            }
            if(highestFrequency<frequencyMap.get(string)){
                highestFrequency=frequencyMap.get(string);
                highestFrequencyWord=string;
            }
        }
        if (shouldReturnMostFrequent)
            return highestFrequencyWord;
        else
            return lowestFrequencyWord;
    }
    public static Boolean isAlphabet(Character c) {
        if( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            return true;
        else
            return false;
    }
    public static int getTotalConsonantsandVowels(String fileText, Boolean gettingConsonants){
        int consonants=0;
        int vowels=0;
        for(int index=0;index<fileText.length();index++){
            if(isAlphabet(fileText.charAt(index)) && fileText.charAt(index)!='a' && fileText.charAt(index)!='e'
                    && fileText.charAt(index)!='i' && fileText.charAt(index)!='o' && fileText.charAt(index)!='u' )
                consonants++;
            if(isAlphabet(fileText.charAt(index)) && (fileText.charAt(index)=='a' || fileText.charAt(index)=='e'
                    || fileText.charAt(index)=='i' || fileText.charAt(index)=='o' || fileText.charAt(index)=='u') )
                vowels++;
        }
        if(gettingConsonants)
            return consonants;
        return vowels;
    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public void printAllFromMapWithGivenFrequency(HashMap<String,Integer> frequencyMap, String givenWord){
        int givenFrequency = frequencyMap.get(givenWord);
        String output="[";
        for(String string : frequencyMap.keySet()){
            if(frequencyMap.get(string)==givenFrequency){
                output+=(string + " (" + givenFrequency +"), ");
            }
        }
        System.out.println(output.substring(0,output.length()-2)+"]");
    }
    public static void main(String[] args) {
        FileOutputReader file = new FileOutputReader("src/com/main/file.txt");
        String highestFrequencyWord = mostFrequentAndLeastFrequentWord(file.getText(),true);
        System.out.println("Word With most occurrences : ");
        file.printAllFromMapWithGivenFrequency(getFrequencyMapFromText(file.getText()),highestFrequencyWord);
        String lowestFrequencyWord = mostFrequentAndLeastFrequentWord(file.getText(),false);
        System.out.println("Word With least occurrences : ");
        file.printAllFromMapWithGivenFrequency(getFrequencyMapFromText(file.getText()),lowestFrequencyWord);
        int totalWords=getFrequencyMapFromText(file.getText()).size();
        System.out.println("Total Number Of Unique Words: " + totalWords);
        int totalVowels= getTotalConsonantsandVowels(file.getText(),false);
        System.out.println("Total Number Of Vowels: " + totalVowels);
        int totalConsonants= getTotalConsonantsandVowels(file.getText(),true);
        System.out.println("Total Number Of Consonants: " + totalConsonants);
        HashMap<String, Integer> sortedByFrequencyMap = sortByValue(getFrequencyMapFromText(file.getText()));
        System.out.println("Descending Order By Frequency " +sortedByFrequencyMap.keySet());
    }
}
