package javachatbot;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;


/**
 *
 * @author Spenser Franklin
 */
public class JavaChatbot extends JFrame implements KeyListener{
    JPanel panel = new JPanel();
    JTextArea dialog = new JTextArea(20,55);
    JTextArea input = new JTextArea(1,55);
    JScrollPane scroll = new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    
    String[][] chatBot={
        //Standard greetings. First is accepted input second is possible responses
        {"hi","hello","howdy"},
        {"Hi","Hello","Hey"},
        //Question greetings
        {"how are you", "how are you today"},
        {"I am good, thank you", "I am doing well"},
        //List of books
        {"what are some good books", "what is your favorite book", "what do you like to read", "tell me about books"},
        {"The Piano Tuner by Daniel Mason, is an interesting novel", "Writting does not get much better than The Kite Runner by Khaled Hosseini",
        "One of my favorite series is the John Keller series by Lawrence Block", "It is hard to go wrong with a good classic such as Great Expectations by Charles Dickens"},
        //Interesting facts
        {"tell me something new", "give me a interesting fact", "tell me a fact", "give me an interesting fact", "give me a fact", "tell me an interesting fact", "tell me a interesting fact"},
        {"There are actually more public libraries in the US than McDonald's", "In 1981, a black lab named Bosco was elected honorary mayor of Sunol, California",
        "Canadians say \"sorry\" so much that The Apology Act was passed in 2009,\n declaring that an apology can't be used as evidence of admission of guilt",
        "Sea otters have a pouch under their forearm to store their favorite rocks"},
        //Jokes
        {"tell me a joke"},
        {"Did you hear about the actor who fell through the floorboards?\n He was just going through a stage",
            "I saw a guy spill all his Scrabble letters on the road.\n I asked him, \"What's the word on the street?\"", 
        "Rick Astley will let you borrow any movie from his Pixar collection, except one.\n He's never gonna give you Up",
        "They all laughed when I said I wanted to be a comedian. Well, they're not laughing now"},
        //What the bot can say
        {"what can you do"},
        {"I can respond to simple greetings, tell you about some of my favorite books,\n I know a few interesting facts, and I will tell you some mediocre jokes."
            + " I will respond to these by asking\n what are some good books, tell me something new, and tell me a joke."},
        //Default
        {"Sorry I did not understand please ask, what can you do, to see what I can talk about"},
    };

    public static void main(String[] args) {
        new JavaChatbot();
    }
    
    public JavaChatbot(){
        super("Chatbot");
        setSize (650,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        dialog.setEditable(false);
        input.addKeyListener(this);
        
        panel.add(scroll);
        panel.add(input);
        panel.setBackground(new Color(0,128,0));
        add(panel);
        
        setVisible(true);
    }
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            input.setEditable(false);
            //get user input
            String quote = input.getText();
            input.setText("");
            addText("-->You:\t"+ quote);
            //Remove punctuation from input
            quote=quote.trim();
            while(quote.charAt(quote.length() -1) == '!' ||
                  quote.charAt(quote.length() -1) == '.' ||
                  quote.charAt(quote.length() -1) == '?')
            {
                quote=quote.substring(0,quote.length() -1);
            }
            quote=quote.trim();
            byte response = 0;
            /*
            0: Searching through chatBot array to find matches
            1: No matches were found
            2: A match was found
            */
            //Checking for matches
            int j = 0; //Sets what input group is checked
            while(response == 0){
            if(inArray(quote.toLowerCase(), chatBot[j*2])){
                response = 2;
                int r = (int)Math.floor(Math.random() * chatBot[(j*2)+1].length);
                addText("\n-->Chatbot:\t" + chatBot[(j*2)+1][r]);
            }
            j++;
            if(j*2 == chatBot.length - 1 && response == 0){
                response = 1;
            }
          }
          //Default
          if(response == 1){
             int r = (int)Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
                addText("\n-->Chatbot\t" + chatBot[chatBot.length - 1][r]); 
          }
          addText("\n");
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            input.setEditable(true);
        }
    }
    public void keyTyped(KeyEvent e){}
    
    public void addText(String str){
        dialog.setText(dialog.getText() + str);
    }
    
    public boolean inArray(String in, String[] str){
        boolean match = false;
        for(int i = 0; i < str.length; i++){
            if(str[i].equals(in)){
                match = true;
            }
        }
        return match;
    }
}
