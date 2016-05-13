# tak
This is an implementation in Java of the abstract strategy game called Tak. Further information can be found at: http://cheapass.com/games/tak

Tak is a game similar to tic-tac-toe, checkers, backgammon or chess. 
It can be played in boards of sizes going from 3x3 to 8x8 and has a very simple set of rules.
Players alternate in turns to either place a new stone or move a stack of stones.
The game objective is to create a "road" connecting opposite sides of a board with pieces.
A road don't need to be a straight line. 

## Movement rules
**Place**

 * There are 3 types of stones that can be place: flat stones, standing stones and capstones.
 * A player can only place a new stone over an empty space.

**Move**

 * A stack of existing stones can be moved linearly in any direction.
 * A player can chose a stack and start dropping a subset of pieces from that stack onto other squares, that way moving pieces around.
 * A player has to drop at least one piece per square after the original stack square if it wants to continue dropping onto the next square.  
 * Flat stones can not surmount standing stones.
 * No stone can surmount a capstone.
 * Capstones, if dropped alone, can surmount standing stones turning them into flat stones. 
 * The maximum number of pieces moved per stack equals the board size.

## Additional info

** Milestones **

* 29/04/2016 - Tak project was created. 

* 08/05/2016 - Tak engine was finished. Tak PTN console interface was finished. 

** Future Work ** 

* Create a web service based on the engine
* Create a Web interface that uses the service
* Create a authentication/login service to register players 
* Add the capacity to host multiple games simultaneously
* Create an Android interface 