/*
    file: Poker.java
    date: 9/2017
    purpose:
    1. implement enumerations for both card ranks, suits and classifications
    2. enumeration types are accessed by Card.java and Hand.java
    3. include methods to check the command line input validity;
       parse the valid cards input;
       output the description of the hand;
       play the poker game (if there is more than one player);
       compare hands and determine the winner
*/

public class Poker {
    static final int CARDS_IN_ONE_HAND = 5;

    // Check if the number of command line arguments = 0,
    // or not a multiple of 5
    // if so, show error message and exit
    private void checkCards (String[] cards) {
        if (cards.length == 0 || cards.length%CARDS_IN_ONE_HAND != 0) {
            System.out.println("Error: wrong number of arguments; " +
                    "must be a multiple of 5");
            System.exit(0);
        }
    }

    // parse the command line inputs, construct cards and hands using 5 cards
    // output the corresponding description
    // of the hand for each player along the way
    // return the hands objects if there are more than 1 player
    private Hand[] parseHands (String[] args) {
        int player = args.length/CARDS_IN_ONE_HAND; // how many player?
        Hand[] hands = new Hand[player];

        // if only one player,
        // show the hand description and exit
        if (player == 1) {
            Card[] hand = new Card[CARDS_IN_ONE_HAND];
            for (int i=0; i<CARDS_IN_ONE_HAND; ++i) {
                hand[i] = new Card(args[i]);
            }
            hands[0] = new Hand(hand);
            System.out.println("Player 1: " + hands[0].getDescription());
            System.exit(0);
        }

        // if multiple players,
        // show hand description for each and return the hands for playing
        for (int i = 0; i < player; ++i) {
            Card[] hand = new Card[CARDS_IN_ONE_HAND];
            int counter = 0;
            // construct hand by parsing the 5 cards for each player
            for (int j=CARDS_IN_ONE_HAND*i; j<CARDS_IN_ONE_HAND*(i+1); ++j) {
                hand[counter] = new Card(args[j]);
                counter++;
            }
            hands[i] = new Hand(hand);
            // print hand description when finish parsing a hand
            System.out.println("Player " + (i+1)
                    + ": " + hands[i].getDescription());
        }
        return hands;
    }

    // play the poker game, compare hands, and output the winner
    private void play (Hand[] hands) {
        Hand[] winner = new Hand[hands.length];
        int counter = 1; // to count number of winners, initialize to 1

        // store the winners' index, default to player 1
        int[] winnerIndex = new int[hands.length];

        // the initial winner default to player 1
        winnerIndex[0] = 0;
        winner[0] = hands[0];

        for (int i=1; i < hands.length; ++i) {
            // only need to compare with the first one in the winners array
            // even we have multiple tied winners
            // if one of them lost to the new winner, all of them lost
            if (winner[0].compareTo(hands[i]) < 0) {
                // if current winner lost, reset everything
                winner = new Hand[hands.length];
                counter = 1;
                winnerIndex = new int[hands.length];
                // and default to new winner
                winnerIndex[0] = i;
                winner[0] = hands[i];
            } else if (winner[0].compareTo(hands[i]) == 0) {
                // we got a tie, do not reset, just append the tied winner
                counter++; // got one more winner
                winnerIndex[counter-1] = i;
                winner[counter-1] = hands[i];
            }
        }
        // output winner message based on number of winners left (win or draw)
        if (counter == 1) {
            System.out.println("Player " + (winnerIndex[0] + 1) + " wins.");
        } else {
            System.out.print("Players");
            for (int i=0; i<counter-2; ++i) {
                System.out.print(" " + (winnerIndex[i] + 1) + ",");
            }
            System.out.print(" " + (winnerIndex[counter-2] + 1)
                    + " and " +  (winnerIndex[counter-1] + 1) + " draw.\n");
        }
    }

    // putting everything together
    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.checkCards(args);
        Hand[] hands = poker.parseHands(args);
        poker.play(hands);
    }
}