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
    (is (= (c/navigate-one {:loc {:x 0 :y 0}, :waypoint {:x 10 :y 1}}
                           {:action "F" :value 10})
           {:loc {:x 100 :y 10}, :waypoint {:x 10 :y 1}})))
  (testing "north"
    (is (= (c/navigate-one {:loc {:x 100 :y 10}, :waypoint {:x 10 :y 1}}
                           {:action "N" :value 3})
           {:loc {:x 100 :y 10}, :waypoint {:x 10 :y 4}})))
  (testing "rotate right"
    (is (= (c/navigate-one {:loc {:x 170 :y 38}, :waypoint {:x 10 :y 4}}
                           {:action "R" :value 90})
           {:loc {:x 170 :y 38}, :waypoint {:x 4 :y -10}}))))

(deftest rotate
  (is (= (c/rotate {:x 3 :y 4} "L" 90)
         {:x -4 :y 3})
      (= (c/rotate {:x -4 :y 3} "R" 90)
         {:x 3 :y 4})))

(deftest navigate
  (is (= (c/navigate example)
         {:loc {:x 214 :y -72}, :waypoint {:x 4 :y -10}})))

(deftest distance
  (is (= (c/distance {:loc {:x 214 :y -72, :waypoint {:x 4 :y -10}}})
         286)))
