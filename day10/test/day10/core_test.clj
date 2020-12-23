(ns day10.core-test
  (:require [clojure.test :refer :all]
            [day10.core :refer [build-joltage-graph]]))

(def joltages [16 10 15 5 1 11
               7 19 6 12 4 0 22])

(deftest test-build-joltage-graph
  (is (= (build-joltage-graph joltages)
         {16 [19]
          10 [11 12]
          15 [16]
          5  [7 6]
          1  [4]
          11 [12]
          7  [10]
          19 [22]
          6  [7]
          12 [15]
          4  [5 7 6]
          0  [1]})))
