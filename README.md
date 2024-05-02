# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

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
 The first few session were spent brainstorming on the project and how we wanted the game to run
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.


## ZOE REFLECTION: