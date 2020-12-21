(ns day4.core
  (:gen-class)
  (:require [clojure.string :as s]))

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

(defn -main
  [& args]
  (println "Hello, World!"))
