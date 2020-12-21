(ns day4.core
  (:gen-class)
  (:require [clojure.string :as s]
            [clojure.set :as set]))

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

(defn valid?
  [passport]
  (set/subset? required-fields (set (keys passport))))

(defn -main
  [& args]
  (let [passports (read-input)
        valid-passports (filter valid? passports)]
    (println (count passports) "total passports")
    (println "Part 1:" (count valid-passports))))
