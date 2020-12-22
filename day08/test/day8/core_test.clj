(ns day8.core-test
  (:require [clojure.test :refer :all]
            [day8.core :refer [parse-input execute fix-program]]))

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
  [{:op 'nop :arg 0}
   {:op 'acc :arg 1}
   {:op 'jmp :arg 4}
   {:op 'acc :arg 3}
   {:op 'jmp :arg -3}
   {:op 'acc :arg -99}
   {:op 'acc :arg 1}
   {:op 'jmp :arg -4}
   {:op 'acc :arg 6}])

(deftest test-parse-input
  (is (= (parse-input example-code) example-parsed)))

(deftest test-find-loop
  (is (= (execute example-parsed) {:reason 'looped :accumulator 5})))

(deftest test-fix-program
  (is (= (fix-program example-parsed) 8)))
