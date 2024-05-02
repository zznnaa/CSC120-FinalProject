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
    - Allow user "run-away" from a battle
    - Think about what methods and attributes should be public or private and make changes accordingly
    - Increase character's alliance based on how far down they get in the character's dialogue graph
    - Randomize the dialogue script between characters
    - Add more detailed background stories both for characters and enemies

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
About a week to Demo Day, Jordan advised that I migrate the battle class to a method within the game and make training a parameter in the battle class. I spent about an hour asking questions about how to go about that because I couldn't wrap my mind around how I would implement that in such a short time, and was honestly slightly upset that I would have to break a part of our code I thought was mostly done. However, that turned out to be crucial to the eventual flow of the game and also helped me changed how I thought about implementing other parts of the game. Migrating battle to game also turned out to not be too bad. I finished it in two coding sessions and did much more during the last week working on the project. 

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 Don't be too fixated on getting a clean code from the start, and it's fine if you have to revise your code a thousand times. Just make sure that you keep making progress and keep making those lists at the beginning of each of your coding sessions. Really helpful to look back and to see how your project has morphed from session to session
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
Working with an initiator like Zoe was wonderful! I'm more of a strategist, and I love to see the end from the beginning. Sometimes, I get stuck in planning and replanning and do not get started on actual implementation. Having Zoe insist that we start even when most components of the game felt squishy was extremely advantageous to finishing the project in time. My attention to detail, continuous note taking and segmentation of our project ensured that we implemented all parts of the project methododically and didn't forget anything.

## ZOE REFLECTION:
 - What was your **overall approach** to tackling this project?
    Overall, our approach was to split tasks into parts that each team member could do seperately. My job was to learn guava and then create the campfire class/method. Then I tried to split my task into manageable pieces such as build an immutable network using strings -> add method to move to a new location in the network -> build a network using strings that can access hashtables -> add network to Character class -> add hashtables to Game class -> write scripts.

 - What **new thing(s)** did you learn / figure out in completing this project?
    I learned how to install guava, create immutable networks - including how to traverse through networks, how to store your location from a network, how to move to a different location in a network, and how to use network to access hashtables.
    
 - Is there anything that you wish you had **implemented differently**?
    If I had more time, I would love to add a character selection method and add seperate conditional method for campfire() before allowing it to go through (like Chioma's canBattle method). The way I currently have it means a "not valid input" message is printed if the user input doesn't match any sucessive nodes, which could happen because of an invalid input or because there are no more dialogue options, and although I got around that by checking whether there were additional dialogue options at the beginning of the while loop, it made it very convoluted and I would love to fix it if I had more time.

 - If you had **unlimited time**, what additional features would you implement?
    An additional feature I would want to implement if I had more time would be creating a dialogue script that works with backtracking so Option 5 - see Fig. 1 - could cross over to the thread on the right side and end up at Option 3. This would allow a more interesting alliance stat because I would store the initial "level" of the network the user starts out on, compare that to the final level they end up on, and increase the character's alliance based on if the user got to a deeper dialogue level with that character, not just on how many options they can get through.

    Fig. 1
     beginning         Level One
       /    \
      2   __ 3         Level Two
     / \ /  / \
     4 5    6 7        Level Three
    || |   || ||

    I would also like to create a variable network that can create the amount of edges needed based on the options avaliable in the hashtable, so that way the format of the script doesn't have to be the same for every character in order to work.

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
    Although I didn't have time to implement it in my code, the most helpful piece of advice / useful bit of coding information I got was from Tillie who used .contains() instead of .equals() in her project which allowed for a level of flexibility with typos that most other games did not have.

    Ex. userInput = "set up a campfire"
        userInput !== "campfire"
        code fails

        userInput = "set up a campfire"
        userInput.contains("campfire")
        code succeeds with variously accurate inputs

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
    I would advise my past self to start battle, training, and campfire as methods instead of independent classes! But I also think we had to go through the struggle of transferring that to learn why it worked, so I don't regret having made that "mistake"

 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
    I loved working with Chioma! Chioma's attention to detail really forced me to be specific about what I wanted to implement and why, which greatly increased my understanding of the code I was working with, and allowed us to get assistance early on for the areas we needed help with. She also kept track of every task, class, and method we needed to complete, which made the project feel manageable and was very useful as I'm a coder who tends to .

    Fun fact: We worked on this project for a combined total of __ hours.