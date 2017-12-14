# Poker
- [Here](https://en.wikipedia.org/wiki/List_of_poker_hands) is a complete guide of the poker hands descriptions and rankings
- Ensure all files are in the same folder and compile using `javac Poker.java`
- Run the program using `java Poker` and input your cards
- Cards should be entered on the command line as two-character strings, the first being an `A` for `Ace`, `K` for `King`, `Q` for `Queen`, `J` for `Jack`, `T` for `Ten`, or digit between 2 and 9 for ranks 2-9. The second character should be a `C` for `Clubs`, `D` for `Diamonds`, `H` for `Hearts`, or `S` for `Spades`. Both upper and lower case characters are supported.
- `Aces` are the highest rank. `Ace 2 3 4 5` is not considered as a straight (or straight flush)

For example,  

This input:    
`java Poker 2H TH AS AD TC`    
Should produce this output:    
`Player 1: Aces over 10s`    

This input:  
`java Poker 2H TH 1S 1D TC`  
Should produce this output:  
`Error: invalid card name '1S'`    

This input:  
`java Poker KS 9S QS AS JS 3D 7C 3S 3H 7S`  
Should produce this output:    
`Player 1: Ace-high flush`        
`Player 2: 3s full of 7s`        
`Player 2 wins.`        

This input:    
`java Poker qc jc 2h 7s 9h qd jd 2s 7c 9s 9c 7d 2c jh qh 9d 7h 2d js qs`  
Should produce this output:  
`Player 1: Queen-high`    
`Player 2: Queen-high`      
`Player 3: Queen-high`    
`Player 4: Queen-high`    
`Players 1, 2, 3 and 4 draw.`      

# Credits
- The project is from the course [Programming and Software Development (COMP90041)](https://handbook.unimelb.edu.au/2018/subjects/comp90041)
- Thanks to [Dr.Peter Schachte](http://people.eng.unimelb.edu.au/schachte/) and the tutors for the effort of teaching the subject and preparing the specification for this project 
