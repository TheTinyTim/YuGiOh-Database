package com.ingram.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FetchCardNames {
    
    /*
     * This class will fetch all the current card names from the Yu-Gi-Oh! wiki and store them in a list
     * that other classes will access.
     */
    
    static String wikiCardPage = "http://yugioh.wikia.com/wiki/Category:TCG_cards";
    static String wikiPagePhp = "?page=";
    
    //This method will go through all of the pages of the wiki page and grab every card listed
    public static void fetchCardNames ()
    {
        //Get the total amount of pages for the amount of cards
        int totalPages = getTotalPages ();
        //Now Lets go through all the pages as long as there isn't 0 pages
        if (totalPages != 0)
        {
            //Lets initialize a list that will hold all the card names in the game
            ArrayList<String> cardNames = new ArrayList<> ();
            //Loop based on the amount of pages
            for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
                //We must encapsulate the jsoup connect in a IOException try/catch
                try {
                    //First load the web page with the current page number
                    Document doc = Jsoup.connect (wikiCardPage + wikiPagePhp + pageNum).get ();
                    //Now grab the div the card table list is located in
                    Element div = doc.select ("div#mw-pages").first ().select ("div.mw-content-ltr").first ();
                    //Now grab the table all the cards are located in
                    Elements unorderedLists = div.getElementsByTag ("ul");
                    
                    //Now we must loop through all the unordered lists and get all the hyperlinks in it
                    for (Element list : unorderedLists) {
                        //Now grab all the elements with an a (hyperlink) tag in the table which will be all the card names
                        Elements cardLinks = list.getElementsByTag ("a");
    
                        //Now that we have all the links in the current page we must loop through every card to store the name
                        for (Element cardLink : cardLinks) {
                            //Let's get the card name by splitting the text by : and getting the second array item
                            cardNames.add (cardLink.text ());
                        }
                    }
                } catch (IOException e) { e.printStackTrace (); }
            }
        }
    }
    
    //This method will return the total amount of pages there will be on the wiki page based on how many
    //cards there are and how many cards are shown per page (200)
    public static int getTotalPages ()
    {
        try {
            
            //Load the html file from the wiki card page
            Document doc = Jsoup.connect (wikiCardPage).get ();
            
            //Now lets try and grab the paragraph tag that holds the amount of cards
            Element div = doc.select ("div#mw-pages").first ();
        
            //Now cut up the string of the paragraph to grab the total amount of cards
            String totalStr = div.getElementsByTag ("p").first ().text ().split ("of") [1].split (" ") [1];
            //Now remove the comma from the total number so we can parse it
            totalStr = totalStr.replaceAll (",", "");
            //Now take the total string and parse it into a double then divide it by the amount of cards per page and ceil it
            return (int)Math.ceil (Double.parseDouble (totalStr) / 200d);
            
        } catch (IOException e) { e.printStackTrace (); return 0;}
    }
}
