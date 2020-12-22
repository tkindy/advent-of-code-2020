# December 8, 2020

_Completed on December 22, 2020_

I got excited while reading this one because it touched on programming
languages. Big fan! It ever-so-slightly brought me back to my compilers class in
college. I've gotta dabble back into language design/implementation one of these
days.

Anyways, this challenge was pretty neat. The hardest part was dealing with some
weird Clojure-isms that I haven't developed intuition for yet. For example,
`map` in Clojure is lazy. That's pretty useful in general, but in this case,
it's annoying because I just want a vector of the instructions to index into and
apparently lazy sequences aren't associative like vectors are.

The best way I've found to convert a lazy seq into a vector is to just `conj`
them all together like this:

```clojure
(reduce conj [] some-lazy-seq)
```

It feels pretty janky, but it works. Now I just have to learn to pepper it in
wherever it's needed.

I'm wondering if there's a better way. I can't help but feel like I'm fighting
with the language.
