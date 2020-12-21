(ns day2.core-test
  (:require [clojure.test :refer :all]
            [day2.core :refer [parse-line count-occurrences]]))

(deftest a-test
  (testing "parse-line"
    (is (= (parse-line "1-3 a: abcde")
           {:min-count 1 :max-count 3
            :letter \a :password "abcde"})))
  (testing "count-occurrences"
    (is (= (count-occurrences "abcdbab" \b)
           3))))
