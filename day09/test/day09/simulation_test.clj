(ns day09.simulation-test
  (:require [clojure.test :refer :all]
            [day09.common-test :refer [example-parsed example-cycled]]
            [day09.simulation :refer [run-cycle get-neighbor-locs]]))

(deftest test-run-cycle
  (is (= (run-cycle example-parsed)
         example-cycled)))

(deftest test-get-neighbor-locs
  (is (= (get-neighbor-locs {:x 3, :y -7, :z 14})
         #{{:x 2 :y -8 :z 13} {:x 3 :y -8 :z 13} {:x 4 :y -8 :z 13}
           {:x 2 :y -7 :z 13} {:x 3 :y -7 :z 13} {:x 4 :y -7 :z 13}
           {:x 2 :y -6 :z 13} {:x 3 :y -6 :z 13} {:x 4 :y -6 :z 13}
           {:x 2 :y -8 :z 14} {:x 3 :y -8 :z 14} {:x 4 :y -8 :z 14}
           {:x 2 :y -7 :z 14}                    {:x 4 :y -7 :z 14}
           {:x 2 :y -6 :z 14} {:x 3 :y -6 :z 14} {:x 4 :y -6 :z 14}
           {:x 2 :y -8 :z 15} {:x 3 :y -8 :z 15} {:x 4 :y -8 :z 15}
           {:x 2 :y -7 :z 15} {:x 3 :y -7 :z 15} {:x 4 :y -7 :z 15}
           {:x 2 :y -6 :z 15} {:x 3 :y -6 :z 15} {:x 4 :y -6 :z 15}})))
