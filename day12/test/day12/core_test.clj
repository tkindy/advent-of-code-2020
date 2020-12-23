(ns day12.core-test
  (:require [clojure.test :refer :all]
            [day12.core :as c]))

(def example-string
  "F10
N3
F7
R90
F11")
(def example [{:action "F" :value 10}
              {:action "N" :value 3}
              {:action "F" :value 7}
              {:action "R" :value 90}
              {:action "F" :value 11}])

(deftest parse-input
  (is (= (c/parse-input example-string)
         example)))

(deftest navigate-one
  (testing "forward"
    (is (= (c/navigate-one {:loc {:x 0 :y 0}, :dir "E"}
                           {:action "F" :value 10})
           {:loc {:x 10 :y 0}, :dir "E"})))
  (testing "north"
    (is (= (c/navigate-one {:loc {:x 10 :y 0}, :dir "E"}
                           {:action "N" :value 3})
           {:loc {:x 10 :y -3}, :dir "E"})))
  (testing "rotate right"
    (is (= (c/navigate-one {:loc {:x 17 :y -3}, :dir "E"}
                           {:action "R" :value 90})
           {:loc {:x 17 :y -3}, :dir "S"}))))

(deftest navigate
  (is (= (c/navigate example)
         {:loc {:x 17 :y 8}, :dir "S"})))
