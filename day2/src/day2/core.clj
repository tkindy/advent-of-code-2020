(ns day2.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(defn parse-line
  [line]
  (let [[_ min-count max-count letter password] (re-find #"^(\d+)-(\d+) (\w): (\w+)$" line)]
    {:min-count (Integer/parseInt min-count)
     :max-count (Integer/parseInt max-count)
     :letter (.charAt letter 0) :password password}))

(defn read-input
  []
  (with-open [rdr (io/reader "resources/input")]
    (->> rdr
         line-seq
         (reduce conj [])
         (map parse-line))))

(defn count-occurrences
  [s c]
  (->> s
       (filter (fn [l] (= l c)))
       count))

(defn part1-valid?
  [pass]
  (let [count (count-occurrences (:password pass) (:letter pass))]
    (<= (:min-count pass)
        count
        (:max-count pass))))

(defn part1-count-valid
  [ps]
  (->> ps
       (filter part1-valid?)
       count))

(defn -main
  [& args]
  (let [ps (read-input)]
    (println "Part 1:" (part1-count-valid ps))))
