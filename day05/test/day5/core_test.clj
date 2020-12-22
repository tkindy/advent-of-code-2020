(ns day05.core-test
  (:require [clojure.test :refer :all]
            [day05.core :refer [resolve-path]]))

(deftest test-resolve-path
  (is (= (resolve-path 0 7 ['right 'left 'left])
         4)))
