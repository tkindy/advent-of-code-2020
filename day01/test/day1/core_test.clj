(ns day1.core-test
  (:require [clojure.test :refer :all]
            [day1.core :refer (cart)]))

(deftest a-test
  (testing "cart"
    (is (= (set (cart '((a b) (1 2 3))))
           (set '((a 1) (a 2) (a 3) (b 1) (b 2) (b 3)))))))
