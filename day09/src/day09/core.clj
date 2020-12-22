(ns day09.core
  (:gen-class)
  (:require [clojure.string :as str]))

; A Cube is a Boolean indicating whether the cube
; is active or inactive

; A Line is a SortedMap<Integer, Cube>
; wher ethe keys are x-coordinates

; A Plane is a SortedMap<Integer, Line>
; where the keys are y-coordinates

; A Space is a SortedMap<Integer, Plane>
; where the keys are z-coordinates

(defn parse-line
  [line]
  (->> line
       (map-indexed (fn [index c] {index (= \# c)}))
       (apply merge (sorted-map))))

(defn parse-input
  [input]
  (sorted-map 0
              (->> input
                   str/split-lines
                   (map-indexed (fn [index line] {index (parse-line line)}))
                   (apply merge (sorted-map)))))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn cube->string
  [cube]
  (if cube "#" "."))

(defn line->string
  [line]
  (let [cubes (map (fn [[_ cube]] (cube->string cube))
                   line)]
    (str/join cubes)))

(defn plane->string
  [plane]
  (let [lines (map (fn [[_ line]] (line->string line))
                   plane)]
    (str/join "\n" lines)))

(defn space->string
  [space]
  (let [planes (map (fn [[z plane]]
                      (str "z=" z "\n"
                           (plane->string plane)))
                    space)]
    (str/join "\n" planes)))

(defn -main
  [& args]
  (println "Hello, World!"))
