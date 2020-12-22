(ns day8.core-test
  (:require [clojure.test :refer :all]
            [day8.core :refer [parse-input]]))

(def example-code
  "nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6")
(def example-parsed
  [['nop 0]
   ['acc 1]
   ['jmp 4]
   ['acc 3]
   ['jmp -3]
   ['acc -99]
   ['acc 1]
   ['jmp -4]
   ['acc 6]])

(deftest test-parse-input
  (is (= (parse-input example-code) example-parsed)))
