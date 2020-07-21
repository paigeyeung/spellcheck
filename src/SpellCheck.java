/*
Output:

No mistakes
    Type a word: letter
    No mistakes found

Adding letter to beginning
    Type a word: etter
    Not found in dictionary
    Suggestions: better, fetter, getter, letter, netter, petter, setter, wetter

Adding letter to end
    Type a word: lette
    Not found in dictionary
    Suggestions: letted, letter

Removing letter from beginning
    Type a word: aletter
    Not found in dictionary
    Suggestions: letter

Removing letter from end
    Type a word: lettera
    Not found in dictionary
    Suggestions: letter

Exchanging adjacent letters
    Type a word: lteter
    Not found in dictionary
    Suggestions: letter
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SpellCheck {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/pyeung/Downloads/words.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        LinearProbingHashST<String,Integer> st = new LinearProbingHashST<>();

        String str;
        for(int i = 0; (str = br.readLine()) != null; i++) {
            st.put(str, i);
        }

        System.out.print("Type a word: ");
        String word = in.readLine();
        if(st.contains(word))
            System.out.println("No mistakes found");
        else
        {
            System.out.println("Not found in dictionary");
            ArrayList<String> suggestions = new ArrayList<>();
            for(char i = 'a'; i <= 'z'; i++)
            {
                if(st.contains(i + word)) suggestions.add(i + word); // add letter to beginning
                if(st.contains(word + i)) suggestions.add(word + i); // add letter to end
            }

            // remove letter from beginning
            if(st.contains(word.substring(1))) suggestions.add(word.substring(1));

            // remove letter from end
            if(st.contains(word.substring(0,word.length()-1))) suggestions.add(word.substring(0,word.length()-1));

            // exchange adjacent letters
            for(int i = 0; i < word.length()-1; i++)
            {
                String s = word.substring(0,i) + word.charAt(i+1) + word.charAt(i) + word.substring(i+2);
                if(st.contains(s)) suggestions.add(s);
            }

            Collections.sort(suggestions);

            if(suggestions.size() != 0) {
                System.out.print("Suggestions: ");
                for (int i = 0; i < suggestions.size(); i++) {
                    System.out.print(suggestions.get(i));
                    if(i < suggestions.size() - 1)
                        System.out.print(", ");
                }
            }
        }
    }
}
