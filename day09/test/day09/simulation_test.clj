(ns day09.simulation-test
  (:require [clojure.test :refer :all]
            [day09.common-test :refer [example-parsed example-cycled]]
            [day09.simulation :refer [run-cycle]]))

(deftest test-run-cycle
  (is (= (run-cycle example-parsed)
         example-cycled)))
