(ns day09.common-test)

(def example-state
  ".#.
..#
###")
(def example-parsed
  (sorted-map 0
              (sorted-map 0 (sorted-map 0 false, 1 true,  2 false)
                          1 (sorted-map 0 false, 1 false, 2 true)
                          2 (sorted-map 0 true,  1 true,  2 true))))
(def example-cycled
  (sorted-map -1
              (sorted-map 0 (sorted-map 0 true  1 false 2 false)
                          1 (sorted-map 0 false 1 false 2 true)
                          2 (sorted-map 0 false 1 true  2 false))
              0
              (sorted-map 0 (sorted-map 0 true  1 false 2 true)
                          1 (sorted-map 0 false 1 true  2 true)
                          2 (sorted-map 0 false 1 true  2 false))
              1
              (sorted-map 0 (sorted-map 0 true  1 false 2 false)
                          1 (sorted-map 0 false 1 false 2 true)
                          2 (sorted-map 0 false 1 true  2 false))))
