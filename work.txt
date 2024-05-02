Work Meeting #1: 4/16/23
Accomplished: basic structure of game and classes
To-Do:
- Need to fix how battle ends. Character can't keep playing after health = 0
- Create human class and let enemy and character inherit from there.

FP Workshop #1: 4/18
Accomplished: figuring out structure of classes
To-Do:
- seperate battle and game class
- battle & non-battle classes

Zoe 4/20 1 hour:
split game and battle classes

Work Meeting #2 4/20 2 hours:
Chioma: 
    Updated Battle class. Character can no longer play after battle is lost
    Added attack() method within Human to allow human randomly choose attacks
    TODO: 
    - Work on Nonbattle class at your own discretion
Zoe:
    Researched dialogue trees
    TODO:
    - Figure out how dialogue trees work
    - Create the Training class
Next Meeting:
- Work on the Campfire class together

Zoe 4/21 3 hours:
- completed training class
- tried to install guava

Zoe 4/22 1.5 hours:
- installed guava
- started to create graph

Chioma 4/22 1 hour:
- Talked with Jordan about how best to seperate the classes to ensure that we have scanners only in the Game class
    TO-DO:
    - Implement the Battle class as a method in Game rather than a seperate class
    - Implement a boolean in the Battle class that determines if a battle session is training or not

Zoe 4/23 2.5 hours:
- creating and navigating through graph
- replacing strings in graph with hashtable keys/objects

Work Meeting #3 4/24 1 hour:
- creating revised architecture diagram

Zoe 4/24 3 hours:
- worked on fleshing out while loop for dialogue

Chioma 4/24 2.5 hours:
    - Started implementing battle as a method within game
    TO-DO:
    - Fix the battle as a class vs a method problem
    - Once this is done, make sure that Training as an instance of battle works
    - Figure out how to generate random numbers in a more efficient way

Chioma 4/25 45 minutes:
- Implemented battle as a method in game
    TO-DO:
    Implement Training as an instance of battle 
    How to implement a number of battles within game?
    Implement various characters
    Implement various enemies

Chioma 4/25 2 hours:
- Changed up battle to allow for various players and to let the user choose the name of their players.
NEXT:
- Implement a training loop within the static main method in game
- Create a description of the enemies to be battled within game 
- Implement the three "real" battles within game

Chioma 4/26 1.75 hours
- Started implementing a battle class that allows the game to run a real or training battle session

Chioma 4/28 2 hours
- Successfully implemented training as a battle case

Chioma 4/29 1.5 hours
- Researched about switch case in java
- Partially implemented a switch case to allow user switch between different modes of the game (campfire, battle, train)
- Created a map of our game

Chiom 4/29 1.75 hours
- Split up baattle and training and modified battle to take in enemy to be battled
- Implemented function to randomly sample enemy
- Updated addCharacter to randomly sample characters' stats

Chioma 4/30 1 hour:
- Fixed battle class to ensure that chractrs died once their health was negative or zero
- implemented a loop to keep asking user for a response when a valid response isn't inputted

Zoe 4/30 2 hours:
 - Integrated dialogue into main

Work Meeting #4 6 hours:
- Completed working game loop
- Created both scripts for dialogue
- Added canBattle, canTrain, canCampfire
- edited attack methods to work