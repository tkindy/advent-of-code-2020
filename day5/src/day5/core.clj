(ns day5.core
  (:gen-class)
  (:require [clojure.core.match :refer [match]]
            [clojure.java.io :as io]))

(defn resolve-path
  [low high path]
  (first
   (reduce (fn [[low high] dir]
             (let [middle (quot (+ low high) 2)]
               (match dir
                 'left [low middle]
                 'right [(+ 1 middle) high])))
           [low high] path)))

(defn parse-line
  [line]
  (let [row-path (map #(match %
                         \F 'left
                         \B 'right)
                      (.substring line 0 7))
        col-path (map #(match %
                         \L 'left
                         \R 'right)
                      (.substring line 7 10))
        row (resolve-path 0 127 row-path)
        column (resolve-path 0 7 col-path)]
    {:row row :column column :id (+ (* row 8) column)}))

(defn read-input
  []
  (with-open [rdr (io/reader "resources/input")]
    (->> rdr
         line-seq
         (reduce conj [])
         (map parse-line))))

(defn -main
  [& args]
  (let [seats (read-input)]
    (println "Part 1:" (apply max (map :id seats)))))
