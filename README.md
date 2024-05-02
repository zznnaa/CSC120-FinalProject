# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`

## DESIGN JUSTIFICATION
We wanted to create a turn-based battle game that would require the user to go to war, while talking with the characters and forces the user to empathize with the characters they put in danger.

We created The Imperial Crown, where the user is a student conscripted from the Imperial Academy of War to train a new Regiment. The game ends when 3 battles have been completed, and the user wins the game if they have won two out of those three battles.

There are three pieces to this game - battling, training, and setting up camp.

In battle, an enemy is automatically created for the user and the user can choose to attack or check a character's stats every turn. Each human can either kick or shoot as their attack option. The battle ends when either the enemy, or one character in the user's party is dead. Health is decreased for bith characters and the enemy which each attack.

In training, the user chooses two characters to train against each other to gain experience. On the back end, training ends when one character's health is less than or equal to 0, and the character's health is then replenished once the training is over (minus 2 health for the exertion it took to train). However the user only sees the character's stats displayed once training ends, which shows a gain in experience and slightly lowered health.

In campfire, the user chooses a character to talk to. They are guided through a dialogue tree, for which they can choose two responses for each character's line of speech. The Character class also keeps track of the user's position within that character's tree, so when the user returns for another conversation with the same character, they start at the same place they left off.

We started with six classes - Training and Campfire inherited from a Non-Battle class, while Battle, Game, and Human were all their own classes. We started out by inputting information from the game and character classes to run the battle class, and we realized battle, training, and campfire could actually be methods within the Game class instead of existing independently. To turn the battle class into a game method, we stored all thre characters and enemies needed for the game within the game classed and passed the enemy to be battled as an argument in the battle() method. To turn the campfire class into a game method, we realized we would need a seperate Character class that inherited from Human to hold the dialogue tree for each character. The dialogue tree would be fed a randomly chosen HashtablePair (a different class) from Game to initalize the network. To make the game loop work with these three newly created methods, we had to use a switch case to allow the user switch between different modes in the game (battle, train or campfire).


## CHIOMA REFLECTION:
 - What was your **overall approach** to tackling this project?
 The first few session were spent brainstorming on the project and how we (Zoe and I) wanted the game to run. Then, we met a couple times to pair program. After the first couple sessions, we drifted towards parts of the game that we felt more attached to and kept working on that. In the week of Demo Day, we began working on integrating the various changes we had made independently.
 - What **new thing(s)** did you learn / figure out in completing this project?
 I learned how to use switch cases and discovered a brute force approach to validate a user's input and to pause the execution of a program until the user inputs a valid option. I also discovered that I could initialize an ArrayList with values by using the Array asList() method. Besides the java syntax I learned, I saw myself get better at thinking around how to implement a complex idea as code in the simplest way possible, using the skills I already have.
 - Is there anything that you wish you had **implemented differently**?
 I wish we had implemented the map that drives the dialogue between the user and characters in a way that didn't make the constructor in our game class look so clunky. 
 - If you had **unlimited time**, what additional features would you implement?
 Here's a list of the additional features we would have added if we had more time: 
    - Allow chrs train against old enemies battled
    - Allow the user to end a training session when both characters are still alive
    - Add in audio while player is in battle mode (battle music) and campfire (crackling of a fire)
    - Allow player choose which character should return an enemy's attack in "real" battles
    - Outcome of game should be based on both alliance and number of battles won
    - Add graphics to make text print out more interesting
    - Figure out how to generate random numbers in a more efficient way
    - Allow user to quit game voluntarily
    - Allow user "reun-away" from a battle
    - Think about what methods and attributes should be public or private and make changes accordingly
    - Increase character's alliance based on how far down they get in the character's dialogue graph
    - Randomize the dialogue script between characters
    - Add more detailed background stories both for characters and enemies

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
About a week to Demo Day, Jordan advised that I migrate the battle class to a method within the game and make training a parameter in the battle class. I spent about an hour asking questions about how to go about that because I couldn't wrap my mind around how I would implement that in such a short time, and was honestly slightly upset that I would have to break a part of our code I thought was mostly donw. However, that turned out to be crucial to the eventual flow of the game and also helped me changed how I thought about implementing other parts of the game. Migrating battle to game also turned out to not be too bad. I finished it in two coding sessions and did much more during the last week working on the project. 

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 Don't be too fixated on getting a clean code from the start, and it's fine if you have to revise your code a thousand time. Just make sure that you keep making progress and keep making those lists at the beginning of each of your coding sessions. Really helpful to look back and to see how your project has morphed from session to session
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
Working with an initiator like Zoe was wonderful! I'm more of a strategist, and I love to see the end from the beginning. Sometimes, I get stuck in planning and replanning and do not get started on actual implementation. Having Zoe insist that we start even when most components of the game felt squishy was extremely advantageous to finishing the project in time. My attention to detail, continuous note taking and segmentation of our project ensured that we implemented all parts of the project methododically and didn't forget anything.

## ZOE REFLECTION:
 What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.