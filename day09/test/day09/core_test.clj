(ns day09.core-test
  (:require [clojure.test :refer :all]
            [day09.core :refer [parse-input space->string]]))

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

(deftest test-space->string
  (is (= (space->string example-parsed)
         (str "z=0\n"
              example-state))))
