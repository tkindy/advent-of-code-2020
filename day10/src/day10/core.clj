(ns day10.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map #(Long/parseLong %))
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

(defn get-joltages
  [adapters]
  (conj adapters
        0 ; outlet
        (+ 3 (apply max adapters)))) ; phone

(defn find-joltage-dist
  [joltages]
  (let [pairs (make-pairs joltages)
        differences (map (fn [[a b]] (- b a)) pairs)]
    (* (count (filter #(= 1 %) differences))
       (count (filter #(= 3 %) differences)))))

(defn build-joltage-graph
  [joltages]
  (->> (combo/cartesian-product joltages joltages)
       (filter (fn [[out in]] (contains? #{1 2 3} (- in out))))
       (group-by first)
       (map (fn [[key entries]] [key (into [] (map second entries))]))
       (apply conj {})))

(defn count-paths
  [graph from to]
  (let [counts (reduce
                (fn [counts node]
                  (let [neighbors (get graph node [])
                        neighbor-counts (map #(if (= % to)
                                                1
                                                (get counts %))
                                             neighbors)]
                    (assoc counts node (apply + neighbor-counts))))
                {}
                (sort (java.util.Comparator/reverseOrder) (keys graph)))]
    (get counts from)))


(defn -main
  [& args]
  (let [adapters (read-input)
        joltages (sort (get-joltages adapters))]
    (println "Part 1:" (find-joltage-dist joltages))
    (println "Part 2:" (count-paths (build-joltage-graph joltages)
                                    0
                                    (apply max joltages)))))
