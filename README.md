# Card Games Simulator - War
This is a simple Java console game, which is based on a simplest card game ever to be known in Bulgaria - "War" ...and most probably 
everywhere else, but I am not aware of that, so let me live in my own small world, shall we? :D 

## What are the rules of this game?
The game is played with a standard 52 cards deck. The cards are split equally between the players (2, in the current version 
of this simulation). The cards must be placed faced down, so that their values (ranks, colors etc.) will remain unknown.

The game (and every round) begins with players simultaneously turn the top card of their pile face up. The player who turns
the card with the highest value wins both his card and the opponents card. The color of the card does not matter in this game.

After winning a round the player must place the newly acquired cards faced down at the bottom of their deck. This is how a
round ends, now the players must start a new round where they will continue the same process. 

However, there is a possibility that the 2 (or more) players may place cards on the board which may have the same rank 
(example - Jack and Jack) - this is called "War". In this case, both players should leave those cards and withdraw 
yet another 3 cards from their own decks. The player whose last withdrawn card has the highest rank wins all cards in the current 
round. If the last cards of both players are from the same rank, then the "War" process must start again. This means that yet
another 3 cards must be drawn. etc. etc.

If a player is in a process of "War" and they are out of cards, then they are forced to withdraw a random card (or 2,3 
depending on the situation) from the deck of their opponent(s). The upperly mentioned "War" rules are applied in this situation
too.

>The winner of the game is the player who manages to obtain all 52 cards. 

WARNING : This may result in long and everlasting games which may bore you to death, such as this explanation (P.S. thanks 
for reading! :D). 

##What is implemented in this small console game anyway?
Pretty much everything from the upperly mentioned rules, including the edge cases. The console game assumes that there are 2 
players. They are controlled by your machine, so you can just sit and watch the show (free of charge ^^)

##How do I run this?
Note: Upload JAR file

>1. Install java on your machine

>2. Download the ZIP of this project by pressing "Clone or download" and then "Download ZIP"

>3. Open terminal (or cmd)

>4. Locate the folder of the JAR file ../CardGameSimulatorWar/dist

>5. Enter the following command : "java -jar CardGamesSimulatorWar.jar" (without the quotes)


##Any game modes?
Yes, currently there are two modes "automatic" (which is the default mode) and "manual". If you are using "automatic" the 
console app will start every new round on its own with a 3000 milliseconds delay. The "manual" mode will let you start the
new round on your own by pressing the "ENTER" (or any) key.

Trigger automatic mode by entering the following command in the dist folder "java -jar CardGamesSimulatorWar.jar" (without 
the quotes) or "java -jar CardGamesSimulatorWar.jar automatic" (without the quotes)

Trigger manual mode by entering the following command in the dist folder "java -jar CardGamesSimulatorWar.jar manual" (without 
the quotes)

##Any bugs?
Meeh, I think it runs pretty smoothly, but feel free to open a ticket if you find something.

##Future development?
I am planning to upgrade the control via the psvm args[] eg. game speed (eg 1,2,3,4,5), game type (eg "Small war","Big war")

##But why would you build this?
Well, I should do what I like, right? After all, this gives me pleasure and also I am uploading it here, so that anyone can
use it, so please be happy about it ^^ Enjoy and have a nice day!

####Disclaimer
    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE 
    INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE 
    FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM 
    LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, 
    ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
