(ns day09.core
  (:gen-class))

; A Cube is a Boolean indicating whether the cube
; is active or inactive

; A Line is a SortedMap<Integer, Cube>
; wher ethe keys are x-coordinates

; A Plane is a SortedMap<Integer, Line>
; where the keys are y-coordinates

; A Space is a SortedMap<Integer, Plane>
; where the keys are z-coordinates

(defn -main
  [& args]
  (println "Hello, World!"))
