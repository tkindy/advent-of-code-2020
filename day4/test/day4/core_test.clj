(ns day4.core-test
  (:require [clojure.test :refer :all]
            [day4.core :refer [split-passports parse-passport part1-valid?
                               valid-field? part2-valid?]]))

(def example-input
  "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in")
(def all-fields {:ecl "gry" :pid "860033327" :eyr "2020" :hcl "#fffffd"
                 :byr "1937" :iyr "2017" :cid "147" :hgt "183cm"})
(def missing-hgt {:iyr "2013" :ecl "amb" :cid "350" :eyr "2023"
                  :pid "028048884" :hcl "#cfa07d" :byr "1929"})
(def missing-cid {:hcl "#ae17e1" :iyr "2013" :eyr "2024" :ecl "brn"
                  :pid "760753108" :byr "1931" :hgt "179cm"})
(def missing-cid-byr {:hcl "#cfa07d" :eyr "2025" :pid "166559648"
                      :iyr "2011" :ecl "brn" :hgt "59in"})

(deftest test-split-passports
  (is (= (split-passports example-input)
         ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
          "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
          "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"
          "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"])))

(deftest test-parse-passport
  (is (= (parse-passport "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm")
         {:ecl "gry" :pid "860033327" :eyr "2020" :hcl "#fffffd"
          :byr "1937" :iyr "2017" :cid "147" :hgt "183cm"})))

(deftest test-valid?
  (is (part1-valid? all-fields))
  (is (not (part1-valid? missing-hgt)))
  (is (part1-valid? missing-cid))
  (is (not (part1-valid? missing-cid-byr))))

(deftest test-valid-field?
  (testing "byr"
    (is (not (valid-field? :byr "asiduasd")))
    (is (valid-field? :byr "1920"))
    (is (valid-field? :byr "2002"))
    (is (not (valid-field? :byr "1919")))
    (is (not (valid-field? :byr "2003"))))
  (testing "hgt"
    (is (valid-field? :hgt "174cm"))
    (is (valid-field? :hgt "73in"))
    (is (not (valid-field? :hgt "asjdih9")))
    (is (not (valid-field? :hgt "149cm")))))

(deftest test-part2-valid?
  (is (part2-valid? all-fields)))
