(ns day12.core
  (:gen-class)
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]
            [clojure.math.numeric-tower :refer [abs]]))

(defn parse-instruction
  [instruction]
  (let [[_ action value] (re-matches #"(N|S|E|W|L|R|F)(\d+)" instruction)]
    {:action action :value (Integer/parseInt value)}))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map parse-instruction)
       (reduce conj [])))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn loc+
  [{:keys [x y]} dx dy]
  {:x (+ x dx) :y (+ y dy)})

(defn loc+loc
  [{x1 :x y1 :y} {x2 :x y2 :y}]
  {:x (+ x1 x2) :y (+ y1 y2)})

(defn rotate
  [waypoint towards degrees]
  (let [{:keys [x y]} waypoint
        radians (* (/ Math/PI 180) degrees)
        radians (match towards
                  "R" (* radians -1)
                  "L" radians)
        cos (Math/cos radians)
        sin (Math/sin radians)]
    {:x (Math/round (- (* x cos) (* y sin)))
     :y (Math/round (+ (* x sin) (* y cos)))}))

(defn navigate-one
  [ship {:keys [action value]}]
  (let [{:keys [loc waypoint]} ship]
    (match action
      "N" (assoc ship :waypoint (loc+ waypoint 0 value))
      "S" (assoc ship :waypoint (loc+ waypoint 0 (- 0 value)))
      "E" (assoc ship :waypoint (loc+ waypoint value 0))
      "W" (assoc ship :waypoint (loc+ waypoint (- 0 value) 0))
      (:or "L" "R") (assoc ship :waypoint (rotate waypoint action value))
      "F" (if (zero? value)
            ship
            (navigate-one {:loc (loc+loc loc waypoint) :waypoint waypoint}
                          {:action "F" :value (dec value)})))))

(defn navigate
  [instructions]
  (reduce navigate-one
          {:loc {:x 0 :y 0}, :waypoint {:x 10 :y 1}}
          instructions))

(defn distance
  [ship]
  (let [{:keys [loc]} ship
        {:keys [x y]} loc]
    (+ (abs x) (abs y))))

(defn -main
  [& args]
  (let [instructions (read-input)]
    (println "Part 2:" (distance (navigate instructions)))))
