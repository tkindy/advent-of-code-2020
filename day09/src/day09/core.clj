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
       (apply merge)))

(defn parse-input
  [input]
  {0 (->> input
          str/split-lines
          (map-indexed (fn [index line] {index (parse-line line)}))
          (apply merge))})

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn cube->string
  [cube])

(defn row->string
  [row])

(defn space->string
  [space]
  (str/join "\n"))

(defn -main
  [& args]
  (println "Hello, World!"))
