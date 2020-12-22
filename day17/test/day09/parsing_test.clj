(ns day17.parsing-test
  (:require [clojure.test :refer :all]
            [day17.common-test :refer [example-state example-parsed]]
            [day17.parsing :refer [parse-input]]))

(deftest test-parse-input
  (let [parsed (parse-input example-state)]
    (is (= parsed example-parsed))
    (is (sorted? parsed))
    (is (every? sorted? (map (fn [[_ plane]] plane) parsed)))
    (is (every? sorted? (mapcat (fn [[_ plane]]
                                  (map (fn [[_ line]] line)
                                       plane))
                                parsed)))))
