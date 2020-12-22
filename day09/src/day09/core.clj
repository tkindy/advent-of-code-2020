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

(defn check-window
  [nums invalid window-size]
  (loop [index 0]
    (if (> (+ index window-size) (count nums))
      nil
      (let [window (subvec nums index (+ index window-size))]
        (if (= (apply + window) invalid)
          window
          (recur (+ index 1)))))))

(defn find-weakness
  [nums invalid]
  (let [window (some #(check-window nums invalid %)
                     (range 2 (+ (count nums) 1)))]
    (+ (apply min window)
       (apply max window))))

(defn -main
  [& args]
  (let [nums (read-input)
        invalid (first-invalid nums)]
    (println "Part 1:" invalid)
    (println "Part 2:" (find-weakness nums invalid))))
