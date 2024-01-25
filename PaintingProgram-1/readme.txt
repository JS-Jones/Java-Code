Joshua Jones
jjo108@u.rochester.edu
04/02/2023
CSC 171 HW #5 - URPaint

Algorithm description- This algorithm allows users to to draw squares and circles 
on a canvas, drag these shapes around, and change their color in addition to the 
color of the background. The main frame is called URPaint and it contains the main 
subclasses paintPanel (where the shapes are placed) and controlPanel (where the 
buttons and sliders are located). The control panel is further broken down into
a button (or top) and slider (or bottom) panel for format design. 

To use the program, first compile and run the algorithm. The square button is auto
selected so you can start placing squares in the black paint area. If you place and 
hold the square, you can drag it around. if you hold on a previously placed square, 
you will be able to drag that around as well. The same applies when you click on 
the circle buttons and start placing shapes. When you click on a shape, it becomes
"selected" and you can change it's color using the sliders. To deselect a shape, 
either click on the same shape again or click the palette button and then on any
empty space in the panel. Only when a shape isn't selected, can you change the color 
of the background (once again using the sliders to do so). Use the clear button to
wipe all the shapes in the panel.

The program works by storing the selectedshape and also a list of all the shapes in
the panel. Using these data structures, I was able to then create a class for shapes
and the subclasses Rectangle and Oval. These allowed the user to adjust the shape 
colors and also drag them around based on what's selected. Enjoy!

Self Review- This was a very challenging assigment and it involved a lot of self
teaching and experimenting. I don't believe I got the program down to it's smallest
details, but it does work with the general description. The biggest issue which I 
would like to address with further knowledge is to update the slider values based on
what's selected. I couldn't find a solution to this, but it doesn't affect the ability
of changing colors (would be cosmetic). Additionally, I wanted to add a highlight to 
the selected shapes, but once again I fell short in my abilities (especially when I 
would need to drag the shape around). The palette button doesn't do much (per instructions
from the professor that we didn't need to worry about getting the palette up), so I
rather used it to clear the selectedshape value so that users can change the paintPanel
background (users can also do this by selecting the same shape again to deselect).
Some methodology could also be better but it works fairly well given my knowledge otherwise.

Metadata- I spent around 12 hours on this assignment. I had to do a thorought review 
of the graphics content and it took a while to just figure out how to go about it.
I first completed one file with the formatting and then worked in different files for
the specific abilities. I was then able to combine all the different aspects together
after some thorough debugging.