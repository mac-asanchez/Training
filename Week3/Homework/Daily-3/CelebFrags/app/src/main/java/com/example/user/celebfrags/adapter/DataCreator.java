package com.example.user.celebfrags.adapter;

import com.example.user.celebfrags.R;
import com.example.user.celebfrags.model.Celebrity;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {
    public static List<Celebrity> getCelebrities() {
        List<Celebrity> celebrities = new ArrayList<>();

        celebrities.add(new Celebrity(1, "Adam Levine", R.drawable.adam_levine, "Adam Noah Levine (born March 18, 1979) is an American singer and songwriter. He is the lead singer for the pop rock band Maroon 5."));
        celebrities.add(new Celebrity(2, "Adele", R.drawable.adele, "Adele Laurie Blue Adkins MBE (/əˈdɛl/; born 5 May 1988) is an English singer and songwriter. After graduating from the BRIT School for Performing Arts and Technology in 2006."));
        celebrities.add(new Celebrity(3, "Anne Hathaway", R.drawable.anne_hathaway, "Anne Jacqueline Hathaway (born November 12, 1982) is an American actress and singer. One of the world's highest-paid actresses in 2015, she has received multiple awards, including an Academy Award, a Golden Globe, a British Academy Film Award, and an Emmy."));
        celebrities.add(new Celebrity(4, "Daniel Radcliffe", R.drawable.daniel_radcliffe, "Daniel Jacob Radcliffe (born 23 July 1989) is an English actor and producer best known for his role as Harry Potter in the film series of the same name. He made his acting debut at 10 years of age in BBC One's 1999 television film David Copperfield, followed by his cinematic debut in 2001's The Tailor of Panama."));
        celebrities.add(new Celebrity(5, "Dua Lipa", R.drawable.dua_lipa, "Dua Lipa (born 22 August 1995) is an English singer, songwriter, and model. Her musical career began at age 14, when she began covering songs by other artists on YouTube. In 2015, she signed with Warner Music Group and released her first single soon after. "));
        celebrities.add(new Celebrity(6, "Eminem", R.drawable.eminem, "Marshall Bruce Mathers III (born October 17, 1972), known professionally as Eminem (often stylized as EMINƎM), is an American rapper, songwriter, record producer, record executive, and actor. "));
        celebrities.add(new Celebrity(7, "Keanu Reeves", R.drawable.keanu_reeves, "Keanu Charles Reeves (born September 2, 1964) is a Canadian actor, director, producer, and musician. He gained fame for his starring role performances in several blockbuster films, including comedies from the Bill and Ted franchise (1989–1991); action thrillers Point Break (1991), Speed (1994), and the John Wick franchise;"));
        celebrities.add(new Celebrity(8, "Robert Downey Jr.", R.drawable.robert_downey_jr, "Robert John Downey Jr. (born April 4, 1965) is an American actor and singer. His career has included critical and popular success in his youth, followed by a period of substance abuse and legal troubles, and a resurgence of commercial success in middle age."));
        celebrities.add(new Celebrity(9, "Ryan Reynolds", R.drawable.ryan_reynolds, "Ryan Rodney Reynolds (born October 23, 1976) is a Canadian actor and film producer. Ryan began his career starring in the YTV Canadian teen soap opera Hillside and in minor roles before landing the role of Michael Bergen on the ABC sitcom Two Guys and a Girl from 1998-2001."));

        return celebrities;
    }
}
