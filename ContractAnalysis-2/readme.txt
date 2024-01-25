Joshua Jones
jjo108@u.rochester.edu
03/02/2023
CSC 171 HW #4 - Airplane

Algorithm description- The user first input a number which tells the program the number of airplanes to consider (which an array
is made then). While loop through to that number, the user inputs airplane data for each including <name> <TOW_min> <TOW_max> <range> 
<speed> <hourly_cost> <fuel_rate>. An airplane object is created for each plane and run through using a constructor.

The user then inputs contract data containing <mass> <distance> <payment>. Before checking profit, the program checks for each plane 
whether the mass + TOW_min less that of TOW_max and if distance is less than range to ensure that it is within the capability of the 
plane. The profitability is then calculated by calling the calcProfit() method that determines the flight time, staff costs, and 
fuel costs. The costs are subtracted from the payment to determine profit which is then returned. If profitable, it add to a total
profit variable which is then printed once the user quits.

Self Review- I believe the program is mostly correct. While my methodology might differ, my numbers match up with that of the example
output. There are no bugs or defects remaining and I believe that I covered all cases where a profit can not be made. Rounding may be 
off in some cases.

Metadata- I spent a total of 3-4 hours on this assignment. The first half of the time was spent reviewing object-oriented programming
and the second half of the time was spent trying to understand the instructions. Programming wasn't necessairly difficult, but I did
have the read the instructions multiple times to understand and get the basic gist of what is being asked. For example, I got confused
whether all the inputs have to come in and then the outputs. No particularly difficult bugs. 