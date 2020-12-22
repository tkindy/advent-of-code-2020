(ns day09.simulation
  (:require [clojure.core.match :refer [match]]))

(defn count-active
  [cubes]
  (->> cubes
       (filter (fn [cube] cube))
       count))

(defn run-cycle-cube
  [cube neighbors]
  (let [num-active-neighbors (count-active neighbors)]
    (match [cube num-active-neighbors]
      [true (:or 2 3)] true
      [false 3] true
      :else false)))

(defn run-cycle
  [space]
  space)
