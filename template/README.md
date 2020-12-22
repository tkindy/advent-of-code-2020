# Advent of Code 2020 Leiningen Template

This is a super simple [Leiningen] template I threw together to quickly
bootstrap a new Clojure project for each AOC challenge.

This is very similar to the built-in `app` template but I stripped out some of
the more general-purpose stuff. I found myself deleting lots of stuff every time
I'd make a new project for a new challenge, so I automated that.


## Background

[Leiningen template docs]


## Instructions

First, you need to install the template to your local Maven repository. You
should only need to do this once (or whenever you update the template).

```shell
> cd template
> lein install
> cd ..
```

Then, you can create a new Leiningen project using the template.

```shell
> lein new aoc-clj dayX --snapshot
```


[Leiningen]: https://leiningen.org/
[Leiningen template docs]: https://github.com/technomancy/leiningen/blob/master/doc/TEMPLATES.md
