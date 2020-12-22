(ns day02.core-test
  (:require [clojure.test :refer :all]
            [day02.core :refer [parse-line count-occurrences
                                part1-valid? part1-count-valid]]))

(def at-min {:min-count 1 :max-count 3
             :letter \a :password "abcde"})
(def too-few {:min-count 1 :max-count 3
              :letter \b :password "cdefg"})
(def at-max {:min-count 2 :max-count 9
             :letter \c :password "ccccccccc"})

(deftest a-test
  (testing "parse-line"
    (is (= (parse-line "1-3 a: abcde") at-min)))
  (testing "count-occurrences"
    (is (= (count-occurrences "abcdbab" \b) 3)))
  (testing "part1-valid?"
    (is (part1-valid? at-min))
    (is (not (part1-valid? too-few)))
    (is (part1-valid? at-max)))
  (testing "part1-count-valid"
    (is (= (part1-count-valid [at-min too-few at-max]) 2))))
