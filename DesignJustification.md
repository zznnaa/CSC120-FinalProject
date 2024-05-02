We wanted to create a turn-based battle game that would require the user to go to war, while talking with the characters and forces the user to empathize with the characters they put in danger.

We created The Imperial Crown, where the user is a student conscripted from the Imperial Academy of War to train a new Regiment. The game ends when 3 battles have been completed, and the user wins the game if they have won two out of those three battles.

There are three pieces to this game - battling, training, and setting up camp.

In battle, an enemy is automatically created for the user and the user can choose to attack or check a character's stats every turn. Each human can either kick or shoot as their attack option. The battle ends when either the enemy, or one character in the user's party is dead. Health is decreased for the characters who don't die.

In training, the user chooses two characters to train against each other to gain experience. On the back end, training ends when one character's health is less than or equal to 0, and the character's health is then replenished once the training is over (minus 2 health for the exertion it took to train). However the user only sees the character's stats displayed once training ends, which shows a gain in experience and slightly lowered health.

In campfire, the user chooses a character to talk to. They are guided through a dialogue tree, for which they can choose two responses for each character's line of speech. The Character class also keeps track of the user's position within that character's tree, so when the user returns for another conversation with the same character, they start at the same place they left off.

