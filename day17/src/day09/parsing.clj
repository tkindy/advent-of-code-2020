(ns day17.parsing
  (:require [clojure.string :as str]))

(defn parse-line
  [line]
  (->> line
       (map-indexed (fn [index c] {index (= \# c)}))
       (apply merge (sorted-map))))

(defn parse-input
  [input]
  (sorted-map 0
              (sorted-map 0
                          (->> input
                               str/split-lines
                               (map-indexed (fn [index line] {index (parse-line line)}))
                               (apply merge (sorted-map))))))

(defn read-input
  []
  (parse-input (slurp "resources/input")))
