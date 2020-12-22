(ns day10.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map #(Integer/parseInt %))
       (reduce conj [])))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn make-pairs
  [l]
  (first
   (reduce (fn [[acc last] x]
             (let [acc (if last
                         (conj acc [last x])
                         acc)]
               [acc x]))
           [[] nil]
           l)))

(defn find-joltage-dist
  [adapters]
  (let [adapters (sort (conj adapters 0 (+ 3 (apply max adapters))))
        pairs (make-pairs adapters)
        differences (map (fn [[a b]] (- b a)) pairs)]
    (* (count (filter #(= 1 %) differences))
       (count (filter #(= 3 %) differences)))))

(defn -main
  [& args]
  (let [adapters (read-input)]
    (println "Part 1:" (find-joltage-dist adapters))))
