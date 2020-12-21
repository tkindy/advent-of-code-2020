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
  [the-map]
  (loop [x 0, y 0, path []]
    (if (>= y (count the-map))
      path
      (recur (mod (+ x 3) (count (first the-map)))
             (+ y 1)
             (conj path (-> the-map (nth y) (nth x)))))))

(defn count-trees
  [path]
  (->> path
       (filter #(= 'tree %))
       count))

(defn -main
  [& args]
  (let [the-map (read-input)
        path (get-path the-map)]
    (println "Part 1:" (count-trees path))))
