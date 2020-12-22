# December 9, 2020

_Completed on December 22, 2020_

This one was neat. At first, I was a little scared by the computational
complexity of the problems. Part 1 was linear but Part 2 felt like it could be a
lot of work. Turns out, with only 1000 numbers, it wasn't bad at all.

For Part 2, I went with a sliding window solution. Using a fixed window size, I
check whether each consecutive set of that many numbers in the list sum to the
expected number. If I don't find one at that window size, I increment the size
by one and try again. That ended up finding the solution pretty quick.
