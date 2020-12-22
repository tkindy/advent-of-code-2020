(ns day04.core
  (:gen-class)
  (:require [clojure.string :as s]
            [clojure.set :as set]
            [clojure.core.match :refer [match]]))

(defn split-passports
  [input]
  (let [passport-lines (s/split input #"\n\n")]
    (map #(s/replace % #"\n" " ") passport-lines)))

(defn parse-passport
  [line]
  (let [pairs (s/split line #"\s+")]
    (reduce (fn [acc pair]
              (let [[k v] (s/split pair #":")]
                (assoc acc (keyword k) v)))
            {} pairs)))

(defn read-input
  []
  (->> (slurp "resources/input")
       split-passports
       (map parse-passport)))

(def required-fields (set [:byr :iyr :eyr :hgt
                           :hcl :ecl :pid]))

(defn parse-int
  [s]
  (try (Integer/parseInt s)
       (catch Exception _
         nil)))

(def valid-eye-colors (set ["amb" "blu" "brn" "gry" "grn" "hzl" "oth"]))

(defn valid-field?
  [k v]
  (match (str k)
    ":byr" (let [year (parse-int v)]
             (and (not (nil? year))
                  (<= 1920 year 2002)))
    ":iyr" (let [year (parse-int v)]
             (and (not (nil? year))
                  (<= 2010 year 2020)))
    ":eyr" (let [year (parse-int v)]
             (and (not (nil? year))
                  (<= 2020 year 2030)))
    ":hgt" (let [[_ height unit] (re-matches #"(\d+)(cm|in)" v)
                 height (parse-int height)]
             (if (or (nil? height) (nil? unit))
               false
               (match unit
                 "cm" (<= 150 height 193)
                 "in" (<= 59 height 76))))
    ":hcl" (re-matches #"#[0-9a-f]{6}" v)
    ":ecl" (contains? valid-eye-colors v)
    ":pid" (re-matches #"\d{9}" v)
    ":cid" true))

(defn part1-valid?
  [passport]
  (set/subset? required-fields (set (keys passport))))

(defn part2-valid?
  [passport]
  (and (set/subset? required-fields (set (keys passport)))
       (every? #(let [[k v] %] (valid-field? k v))
               passport)))

(defn -main
  [& args]
  (let [passports (read-input)]
    (println (count passports) "total passports")
    (println "Part 1:" (count (filter part1-valid? passports)))
    (println "Part 2:" (count (filter part2-valid? passports)))))
