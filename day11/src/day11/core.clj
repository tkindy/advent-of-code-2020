(ns day11.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]))

(defn parse-seat
  [seat]
  (match seat
    \. 'floor
    \L 'empty
    \# 'occupied))

(defn parse-row
  [row]
  (->> row
       (map-indexed (fn [index seat] [index (parse-seat seat)]))
       (apply conj {})))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map-indexed (fn [index row] [index (parse-row row)]))
       (apply conj {})))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn -main
  [& args]
  (println "Hello, World!"))
