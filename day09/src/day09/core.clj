(ns day09.core
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

(defn valid?
  [n summand-pool]
  (let [candidates (->> (combo/cartesian-product summand-pool summand-pool)
                        (filter (fn [[a b]] (not= a b))))]
    (some (fn [[a b]] (= n (+ a b))) candidates)))

(def preamble-length 25)

(defn first-invalid
  [nums]
  (let [with-valid (map-indexed
                    (fn [index n]
                      (let [index (+ index preamble-length)]
                        [n
                         (valid? n
                                 (subvec nums (- index preamble-length) index))]))
                    (subvec nums preamble-length))]
    (first (first (filter #(not (second %)) with-valid)))))

(defn -main
  [& args]
  (let [nums (read-input)]
    (println "Part 1:" (first-invalid nums))))
