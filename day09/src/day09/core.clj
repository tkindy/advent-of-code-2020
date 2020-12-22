(ns day09.core
  (:gen-class)
  (:require [day09.parsing :refer [read-input]]
            [day09.simulation :refer [run-cycle get-active-locs]]))

; A Cube is a Boolean indicating whether the cube
; is active or inactive

; A Line is a SortedMap<Integer, Cube>
; wher ethe keys are x-coordinates

; A Plane is a SortedMap<Integer, Line>
; where the keys are y-coordinates

; A Space is a SortedMap<Integer, Plane>
; where the keys are z-coordinates

; A Loc is a {:x x, :y y, :z z}
; representing a 3D Cartesian coordinate

(defn -main
  [& args]
  (let [space (read-input)]
    (println "Part 1:" (->> (reduce (fn [cur-space _] (run-cycle cur-space))
                                    space
                                    (range 6))
                            get-active-locs
                            count))))
