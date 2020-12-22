(ns day09.core-test
  (:require [clojure.test :refer :all]
            [day09.core :refer [parse-input space->string]]))

(def example-state
  ".#.
..#
###")
(def example-parsed

  (sorted-map 0
              (sorted-map 0 (sorted-map 0 false, 1 true,  2 false)
                          1 (sorted-map 0 false, 1 false, 2 true)
                          2 (sorted-map 0 true,  1 true,  2 true))))

(deftest test-parse-input
  (let [parsed (parse-input example-state)]
    (is (= parsed example-parsed))
    (is (sorted? parsed))
    (is (every? sorted? (map (fn [[_ plane]] plane) parsed)))
    (is (every? sorted? (mapcat (fn [[_ plane]]
                                  (map (fn [[_ line]] line)
                                       plane))
                                parsed)))))

(deftest test-space->string
  (is (= (space->string example-parsed)
         (str "z=0\n"
              example-state))))
