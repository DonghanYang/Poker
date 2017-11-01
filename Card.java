/*
    1. implement a class represents a card
    2. the constructor takes command-line arguments
       and construct the cards accordingly
    3. getter methods to pass message
*/

class Card {
    private Rank rank;
    private Suit suit;

    Card (String card) {
        // If any of the argument is not a valid card name,
        // show error message and exit
        if (card.length() != 2) {
            System.out.println("Error: invalid card name " + "'" + card + "'");
            System.exit(0);
        }

        // set the rank,
        // handle both upper and lower case characters (or a mixture)
        switch (card.substring(0, 1)) {
            case "2":
                this.rank = Rank.TWO;
                break;
            case "3":
                this.rank = Rank.THREE;
                break;
            case "4":
                this.rank = Rank.FOUR;
                break;
            case "5":
                this.rank = Rank.FIVE;
                break;
            case "6":
                this.rank = Rank.SIX;
                break;
            case "7":
                this.rank = Rank.SEVEN;
                break;
            case "8":
                this.rank = Rank.EIGHT;
                break;
            case "9":
                this.rank = Rank.NINE;
                break;
            case "T":
            case "t":
                this.rank = Rank.TEN;
                break;
            case "J":
            case "j":
                this.rank = Rank.JACK;
                break;
            case "Q":
            case "q":
                this.rank = Rank.QUEEN;
                break;
            case "K":
            case "k":
                this.rank = Rank.KING;
                break;
            case "A":
            case "a":
                this.rank = Rank.ACE;
                break;
            default:
                System.out.println("Error: invalid card name "
                                    + "'" + card + "'");
                System.exit(0);
        }

        // set the suit,
        // handle both upper and lower case characters (or a mixture)
        switch (card.substring(1)) {
            case "C":
            case "c":
                this.suit = Suit.CLUBS;
                break;
            case "D":
            case "d":
                this.suit = Suit.DIAMONDS;
                break;
            case "H":
            case "h":
                this.suit = Suit.HEARTS;
                break;
            case "S":
            case "s":
                this.suit = Suit.SPADES;
                break;
            default:
                System.out.println("Error: invalid card name "
                                    + "'" + card + "'");
                System.exit(0);
        }
    }
    // getter method
    Rank getRank() { return rank; }
    Suit getSuit() { return suit; }
}