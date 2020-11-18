/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopWords;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;


/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
//                    final File folder = new File("D:/netbeans projects/Engine/StopWords/TargetForStopwords");
//                    final List<File> fileList = Arrays.asList(folder.listFiles(new FileFilter() {
//                    public boolean accept(File pathname) {
//                        return pathname.isFile();
//                    }
//                }));
//                     for(int a=0;a<fileList.size();a++){
//                         ReFormatFile(fileList.get(a).toString(),"D:/netbeans projects/Engine/StopWords/DestinationForStopWords/"+GetNameOnly(fileList.get(a).getName())+".stp");
//                     } 


//                  ReFormatFile("D:/netbeans projects/Engine/StopWords/SearchEngin/test.txt", "ForTest.txt");  //test Statement



                final File folder = new File("D:/netbeans projects/Engine/StopWords/DestinationForStopWords");
                    final List<File> fileList = Arrays.asList(folder.listFiles(new FileFilter() {
                    public boolean accept(File pathname) {
                        return pathname.isFile();
                    }
                }));
                for(int b=0;b<fileList.size();b++){
                      addFile("D:/netbeans projects/Engine/StopWords/DestinationStemmed/"+GetNameOnly(fileList.get(b).getName())+".sfx",
                              FileTOArrayList("D:/netbeans projects/Engine/StopWords/DestinationForStopWords/"+GetNameOnly(fileList.get(b).getName())+".stp"));
                }
                
//                 

//                    char[] a ={ 'p', 'l', 'a', 'y', 'i','n','g' }; 
//                    char[] b ={ 'u', 'n', 'i', 'v', 'e','r','s','i','t','i','e','s' };  
//                    char[] c ={'i','-','g','o','i','n','g'}; 
//                    char[] d ={ 'u', 'n', 'i', 'v', 'e','r','s','i','t','y' }; 
//
//                     //stemmer takes char array from string stemms it and return string(using s.toString() method
//                    Stemmer s = new Stemmer();
//                    s.add(a);
//                    s.stem();
//                    System.out.println(s.toString()+" ");
//                    s.add(b);
//                    s.stem();
//                    System.out.println(s.toString()+" ");
//                    s.add(c);
//                    s.stem();
//                    System.out.println(s.toString()+" ");
//                    s.add(d);
//                    s.stem();
//                    System.out.println(s.toString()+" ");
                    //universities university 
                    
    }
        public static String GetNameOnly(String s){
            int Dot=s.indexOf(".");
            return s.substring(0,Dot);
        }
        public static ArrayList<String> ReadStopWordFile(){ // return stopWord File as Arraylist for comparison with txt file
        ArrayList<String> words = new ArrayList<>();
        try {
          FileInputStream fis=new FileInputStream("stopwordslist.txt"); 
            Scanner Stop = new Scanner(fis);
          while (Stop.hasNextLine()){
              String s = Stop.nextLine().toLowerCase();
                words.add(s);
         }
        }
        catch (FileNotFoundException e) {
        e.printStackTrace();  
        }
         return words;
    }
        public static ArrayList<String> ReadTextFile(String Path){      //compares stopWords array list with given txt file 
              Scanner WordList = null;
                ArrayList<String> editedTxt = new ArrayList<String>();
                try {
                    WordList = new Scanner(new File(Path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();  
                }
                while (WordList.hasNextLine()) {
                        Scanner List = new Scanner(WordList.nextLine());
                while (List.hasNext()) {  
//                    String s = List.next().toLowerCase().replaceAll("#"," ");
                    String s = List.next().toLowerCase();       
                     ArrayList<String> Stop=ReadStopWordFile();
                                s=CheckPunchString(s);
                                s=Checkbrackets(s);
                    for(int x =0 ;x<Stop.size();x++){
                            if((Stop.get(x).equals(s)))
                            {
                                s="*";
                            }
                         }
                    editedTxt.add(s);     
             }
               for (int x=0;x<editedTxt.size();x++){
                   editedTxt.remove("*"); 
                }
            }
                return editedTxt;
        }
        public static void addFile(String FileName,ArrayList arrayList){ //creates file and adds the filtered arralist to it 
            try {
                  File myObj = new File(FileName);
                  if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                  } else {
                    System.out.println("File already exists.");
                  }
                } catch (IOException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
                }
            try {
                FileWriter myWriter = new FileWriter(FileName);
               for(int a=0;a<arrayList.size();a++) {
                    myWriter.write(arrayList.get(a)+"\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }
        public static void ReFormatFile(String path,String FileName){// for conviance takes original file path and new file name to give the formated file 
             ArrayList<String> FinalList=ReadTextFile(path);
             addFile(FileName, FinalList);
        }
        public static String CheckPunchString(String s){//checks for punct elemnts
            if(s.length()>0)
               { String LastElement= s.substring(s.length() - 1);
                 if(LastElement.equals(".")||LastElement.equals(",")||LastElement.equals("/")){
                    return s.substring(0,(s.length() - 1));
                }
                else{
                    return s;
                }
               }else{
                return s;
            }
        }
        public static String Checkbrackets(String s){ // remove both brakets from a single word or
            if(s.contains("(")&&s.contains(")")){
                return s.substring(1,(s.length()-1));
            }if(s.contains("(")){
                return s.substring(1,s.length());
            }if(s.contains(")")){
                return s.substring(0,s.length()-1);
            } 
            return s;
        }
        public static ArrayList FileTOArrayList(String Path){
            ArrayList<String> result = new ArrayList<>();
	try (FileReader f = new FileReader(Path)) {
	    StringBuffer sb = new StringBuffer();
	    while (f.ready()) {
	        char c = (char) f.read();
	        if (c == '\n') {
                     result.add(StemmString(sb.toString()));
	            sb = new StringBuffer();
	        } else {
	            sb.append(c);
	        }
	    }
	    if (sb.length() > 0) {
	        result.add(sb.toString());
	    }
	}catch (IOException ex) {
                System.out.println(ex);
            }       
	return result;
        }
        public static String StemmString(String str){   
             char[] charArray = new char[500];
            for(int a=0;a<str.length();a++){
                charArray=str.toCharArray();
            }
            Stemmer s = new Stemmer();
                    s.add(charArray);
                    s.stem();
                    return (s.toString());  
        }

    }
    
 

