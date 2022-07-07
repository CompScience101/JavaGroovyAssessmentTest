/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagroovyassessment;

import java.util.ArrayList;

/**
 *
 * @author Nicholas1
 */
public class JavaGroovyAssessment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Assume you got some data from a customer and your task is to design a routine that will calculate the average Product price per Group. */
        // TODO code application logic here
        // The Price of each Product is calculated as:
        // Cost * (1 + Margin)
        // 
        
        // contains information about [Product, Group, Cost]
        String [][]products = {
            {"A", "G1", "20.1"},
            {"B", "G2", "98.4"},
            {"C", "G1", "49.7"},
            {"D", "G3", "35.8"},
            {"E", "G3", "105.5"},
            {"F", "G1", "55.2"},
            {"G", "G1", "12.7"},
            {"H", "G3", "88.6"},
            {"I", "G1", "5.2"},
            {"J", "G2", "72.4"}
        };
         
        // [Category, Cost range from (inclusive), Cost range to (exclusive)]
        // i.e. if a Product has Cost between 0 and 25, it belongs to category C1
// ranges are mutually exclusive and the last range has a null as upper limit
         String [][] category = {
             {"C3", "50", "75"},
             {"C4", "75", "100"},
             {"C2", "25", "50"},
             {"C5", "100", null},
             {"C1", "0", "25"}
         };
           
         
         // contains information about margins for each product Category
         // [Category, Margin (either percentage or absolute value)]
         String[][] margins = {
             {"C1", "20%"},
             {"C2", "30%"},
             {"C3", "0.4"},
             {"C4", "50%"},
             {"C5", "0.6"}
        };
         
    int G1counter = 0;     
    for(int x=0; x<products.length; x++){//find G1 array length
        if(products[x][1].equalsIgnoreCase("G1")){
            G1counter++;
        }
    }
    //System.out.println("G1counter: "+G1counter);
    int G2counter = 0;
    for(int x=0; x<products.length; x++){//find G2 array length
        if(products[x][1].equalsIgnoreCase("G2")){
            G2counter++;
        }
    }     
    //System.out.println("G2counter: "+G2counter);
    int G3counter = 0;
    for(int x=0; x<products.length; x++){//find G3 array length
        if(products[x][1].equalsIgnoreCase("G3")){
            G3counter++;
        }
    }
    double G1total = 0;
    double cost = 0;
    double margin = 0;
    ArrayList<String[]> IndividualPriceList = new ArrayList<String[]>();
    for(int x=0; x<products.length; x++){
        //for(int y=0; y<category.length; y++){
            cost = Double.parseDouble(products[x][2]);
            if(products[x][1].equalsIgnoreCase("G1")){
                G1total = PriceGen(cost, MarginGen( cost , category, margins));
                IndividualPriceList.add(new String[2]);
                IndividualPriceList.get(x)[0] = "G1";//add group id
                IndividualPriceList.get(x)[1] = ""+G1total;//add price 
                //System.out.println("IndividualPriceList: "+IndividualPriceList.get(x)[0]);//add group id);
            }
            if(products[x][1].equalsIgnoreCase("G2")){
                G1total = PriceGen(cost, MarginGen( cost , category, margins));
                IndividualPriceList.add(new String[2]);
                IndividualPriceList.get(x)[0] = "G2";//add group id
                IndividualPriceList.get(x)[1] = ""+G1total;//add price 
            }
            if(products[x][1].equalsIgnoreCase("G3")){
                G1total = PriceGen(cost, MarginGen( cost , category, margins));
                IndividualPriceList.add(new String[2]);
                IndividualPriceList.get(x)[0] = "G3";//add group id
                IndividualPriceList.get(x)[1] = ""+G1total;//add price 
            }
        //}
    }
    //System.out.println("G3counter: "+G3counter);
    //String [][] result  = {
      //  {"G1", "37.5"},
      //  {"G2", "124.5"},
      //  {"G3", "116.1"}
    //};
    String [][] result = {{"G1", ""+AveragePriceGen("G1",IndividualPriceList, G1counter)},
                           {"G2", ""+AveragePriceGen("G2",IndividualPriceList, G2counter)},
                           {"G3", ""+AveragePriceGen("G3",IndividualPriceList, G3counter) }};
    System.out.println("result arr; ");
        for(int x=0; x<result.length; x++){
            System.out.println();
            for(int y=0; y<2; y++){
                System.out.print("["+result[x][y]+"]");
            }
        }
        if(result == null ){
            System.out.println("It doesn't work");
        }
        System.out.println(" It works!");

    }
    public static double PriceGen(double cost, double margin ){
        double Price = 0;
        Price = cost * ( 1+margin );
        return Price;
    }
    public static double MarginGen(double cost, String[][] category, String[][] margins){
        double margin=0;
        String categorySelection = "";
            if(cost >= 100  ){
                categorySelection = "C5";
                for(int y=0; y<margins.length; y++){
                    if(margins[y][0] == categorySelection){
                        margin = Double.parseDouble(margins[y][1].replace("%", "")) / 100;
                        System.out.println("margin: "+margin+" categorySelection "+categorySelection+", cost "+cost+", Integer.parseInt(category[x][1]) >= 100");//test
                    }
                }
            }
            else if(cost >= 75 && cost < 100  ){
                categorySelection = "C4";
                for(int y=0; y<margins.length; y++){
                    if(margins[y][0] == categorySelection){
                        margin = Double.parseDouble(margins[y][1].replace("%", "")) / 100;
                        System.out.println("margin: "+margin+" categorySelection "+categorySelection+", cost "+cost+", Integer.parseInt(category[x][1]) >= 75 && Integer.parseInt(category[x][2]) < 100");//test
                    }
                }
            }
            else if(cost >= 50 && cost < 75  ){
                categorySelection = "C3";
                for(int y=0; y<margins.length; y++){
                    if(margins[y][0] == categorySelection){
                        margin = Double.parseDouble(margins[y][1].replace("%", "")) / 100;
                        System.out.println("margin: "+margin+" categorySelection "+categorySelection+", cost "+cost+", ");//test
                    }
                }
            }
            
            else if(cost >= 25 && cost < 50  ){
                categorySelection = "C2";
                for(int y=0; y<margins.length; y++){
                    if(margins[y][0] == categorySelection){
                        margin = Double.parseDouble(margins[y][1].replace("%", "")) / 100;
                        System.out.println("margin: "+margin+" categorySelection "+categorySelection+", cost "+cost+", Integer.parseInt(category[x][1]) >= 25 && Integer.parseInt(category[x][2]) < 50");//test
                    }
                }
            }
            
            else if(cost >= 0 && cost < 25  ){
                categorySelection = "C1";
                for(int y=0; y<margins.length; y++){
                    if(margins[y][0] == categorySelection){
                        margin = Double.parseDouble(margins[y][1].replace("%", "")) / 100;
                        System.out.println("margin: "+margin+" categorySelection "+categorySelection+", cost "+cost+", Integer.parseInt(category[x][1]) >= 0 && Integer.parseInt(category[x][2]) < 25");//test
                    }
                }
            }

        return margin;
    }
    public static double AveragePriceGen(String GroupID, ArrayList<String[]> IndividualPriceList, int size){
        double total = 0;
        for(int x=0; x<IndividualPriceList.size(); x++){
            if(GroupID.equalsIgnoreCase("G1") && IndividualPriceList.get(x)[0].equalsIgnoreCase("G1")){
                total = total + Double.parseDouble(IndividualPriceList.get(x)[1]);
            }
            if(GroupID.equalsIgnoreCase("G2") && IndividualPriceList.get(x)[0].equalsIgnoreCase("G2")){
                total = total + Double.parseDouble(IndividualPriceList.get(x)[1]);
            }
            if(GroupID.equalsIgnoreCase("G3") && IndividualPriceList.get(x)[0].equalsIgnoreCase("G3")){
                total = total + Double.parseDouble(IndividualPriceList.get(x)[1]);
            }
        }
           if(GroupID.equalsIgnoreCase("G1")){
                return total / size;
            }
            if(GroupID.equalsIgnoreCase("G2")){
                return total / size;
            }
            if(GroupID.equalsIgnoreCase("G3")){
                return total / size;
            }
        return 0 ;
    }
   
    
}