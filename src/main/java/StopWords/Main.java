/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopWords;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
            ArrayList<String> FinalList=ReadTextFile("D:/netbeans projects/Engine/StopWords/SearchEngin/filename.txt");
            ArrayList<String> FinalList2=ReadTextFile("D:/netbeans projects/Engine/StopWords/SearchEngin/otherFile.txt");


            for(int a =0 ;a<FinalList.size();a++){
                System.out.print(" "+FinalList.get(a));
            }
            System.out.println("");
            for(int a=0;a<FinalList2.size();a++){
                System.out.print(" "+FinalList2.get(a));
            }
    
           
       }
    
    public static ArrayList<String> ReadStopWordFile(){ // return stopWord File as Arraylist for comparison with txt file
        Scanner StopWords=null;
        ArrayList<String> Stopwords = new ArrayList<String>();
        
        try {
          StopWords= new Scanner(new File("stopwordslist.txt"));

        }
        catch (FileNotFoundException e) {
        e.printStackTrace();  
        }
         Scanner Stop = new Scanner(StopWords.nextLine());
         while (Stop.hasNext()){
              String s = Stop.next();
                Stopwords.add(s);
                
         }
         return Stopwords;
         
    }
    
        public static ArrayList<String> ReadTextFile(String Path){      //compares stopWords array list with given txt file 
              Scanner WordList = null;
                int a =0;
                ArrayList<String> editedTxt = new ArrayList<String>();
                try {
                    WordList = new Scanner(new File(Path));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();  
                }
                while (WordList.hasNextLine()) {
                        Scanner List = new Scanner(WordList.nextLine());
           
                while (List.hasNext()) {  
                    String s = List.next();
                     ArrayList<String> Stop=ReadStopWordFile();
                    for(int x =0 ;x<Stop.size();x++){
                            if((Stop.get(x).equals(s))){
                                    s="";
                            }    
                         }
//                    System.out.print(" "+s);     
                    editedTxt.add(s);
             }
            }
                return editedTxt;
        }
    
    
    
    }
    
 


