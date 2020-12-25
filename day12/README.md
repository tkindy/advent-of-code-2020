# December 12, 2020

_Completed on December 25, 2020_

I got bit by rounding errors on part 2 for a while. My answer kept being wrong
and I suspected the rounding in the rotation function. I had unit tests for it
though so I was banging my head against the wall trying to figure out what could
be wrong.

Then I realized I had accidentally passed a test case as the message argument to
an `is` assertion rather than putting it into its own `is`. That meant it wasn't
actually getting tested; I fixed the test case and it was failing. Dynamic
languages have their pros and cons.

Obviously, the problem was I was casting `double`s to `int`s rather than
`Math.round`ing them.
