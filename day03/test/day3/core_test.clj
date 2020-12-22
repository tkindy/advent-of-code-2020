(ns day03.core-test
  (:require [clojure.test :refer :all]
            [day03.core :refer [parse-line]]))

(deftest test-parse-line
  (testing "parse-line"
    (is (= (parse-line ".#..###.")
           ['open 'tree 'open 'open 'tree 'tree 'tree 'open]))))
