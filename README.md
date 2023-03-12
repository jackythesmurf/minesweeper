# Minesweeper in the CLI

### Introduction
This is a minesweeper application in the commandline written in java. 

To run the project, firstly clone the project, then `cd` into `src`, compile and execute `Main.java`.

#### Features of the Game/ MVP
* A full implemention of Minesweeper in java console.
* Fully tested with junit, and with high coverage.
* Select different levels of difficulties, each level generates a different sized grid and amount of mines.
* The user can reveal a tile by following the prompts and entering a X and Y coordinate.
* Upon revealing an empty square it will reveal all other empty squares around it, using the recursive flood fill algorithm.
* If the user selects a Mine, the game is lost.
* If every empty square has been revealled the game is won.
  
#### Design Approach
The design of this application required an OOP approach, therefore I firstly designated what types of objects which will be used, and what are the object's relationship with one another, and also how do their methods influence one another. 

There are three main classes in this application, the `Main` class controls the user inputs and the text displayed to console, the `Grid` class which is responsible for generating the grid and drawing the grid based off user inputs, and the `Tiles` class which contains two inherited subclasses, which I will elaboorate upon. For context, the parent class `Tiles` has the responsibilty of defining the properties of a tile, and the setter/getter methods which will be needed for the child classes `Mine` and `Numbered`. The `Tiles` itself handles the rendering of the grid and the checks how many empty tiles are revealed or if the mine is revealed. The two inherited child classes `Mine` and `Numbered` has methods which generates their location on the grid.

#### What I struggled with 

I struggled on the feature where upon revealing an empty square, it should also reveal surrounding squares, creating a cascading effect. The first step I took was to search up the algorthirm's name that used in minesweeper, and going online to find visualisations and pseudocode to help me better understand the algorithm. I then wrote my own implementation with the guidance of the pseudocode. 

#### What I learnt
During this process I finally understood that recursion was **LIFO** (last in first out). 
This was the simple term which had made recursion much more comprehensible for me. 
Writing tests for the application also taught me that my methods in OOP should be as granular as possible, and writing unit tests can help improve the structure and design of your software, as it enforces one to reflect if a certain method can be broken down further.

