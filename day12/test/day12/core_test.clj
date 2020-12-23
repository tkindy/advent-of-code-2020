(ns day12.core-test
  (:require [clojure.test :refer :all]
            [day12.core :as c]))

(def example-string
  "F10
N3
F7
R90
F11")

(deftest parse-input
  (is (= (c/parse-input example-string)
         [{:action "F" :value 10}
          {:action "N" :value 3}
          {:action "F" :value 7}
          {:action "R" :value 90}
          {:action "F" :value 11}])))
