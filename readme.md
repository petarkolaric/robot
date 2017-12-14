## Robot Exercise

##### Problem

This is a toy robot program designed to solve the problem described [here](https://git.hq.local/projects/RECR/repos/coding-challenge/browse/coding-challenge-ioof.pdf)

##### Solution

The solution provided here uses a command pattern to determine what command the user is trying to run. There are distinct domain objects to represent the robot and the table, which are used by the commands. The commands run with the given robot and table, and update the robot state accordingly.

##### Decisions Made

This could have been solved without a command pattern, with hard coded statements for determining what to do for each command, and then running that particular command. I deemed this to be harder to understand, as it makes it harder to separate the responsibilities of each class.

In this solution I have just used a generic exception type, with the message of the exception saying what the problem is. I deemed this to be OK for this small application, but for a larger application I would likely create a new exception type that represents the actual problem occurring, or use an existing one that better suits the problem.

I also made a decision to take input from the command line rather than from a file. This may also work with code redirected from a file, however this has not been tested.

I also chose to make the robots position zero indexed, rather than 1 indexed. This is to make it slightly simpler, but could easily be changed.

##### Running the application

To run the application, type `./gradlew run`

##### Testing the application

To test the application, type `./gradlew test`
