(ns day2.core-test
  (:require [clojure.test :refer :all]
            [day2.core :refer [parse-line count-occurrences valid? count-valid]]))

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
  (testing "valid?"
    (is (valid? at-min))
    (is (not (valid? too-few)))
    (is (valid? at-max)))
  (testing "count-valid"
    (is (= (count-valid [at-min too-few at-max]) 2))))
