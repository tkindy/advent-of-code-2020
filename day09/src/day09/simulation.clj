(ns day09.simulation
  (:require [clojure.core.match :refer [match]]
            [clojure.math.combinatorics :as combo]))

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

(defn get-cube
  [space {:keys [x y z]}]
  (get-in space [z y x] false))

(defn get-neighbor-locs
  [loc]
  (let [{:keys [x y z]} loc]
    (->>
     (combo/cartesian-product [(- x 1) x (+ x 1)]
                              [(- y 1) y (+ y 1)]
                              [(- z 1) z (+ z 1)])
     (map (fn [[x y z]] {:x x, :y y, :z z}))
     (filter #(not= % loc))
     set)))

(defn run-cycle
  [space]
  space)
