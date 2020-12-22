(ns day09.core-test
  (:require [clojure.test :refer :all]
            [day09.core :refer [parse-input]]))

(def example-state
  ".#.
..#
###")
(def example-parsed
  {0 {0 {0 false, 1 true,  2 false}
      1 {0 false, 1 false, 2 true}
      2 {0 true,  1 true,  2 true}}})

(deftest test-parse-input
  (is (= (parse-input example-state)
         example-parsed)))
