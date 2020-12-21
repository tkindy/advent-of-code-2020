(ns day3.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.core.match :refer [match]]))

(defn parse-line
  [line]
  (map (fn [c]
         (match c
           \. 'open
           \# 'tree))
       line))

(defn read-input
  []
  (with-open [rdr (io/reader "resources/input")]
    (->> rdr
         line-seq
         (reduce conj [])
         (map parse-line))))

(defn get-path
  [the-map dx dy]
  (loop [x 0, y 0, path []]
    (if (>= y (count the-map))
      path
      (recur (mod (+ x dx) (count (first the-map)))
             (+ y dy)
             (conj path (-> the-map (nth y) (nth x)))))))

(defn count-trees
  [path]
  (->> path
       (filter #(= 'tree %))
       count))

(defn -main
  [& args]
  (let [the-map (read-input)
        part1-path (get-path the-map 3 1)
        slopes [[1 1] [3 1] [5 1] [7 1] [1 2]]
        part2-paths (map #(get-path the-map (first %) (second %)) slopes)
        part2-counts (map count-trees part2-paths)]
    (println "Part 1:" (count-trees part1-path))
    (println "Part 2:" (apply * part2-counts))))
