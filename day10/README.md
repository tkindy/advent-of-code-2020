# December 10, 2020

_Completed on December 22, 2020_

This one was fun! Graphs are a really cool data structure I like to play around
with whenever I get the chance. For counting the adapter combinations in part 2,
I noticed pretty quickly that the adapters could be represented by a [DAG] where
the nodes were the adapters, outlet, and phone and an edge from A to B meant
that B could plug into A.

[My first implementation] was a graph traversal where the frontier could contain
duplicates. Starting at the outlet node, I tossed all its neighbors into the
frontier and recurred, keeping count of the number of times the phone node was
found. I believe this was correct but it was just too slow; it ran for a couple
minutes without producing a result.

While working on that first iteration, I did notice it felt like I was doing a
bunch of duplicate work. If I'd already seen some node already, I shouldn't have
to recur on it again to come up with the same path count I already had. I
finally cracked the dynamic programming solution of working down the list of
joltages in reverse order and remembering the path count from each node. Since
it's a DAG, there's a [topological ordering] so we can compute the path counts
in order pretty easily.

Overall, I really liked this one.


[DAG]: https://en.wikipedia.org/wiki/Directed_acyclic_graph
[My first implementation]: https://github.com/tkindy/advent-of-code-2020/blob/692973660e415871875dfdb375cb0112bfbb0f47/day10/src/day10/core.clj
[topological ordering]: https://en.wikipedia.org/wiki/Topological_sorting
