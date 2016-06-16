package CountWords;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Comparator;
import java.util.HashMap;


public class textCounting {

   public Map<String, Integer> getWordCount(String fileName){
 
        FileInputStream loadmyfile = null;
        DataInputStream importmyData = null;
        BufferedReader br = null;
        Map<String, Integer> MapMyWords = new HashMap<String, Integer>();
        try {
            loadmyfile = new FileInputStream(fileName);
            importmyData = new DataInputStream(loadmyfile);
            br = new BufferedReader(new InputStreamReader(importmyData));
            String line = null;
            while((line = br.readLine()) != null){
                StringTokenizer myfavstrings = new StringTokenizer(line, "  -,.!?:;'");
                while(myfavstrings.hasMoreTokens()){
                    String textString = myfavstrings.nextToken().toLowerCase();
                    
                    if(MapMyWords.containsKey(textString)){
                    	//
                    	
                    	int counter = textString.length();
                    	if(counter > 4){
                    		MapMyWords.put(textString, MapMyWords.get(textString)+1);
                    	}
                        
                    } else {
                    	//
                        MapMyWords.put(textString, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return MapMyWords;
    }
     
    public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){
         
        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
    }
     
    public static void main(String a[]){
    	textCounting mdc = new textCounting();
        Map<String, Integer> wordMap = mdc.getWordCount("C:/Users/Samima/workspace/WordCount/macbeth.txt");
        List<Entry<String, Integer>> list = mdc.sortByValue(wordMap);
        int countforsecond=1;
        for(Map.Entry<String, Integer> entry:list){
        	
        	if(countforsecond==2){
            System.out.println(entry.getKey()+" ==> "+entry.getValue());
        	}
        	countforsecond++;
        }
    }
}