# December 7, 2020

_Completed on December 21, 2020_

This one was interesting. It reminded me a lot of a web crawler; we're
recursively traversing a graph (in this case, a tree), adding neighbor nodes to
a "frontier". To implement part 2, I abstracted out that "crawling" core to make
it easy to accumulate arbitrary data during the traversal.

For part 2, I opted to do the counting in a pretty naive way -- I threw each
individual contained bag into the frontier. This meant I didn't need any special
logic for calculating how many bags we'd seen; it was a simple count of the
number of iterations through the recursive loop.

I'm getting more comfortable with Clojure. Looping was something I didn't quite
understand before. I see now that it's simply a shorthand for many hand-rolled
recursive functions I've written in the past.
