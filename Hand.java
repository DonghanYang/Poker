/*
    file: Hand.java
    date:   9/2017
    purpose:
    1. implement a class represents a hand (contains 5 cards)
    2. each hand has a classification, a description,
       and an extraInfo array to store information
       for comparison if two hands tie
    3. getter methods to prevent privacy leak
*/

import java.util.Arrays;
import java.util.Collections;

class Hand {
    
    private Rank[] extraInfo = new Rank[Poker.CARDS_IN_ONE_HAND];
    private Classification classifications;
    private String description;

    // construct the hand based on its classifications
    // in order of most valuable to least valuable
    Hand (Card[] hand) {
        if (Flush(hand) && Straight(hand)) {
            this.extraInfo = maxRank(hand);
            this.classifications = Classification.STRAIGHTFLUSH;
            this.description = this.extraInfo[0].rank + "-high straight flush";
        } else if (Four_of_a_kind(hand)) {
            this.description = "Four " + this.extraInfo[0].rank + "s";
            this.classifications = Classification.FOUR_OF_A_KIND;
        } else if (FullHouse(hand)) {
            this.description = this.extraInfo[0].rank + "s full of "
                             + this.extraInfo[1].rank + "s";
            this.classifications = Classification.FULLHOUSE;
        } else if (Flush(hand)) {
            this.extraInfo = ReverseSortCard(hand);
            this.classifications = Classification.FLUSH;
            this.description = this.extraInfo[0].rank + "-high flush";
        } else if (Straight(hand)) {
            this.extraInfo = maxRank(hand);
            this.classifications = Classification.STRAIGHT;
            this.description = this.extraInfo[0].rank + "-high straight";
        } else if (Three_of_a_kind(hand)) {
            this.classifications = Classification.THREE_OF_A_KIND;
            this.description = "Three " + this.extraInfo[0].rank + "s";
        } else if (TwoPair(hand)) {
            this.classifications = Classification.TWOPAIR;
            this.description = this.extraInfo[0].rank + "s over "
                             + this.extraInfo[1].rank + "s";
        } else if (OnePair(hand)) {
            this.classifications = Classification.ONEPAIR;
            this.description = "Pair of " + this.extraInfo[0].rank + "s";
        } else {
            this.extraInfo = ReverseSortCard(hand);
            this.classifications = Classification.HIGHCARD;
            this.description = this.extraInfo[0].rank + "-high";
        }
    }

    // a method that sorts cards based on its rank in ascending order,
    // and then return the card ranks
    private Rank[] SortCard (Card[] hand) {
        Rank[] ranks = new Rank[Poker.CARDS_IN_ONE_HAND];
        for (int i=0;i<Poker.CARDS_IN_ONE_HAND;++i){
            ranks[i] = hand[i].getRank();
        }
        Arrays.sort(ranks);
        return ranks;
    }

    // sort cards based on its rank in descending order
    // and return the card ranks
    // In worst case of Flush and High card,
    // reverse sorted card ranks are needed,
    // to perform peer-wise comparison to break the tie
    private Rank[] ReverseSortCard (Card[] hand) {
        Rank[] ranks = new Rank[Poker.CARDS_IN_ONE_HAND];
        for (int i=0;i<Poker.CARDS_IN_ONE_HAND;++i){
            ranks[i] = hand[i].getRank();
        }
        Arrays.sort(ranks, Collections.reverseOrder());
        return ranks;
    }

    // return the maximum rank given a hand of cards
    private Rank[] maxRank (Card[] hand) {
        Rank[] ranks = new Rank[Poker.CARDS_IN_ONE_HAND];
        ranks[0] = SortCard(hand)[Poker.CARDS_IN_ONE_HAND-1];
        return ranks;
    }

    // return true if the hand is a Flush
    private boolean Flush (Card[] hand) {
        Suit[] suits = new Suit[Poker.CARDS_IN_ONE_HAND];
        for (int i = 0; i < Poker.CARDS_IN_ONE_HAND; ++i) {
            suits[i] = hand[i].getSuit();
        }
        return (suits[0].equals(suits[1])
                && suits[1].equals(suits[2])
                && suits[2].equals(suits[3])
                && suits[3].equals(suits[4]));
    }

    // return true if the hand is a Straight
    private boolean Straight (Card[] hand) {
        Rank[] ranks = SortCard(hand);
        return (ranks[0].compareTo(ranks[1]) == -1
                && ranks[1].compareTo(ranks[2]) == -1
                && ranks[2].compareTo(ranks[3]) == -1
                && ranks[3].compareTo(ranks[4]) == -1);
    }

    // return true if the hand is Four of a kind,
    // the extraInfo will be assigned with the rank of four cards,
    // and then the rank of the fifth card
    private boolean Four_of_a_kind (Card[] hand) {
        Rank[] ranks = SortCard(hand);
        if (ranks[0].equals(ranks[1]) && ranks[1].equals(ranks[2])
                && ranks[2].equals(ranks[3]) && !ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[0]; // rank of 4
            this.extraInfo[1] = ranks[4]; // rank of the fifth
            return true;
        } else if (!ranks[0].equals(ranks[1]) && ranks[1].equals(ranks[2])
                && ranks[2].equals(ranks[3]) && ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[4];
            this.extraInfo[1] = ranks[0];
            return true;
        }
        return false;
    }

    // return true if the hand is Full house
    // the extraInfo is assigned with the rank of triple,
    // and then the rank of the pair
    private boolean FullHouse (Card[] hand) {
        Rank[] ranks = SortCard(hand);
        if (ranks[0].equals(ranks[1]) && ranks[1].equals(ranks[2])
                && !ranks[2].equals(ranks[3]) && ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[0]; // rank of the triple
            this.extraInfo[1] = ranks[4]; // rank of the pair
            return true;
        } else if (ranks[0].equals(ranks[1]) && !ranks[1].equals(ranks[2])
                && ranks[2].equals(ranks[3]) && ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[4];
            this.extraInfo[1] = ranks[0];
            return true;
        }
        return false;
    }

    // return true if the hand is Three of a kind
    // the extraInfo is assigned with the rank of the triple and
    // then the ranks of the other two cards in descending order
    private boolean Three_of_a_kind (Card[] hand) {
        Rank[] ranks = SortCard(hand);
        if (ranks[0].equals(ranks[1]) && ranks[1].equals(ranks[2])
                && !ranks[2].equals(ranks[3]) && !ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[0]; // rank of the triple
            this.extraInfo[1] = ranks[4]; // rank of the higher ranking other card
            this.extraInfo[2] = ranks[3]; // rank of the lower ranking other card
            return true;
        } else if (!ranks[0].equals(ranks[1]) && !ranks[1].equals(ranks[2])
                  && ranks[2].equals(ranks[3]) && ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[4];
            this.extraInfo[1] = ranks[1];
            this.extraInfo[2] = ranks[0];
            return true;
        } else if (!ranks[0].equals(ranks[1]) && ranks[1].equals(ranks[2])
                   && ranks[2].equals(ranks[3]) && !ranks[3].equals(ranks[4])
                   && !ranks[0].equals(ranks[4])) {
            this.extraInfo[0] = ranks[1];
            this.extraInfo[1] = ranks[4];
            this.extraInfo[2] = ranks[0];
            return true;
        }
        return false;
    }

    // return true if the hand is Two pair
    // the extraInfo contains the ranks of the 2 pairs in descending order
    // and then the rank of the other card
    private boolean TwoPair (Card[] hand) {
        Rank[] ranks = SortCard(hand);
        if (ranks[0].equals(ranks[1]) && !ranks[1].equals(ranks[2])
                && ranks[2].equals(ranks[3]) && !ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[2]; // rank of the higher ranking pair
            this.extraInfo[1] = ranks[0]; // rank of the lower ranking pair
            this.extraInfo[2] = ranks[4]; // rank of the other card
            return true;
        } else if (ranks[0].equals(ranks[1]) && !ranks[1].equals(ranks[2])
                && !ranks[2].equals(ranks[3]) && ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[4];
            this.extraInfo[1] = ranks[0];
            this.extraInfo[2] = ranks[2];
            return true;
        } else if (!ranks[0].equals(ranks[1]) && ranks[1].equals(ranks[2])
                && !ranks[2].equals(ranks[3]) && ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[4];
            this.extraInfo[1] = ranks[1];
            this.extraInfo[2] = ranks[0];
            return true;
        }
        return false;
    }

    // return true if the hand is One pair
    // the extraInfo contains the rank of the pair
    // and then the ranks of the other cards in descending order
    private boolean OnePair (Card[] hand) {
        Rank[] ranks = SortCard(hand);
        if (ranks[0].equals(ranks[1])) {
            this.extraInfo[0] = ranks[0]; // rank of the pair
            // the ranks of other cards, from high to low
            this.extraInfo[1] = ranks[4];
            this.extraInfo[2] = ranks[3];
            this.extraInfo[3] = ranks[2];
            return true;
        } else if (ranks[1].equals(ranks[2])) {
            this.extraInfo[0] = ranks[1];
            this.extraInfo[1] = ranks[4];
            this.extraInfo[2] = ranks[3];
            this.extraInfo[3] = ranks[0];
            return true;
        } else if (ranks[2].equals(ranks[3])) {
            this.extraInfo[0] = ranks[2];
            this.extraInfo[1] = ranks[4];
            this.extraInfo[2] = ranks[1];
            this.extraInfo[3] = ranks[0];
            return true;
        } else if (ranks[3].equals(ranks[4])) {
            this.extraInfo[0] = ranks[4];
            this.extraInfo[1] = ranks[2];
            this.extraInfo[2] = ranks[1];
            this.extraInfo[3] = ranks[0];
            return true;
        }
        return false;
    }

    // a method to compare 2 hands using their extraInfo array
    // Order: from the left to the right
    // Why: we assigned the array in the order of the most significant (left),
    // to the least significant (right)
    // How: peer-wise comparison,
    // if both slots have the same rank,
    // then continue until all the slots are compared or we have a winner
    // the method returns a negative integer, zero, or a positive integer,
    // if hand1/player1 loses, tie or wins
    private int compareExtraInfo (Rank[] extraInfo1, Rank[] extraInfo2) {
        for (int i=0; i < extraInfo1.length; ++i) {
            if (extraInfo1[i] == null && extraInfo2[i] == null) {
                return 0;
            }
            if (extraInfo1[i].compareTo(extraInfo2[i]) != 0) {
                return extraInfo1[i].compareTo(extraInfo2[i]);
            }
        }
        return 0;
    }

    // compareTo method for the Hand, make use of above compareExtraInfo method
    // first compare classifications, if no tie then return result,
    // if tie, then compare extraInfo to break the tie
    // the method returns a negative integer, zero, or a positive integer,
    // if current hand loses, tie or wins
    int compareTo (Hand hand) {
        if (!this.classifications.equals(hand.classifications)) {
            return this.classifications.compareTo(hand.classifications);
        } else {
            return compareExtraInfo(this.extraInfo, hand.extraInfo);
        }
    }
    // getter method
    String getDescription() { return description; }
}